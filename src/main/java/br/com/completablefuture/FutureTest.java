package br.com.completablefuture;

import java.util.concurrent.*;

public class FutureTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    public static void main(String[] args) {
        Future<Double> future = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 2000D;
        });
        enrolar();
        try {
            Double resultado = future.get();
            System.out.println("Resultado: "+resultado);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }
    private static void enrolar() {
        long soma = 0;
        for (int i = 0; i < 1_000_000; i++) {
            soma += 1;
        }
        System.out.println(soma);
    }
}
