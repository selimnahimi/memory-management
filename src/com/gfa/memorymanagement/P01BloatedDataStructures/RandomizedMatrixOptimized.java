package com.gfa.memorymanagement.P01BloatedDataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomizedMatrixOptimized {
  public static void main(String[] args) {
    optimized();
    // unoptimized();
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

  private static void unoptimized() {
    Random random = new Random();
    List<List<Integer>> randomizedMatrix = new ArrayList<>();

    for (int y = 0; y < 5000; y++) {
      randomizedMatrix.add(new ArrayList<>());

      for (int x = 0; x < 5000; x++) {
        randomizedMatrix.get(y).add(
                random.nextInt(100000)
        );
      }
    }
  }
}
