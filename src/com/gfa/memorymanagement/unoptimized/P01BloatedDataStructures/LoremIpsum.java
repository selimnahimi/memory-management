package com.gfa.memorymanagement.unoptimized.P01BloatedDataStructures;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class LoremIpsum {
  public static void main(String[] args) throws Exception {
    // unoptimized();
    optimized();
  }

  private static void unoptimized() throws IOException {
    String loremIpsum = Files.readAllLines(Path.of("assets/loremipsum.txt")).stream()
            .reduce((out, elem) -> out + " " + elem)
            .get();

    String[] words = loremIpsum.split(" ");

    for (String word : words) {
      System.out.print(word + " ");
    }
  }

  private static void optimized() throws FileNotFoundException, ExecutionException {
    class CachedTextFile {
      File file;
      Scanner scanner;
      CacheLoader<Integer, String> wordLoader;
      LoadingCache<Integer, String> wordCache;
      int length;

      public CachedTextFile(String path) throws FileNotFoundException {
        file = new File(path);
        scanner = new Scanner(file);
        wordLoader = new CacheLoader<>() {
          @Override
          public String load(Integer key) {
            return scanner.next();
          }
        };
        wordCache = CacheBuilder.newBuilder().maximumSize(10).build(wordLoader);

        length = 0;
      }

      public void loadFile() {
        while(scanner.hasNext()) {
          wordCache.getUnchecked(length++);
        }
      }

      public long getCacheSize() {
        return wordCache.size();
      }

      public String get(int index) throws ExecutionException {
        return wordCache.get(index);
      }
    }

    CachedTextFile cachedLoremIpsum = new CachedTextFile("assets/loremipsum.txt");
    cachedLoremIpsum.loadFile();

    System.out.println(cachedLoremIpsum.getCacheSize());

    for (int i = 0; i < cachedLoremIpsum.length; i++) {
      System.out.print(cachedLoremIpsum.get(i) + " ");
    }
  }
}
