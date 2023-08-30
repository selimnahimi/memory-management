package com.gfa.memorymanagement.unoptimized.P05Caching;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class LoremIpsum {
  public static void main(String[] args) throws Exception {
    unoptimized();
  }

  private static void unoptimized() throws IOException {
    String loremIpsum = Files.readAllLines(Path.of("assets/loremipsum_big.txt")).stream()
            .reduce((out, elem) -> out + " " + elem)
            .get();

    HashMap<String, Integer> wordMap = new HashMap<>();

    for (String word : loremIpsum.split(" ")) {
      if (!wordMap.containsKey(word)) {
        wordMap.put(word, 1);
      }

      int count = wordMap.get(word);
      wordMap.put(word, count + 1);
    }
  }
}
