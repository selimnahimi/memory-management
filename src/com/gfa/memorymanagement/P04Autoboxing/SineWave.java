package com.gfa.memorymanagement.P04Autoboxing;

import java.util.ArrayList;
import java.util.List;

public class SineWave {
    public static void main(String[] args) throws InterruptedException {
        unoptimized();
    }

    private static void unoptimized() throws InterruptedException {
        List<Double> signal = new ArrayList<>();

        for (double t = 0; t < 5000000; t += 0.3) {
            double noise = Math.random() * 0.001;
            double value = (1 + Math.sin(t)) * 10 + noise;
            signal.add(value);
        }

        for (double value : signal) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < 20; i++) {
                if ((int) value == i) {
                    line.append(".");
                } else {
                    line.append(" ");
                }
            }
            System.out.println(line);
            Thread.sleep(100);
        }
    }
}
