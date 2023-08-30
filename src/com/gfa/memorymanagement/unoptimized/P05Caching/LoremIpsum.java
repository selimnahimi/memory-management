package com.gfa.memorymanagement.unoptimized.P05Caching;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class LoremIpsum {
  public static void main(String[] args) throws Exception {
    optimized();
  }

  private static void optimized() throws FileNotFoundException {
    File file = new File("assets/loremipsum.txt");
    ArrayList<Integer> textIndices = new ArrayList<>();

    DB db = DBMaker.fileDB("words.db").make();
    HTreeMap wordMap = db.hashMap("loremIpsum").createOrOpen();
    Scanner scanner = new Scanner(file);

    int counter = 0;
    while (scanner.hasNext()) {
      String word = scanner.next();
      if (!wordMap.containsKey(word)) {
        wordMap.put(word, counter++);
      }

      int index = (int) wordMap.get(word);

      textIndices.add(index);
    }

    System.out.println(textIndices);

    for (int index : textIndices) {
      System.out.print(wordMap.get(index) + " ");
    }
    System.out.println(wordMap.size());

    db.close();
  }

  private static void unoptimized() throws IOException {
    String loremIpsum = Files.readAllLines(Path.of("assets/loremipsum.txt")).stream()
            .reduce((out, elem) -> out + " " + elem)
            .get();
  }
}
