package com.gfa.memorymanagement.optimized.P04Autoboxing;

public class SineWaveOptimized {
    public static void main(String[] args) throws InterruptedException {
        optimized();
    }

    private static void optimized() throws InterruptedException {
        class DoubleArrayList {
            double[] array;
            int pointer = 0;

            DoubleArrayList() {
                array = new double[2];
            }

            public void add(double num) {
                if (pointer + 1 == array.length) {
                    expand();
                }

                array[pointer++] = num;
            }

            private void expand() {
                double[] newArray = new double[array.length * 2];

                for (int i = 0; i < array.length; i++) {
                    newArray[i] = array[i];
                }

                array = newArray;
            }
        }

        DoubleArrayList signal = new DoubleArrayList();

        for (double t = 0; t < 5000000; t += 0.3) {
            double noise = Math.random() * 0.001;
            double value = (1 + Math.sin(t)) * 10 + noise;
            signal.add(value);
        }

        for (double value : signal.array) {
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
