package com.gfa.memorymanagement.optimized.P01BloatedDataStructures;

import java.util.Random;

public class RandomizedMatrixOptimized {
  public static void main(String[] args) {
    optimized();
  }

  private static void optimized() {
    Random random = new Random();
    int[][] randomizedMatrix = new int[5000][5000];

    for (int y = 0; y < 5000; y++) {
      for (int x = 0; x < 5000; x++) {
        randomizedMatrix[y][x] = random.nextInt(100000);
      }
    }
  }
}
