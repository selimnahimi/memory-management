package com.gfa.memorymanagement.unoptimized.P05Caching;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LoremIpsum {
  public static void main(String[] args) throws IOException {
    String loremIpsum = Files.readAllLines(Path.of("assets/loremipsum.txt")).stream()
            .reduce((out, elem) -> out + " " + elem)
            .get();

    
  }
}
