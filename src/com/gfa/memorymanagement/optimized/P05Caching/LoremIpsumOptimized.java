package com.gfa.memorymanagement.optimized.P05Caching;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoremIpsumOptimized {
  public static void main(String[] args) throws Exception {
    optimized();
  }

  private static void optimized() throws FileNotFoundException {
    File file = new File("assets/loremipsum_big.txt");

    DB db = DBMaker
            .fileDB("words.db")
            .fileMmapEnable()
            .fileMmapEnableIfSupported()
            .fileMmapPreclearDisable()
            .cleanerHackEnable()
            .make();

    HTreeMap wordMap = db
            .hashMap("loremIpsum")
            .createOrOpen();

    Scanner scanner = new Scanner(file);

    while (scanner.hasNext()) {
      String word = scanner.next();
      int count = 0;

      if (wordMap.containsKey(word))
        count = (int) wordMap.get(word);

      wordMap.put(word, count + 1);
    }

    db.close();
  }
}
