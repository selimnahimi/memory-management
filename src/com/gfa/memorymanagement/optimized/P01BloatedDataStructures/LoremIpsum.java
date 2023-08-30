package com.gfa.memorymanagement.optimized.P01BloatedDataStructures;

import java.nio.file.Files;
import java.nio.file.Path;

public class LoremIpsum {
  public static void main(String[] args) throws Exception {
    String loremIpsum = Files.readAllLines(Path.of("assets/loremipsum.txt")).stream()
            .reduce((out, elem) -> out + " " + elem)
            .get();
  }
}
