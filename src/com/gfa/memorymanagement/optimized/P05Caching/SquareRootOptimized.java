package com.gfa.memorymanagement.optimized.P05Caching;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.ArrayList;
import java.util.List;

public class SquareRootOptimized
{
  public static double square(double number){
    double t;

    double squareroot = number / 2;

    do {
      t = squareroot;
      squareroot = (t + (number / t)) / 2;
    } while ((t - squareroot) != 0);

    return squareroot;
  }

  public static void main(String[] args) {
    optimized();
  }

  private static void optimized() {
    CacheLoader<Double, Double> rootLoader = new CacheLoader<>() {
      @Override
      public Double load(Double key) {
        return square(key);
      }
    };

    LoadingCache<Double, Double> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .build(rootLoader);

    List<Integer> numbers = new ArrayList<>();

    for (int i = 0; i < 1000000; i++) {
      numbers.add((int) (1 + Math.random() * 1000));
    }

    for (double number : numbers) {
      System.out.println("Square root of " + number + ": " + cache.getUnchecked(number));
    }
  }
}