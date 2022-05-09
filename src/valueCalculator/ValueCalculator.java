package valueCalculator;

public class ValueCalculator {
    private double[] numbers;
    private long size;
    private int half;

    public ValueCalculator(double[] numbers) {
        this.numbers = numbers;
        this.size = numbers.length;
        this.half = numbers.length / 2;
    }

    public void doCalc() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            numbers[i] = 3;
        }

        double[] a1 = new double[half];
        double[] a2 = new double[half];
        System.arraycopy(numbers, 0, a1, 0, half);
        System.arraycopy(numbers, half, a2, 0, half);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a1.length; i++) {
                    a1[i] = (float)(numbers[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a2.length; i++) {
                    a2[i] = (float)(numbers[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, numbers, 0, half);
        System.arraycopy(a2, 0, numbers, half, half);

        long finish = System.currentTimeMillis();
        System.out.println("time: " + (finish - start));
    }
}