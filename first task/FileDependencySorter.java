import java.util.*;

public class FileDependencySorter {
  private Map<String, List<String>> fileDependencies;

  public FileDependencySorter(Map<String, List<String>> fileDependencies) {
    this.fileDependencies = fileDependencies;
  }

  public List<String> sortFilesByDependency() {
    Map<String, Integer> inDegree = new HashMap<>();
    Queue<String> queue = new LinkedList<>();
    Map<String, String> fileNameToAbsolutePath = new HashMap<>();
    List<String> sortedFiles = new ArrayList<>();

    // Создаем отображение для подсчета входящих связей (in-degree) для каждого
    // файла
    for (String file : fileDependencies.keySet()) {
      inDegree.putIfAbsent(file, 0);
      for (String dependentFile : fileDependencies.get(file)) {
        inDegree.put(dependentFile, inDegree.getOrDefault(dependentFile, 0) + 1);
      }
    }

    // Находим файлы без зависимостей и добавляем их в очередь
    for (String file : inDegree.keySet()) {
      if (inDegree.get(file) == 0) {
        queue.add(file);
      }
    }

    // Создаем отображение имен файлов к абсолютным путям
    for (String absolutePath : fileDependencies.keySet()) {
      String fileName = getFileName(absolutePath);
      fileNameToAbsolutePath.putIfAbsent(fileName, absolutePath);
    }

    // Топологическая сортировка
    while (!queue.isEmpty()) {
      String file = queue.poll();
      String absolutePath = fileNameToAbsolutePath.getOrDefault(file, null);
      if (absolutePath != null) {
        sortedFiles.add(absolutePath); // Добавляем абсолютный путь файла в итоговый список
      }

      if (fileDependencies.containsKey(file)) {
        for (String dependentFile : fileDependencies.get(file)) {
          inDegree.put(dependentFile, inDegree.get(dependentFile) - 1);
          if (inDegree.get(dependentFile) == 0) {
            queue.add(dependentFile);
          }
        }
      }
    }

    return sortedFiles;
  }

  // Метод для получения имени файла из абсолютного пути
  private String getFileName(String path) {
    int index = path.lastIndexOf('/');
    if (index >= 0) {
      return path.substring(index + 1);
    }
    return path;
  }
}
