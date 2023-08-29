package com.gfa.memorymanagement.P02InefficientRecursion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SP01FolderSearch {
  public static void main(String[] args) {
    System.out.println(optimized("C:/").size());
    // System.out.println(unoptimized("C:/Windows").size());
  }

  private static List<String> optimized(String scanPath) {
    File scanDir = new File(scanPath);
    File[] scannedFilesAndFolders = scanDir.listFiles();

    if (scannedFilesAndFolders == null)
      return new ArrayList<>();

    List<String> scannedFilenames = Stream.of(scannedFilesAndFolders)
            .filter(file -> !file.isDirectory())
            .map(File::getAbsolutePath)
            .collect(Collectors.toList());

    List<File> scannedFolders = Stream.of(scannedFilesAndFolders)
            .filter(file -> file.isDirectory())
            .collect(Collectors.toList());

    for (File folder : scannedFolders) {
      List<String> result = optimized(folder.getPath());

      for (String fileName : result) {
        scannedFilenames.add(fileName);
      }
    }

    return scannedFilenames;
  }

  private static List<String> unoptimized(String scanPath) {
    File scanDir = new File(scanPath);
    File[] scannedFilesAndFolders = scanDir.listFiles();

    if (scannedFilesAndFolders == null)
      return new ArrayList<>();

    List<String> scannedFilenames = Stream.of(scannedFilesAndFolders)
            .filter(file -> !file.isDirectory())
            .map(File::getAbsolutePath)
            .collect(Collectors.toList());

    List<File> scannedFolders = Stream.of(scannedFilesAndFolders)
            .filter(file -> file.isDirectory())
            .collect(Collectors.toList());

    for (File folder : scannedFolders) {
      List<String> result = unoptimized(folder.getPath());

      for (String fileName : result) {
        scannedFilenames.add(fileName);
      }
    }

    return scannedFilenames;
  }
}
