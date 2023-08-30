package com.gfa.memorymanagement.unoptimized.P06OffHeap;

import net.openhft.chronicle.set.ChronicleSet;
import net.openhft.chronicle.set.ChronicleSetBuilder;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LongStorage {
  public static void main(String[] args) throws Exception {
    System.out.println(unoptimized());
  }

  private static long unoptimized() throws InterruptedException {
    HashSet<Long> set = new HashSet<>();
    return sumNumbers(set);
  }

  private static long sumNumbers(Set<Long> numbers) throws InterruptedException {
    final Random random = new Random();
    for (int i = 0; i < 30_000_000; i++) {
      numbers.add(random.nextLong());
      if (i % 1_000_000 == 0) Thread.sleep(1000);
    }
    return numbers.stream().reduce(0L, Long::sum);
  }
}
