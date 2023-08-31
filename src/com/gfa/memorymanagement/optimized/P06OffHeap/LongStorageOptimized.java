package com.gfa.memorymanagement.optimized.P06OffHeap;

import net.openhft.chronicle.set.ChronicleSet;
import net.openhft.chronicle.set.ChronicleSetBuilder;

import java.util.Random;
import java.util.Set;

/*
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

To run this, you need to change some JVM settings.
Add these VM parameters:
--add-exports=java.base/jdk.internal.ref=ALL-UNNAMED
--add-exports=java.base/sun.nio.ch=ALL-UNNAMED
--add-exports=jdk.unsupported/sun.misc=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED
--add-opens=jdk.compiler/com.sun.tools.javac=ALL-UNNAMED
--add-opens=java.base/java.lang=ALL-UNNAMED
--add-opens=java.base/java.lang.reflect=ALL-UNNAMED
--add-opens=java.base/java.io=ALL-UNNAMED
--add-opens=java.base/java.util=ALL-UNNAMED

In IntelliJ IDEA, go to "Edit Configurations" (top right),
then inside click on "Modify Options" (small blue link on the right),
and choose "Add VM options".

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
*/

public class LongStorageOptimized {
  public static void main(String[] args) throws Exception {
    System.out.println(optimized());
  }

  private static long optimized() throws InterruptedException {
    ChronicleSet<Long> set = ChronicleSetBuilder.of(Long.class)
            .entries(30_000_000)
            .create();
    long result = sumNumbers(set);
    set.close(); // free off-heap memory
    return result;
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
