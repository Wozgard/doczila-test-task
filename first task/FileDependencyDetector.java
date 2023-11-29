import java.util.*;

public class FileDependencyDetector {
  private Map<String, List<String>> fileDependencies;

  public FileDependencyDetector() {
    fileDependencies = new HashMap<>();
  }

  public void addDependency(String sourceFile, String targetFile) {
    if (!fileDependencies.containsKey(sourceFile)) {
      fileDependencies.put(sourceFile, new ArrayList<>());
    }
    fileDependencies.get(sourceFile).add(targetFile);
  }

  public boolean hasCyclicDependency() {
    Set<String> visited = new HashSet<>();
    Set<String> recursionStack = new HashSet<>();

    for (String file : fileDependencies.keySet()) {
      if (isCyclic(file, visited, recursionStack)) {
        return true;
      }
    }

    return false;
  }

  private boolean isCyclic(String file, Set<String> visited, Set<String> recursionStack) {
    if (recursionStack.contains(file)) {
      return true;
    }

    if (visited.contains(file)) {
      return false;
    }

    visited.add(file);
    recursionStack.add(file);

    if (fileDependencies.containsKey(file)) {
      for (String dependentFile : fileDependencies.get(file)) {
        if (isCyclic(dependentFile, visited, recursionStack)) {
          return true;
        }
      }
    }

    recursionStack.remove(file);
    return false;
  }
}
