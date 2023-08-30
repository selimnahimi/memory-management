package com.gfa.memorymanagement.optimized.P06OffHeap;

import net.openhft.chronicle.set.ChronicleSet;
import net.openhft.chronicle.set.ChronicleSetBuilder;

import java.time.Instant;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LongStorageOptimized {
  public static void main(String[] args) throws Exception {
    System.out.println(optimized());
  }

  private static long optimized() throws InterruptedException {
    ChronicleSet<Long> set = ChronicleSetBuilder.of(Long.class)
            .entries(30_000_000)
            .create();
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
