package com.gfa.memorymanagement.optimized.P02InefficientRecursion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FolderSearchOptimized {
  public static void main(String[] args) {
    List<String> files = new ArrayList<>();
    optimized(files, "C:/");
    System.out.println(files.size());
  }

  private static void optimized(List<String> files, String scanPath) {
    File scanDir = new File(scanPath);
    File[] scannedFilesAndFolders = scanDir.listFiles();

    if (scannedFilesAndFolders == null)
      return;

    for (File file : scannedFilesAndFolders) {
      if (file.isDirectory()) {
        optimized(files, file.getPath());
      } else {
        files.add(file.getPath());
      }
    }
  }
}
