import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
  private static Set<String> processedFiles = new HashSet<>();

  public static void main(String[] args) {
    Map<String, List<String>> fileDependencies = new HashMap<>();
    try {
      Files.walk(Paths.get("./fileSystem"))
          .filter(Files::isRegularFile)
          .forEach(file -> {
            try {
              processFile(file.toString(), fileDependencies);
            } catch (IOException e) {
              e.printStackTrace();
            }
          });

      FileDependencyDetector detector = new FileDependencyDetector();
      for (Map.Entry<String, List<String>> entry : fileDependencies.entrySet()) {
        String sourceFile = entry.getKey();
        List<String> dependencies = entry.getValue();
        for (String dependency : dependencies) {
          detector.addDependency(sourceFile, dependency);
        }
      }

      if (!detector.hasCyclicDependency()) {
        System.out.println(fileDependencies);
        FileDependencySorter sorter = new FileDependencySorter(fileDependencies);
        List<String> sortedFiles = sorter.sortFilesByDependency();
        System.out.println(sortedFiles);

        FileWriter writer = new FileWriter("output.txt");
        for (String file : sortedFiles) {
          String content = readFileContent(file);
          writer.write(content);
        }
        writer.close();
      } else {
        System.out.println("Cyclic dependencies found.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void processFile(String filePath, Map<String, List<String>> fileDependencies) throws IOException {
    Path rootPath = Paths.get("fileSystem").toAbsolutePath();
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line;

    if (!fileDependencies.containsKey(filePath)) {
      fileDependencies.put(filePath, new ArrayList<>());
    }

    while ((line = reader.readLine()) != null) {
      if (line.contains("require")) {
        int start = line.indexOf("'");
        int end = line.indexOf("'", start + 1);
        if (start != -1 && end != -1 && end > start) {
          String dependency = line.substring(start + 1, end);
          Path absolutePath = rootPath.resolve(dependency.replace("/", File.separator));
          addDependency(fileDependencies, filePath, absolutePath.toString());
        }
      }
    }
    reader.close();
  }

  private static void addDependency(Map<String, List<String>> fileDependencies, String sourceFile, String dependency) {
    if (!fileDependencies.containsKey(sourceFile)) {
      fileDependencies.put(sourceFile, new ArrayList<>());
    }
    fileDependencies.get(sourceFile).add(dependency);
  }

  private static String readFileContent(String fileName) throws IOException {
    if (processedFiles.contains(fileName)) {
      return ""; // Если файл уже был обработан, вернуть пустую строку
    }

    processedFiles.add(fileName); // Добавить файл в список обработанных

    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    StringBuilder content = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      content.append(line).append("\n");
    }
    reader.close();
    return content.toString();
  }
}
