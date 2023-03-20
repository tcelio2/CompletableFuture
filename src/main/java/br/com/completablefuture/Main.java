package br.com.completablefuture;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        long num = 10_000_000_00;
        somar(num);
//        somarStreamSimples(num);
//        somarStreamParallel(num);
        somarRangeClosedStream(num);
        somarRangeClosedParrallel(num);
        System.out.println(Runtime.getRuntime().availableProcessors());
        //System.setProperty("java.util.concurrent.ForkJoinPool.parallelism", "12");
    }

    private static void somar(long num) {
        System.out.println("Soma for");
        long inicio = System.currentTimeMillis();
        long result = 0;
        for (int i = 0; i < num; i++) {
            result += 1;
        }
        long fim = System.currentTimeMillis();
        System.out.println("Tempo passado: "+(fim - inicio)+" ms");
    }

    //esse tipo de codigo nao é um bom exemplo para usar parallel
    //nao temos todos os valores para separar e processar
    //o tempo que ele demora para se organizar acaba levando muito tempo
    //tem o tempo de boxing e unboxing de atributo prinitivo e nao primtivo
    private static void somarStreamSimples(long num) {
        System.out.println("Soma Stream Simples");
        long inicio = System.currentTimeMillis();
        long result = 0;
        result = Stream.iterate(1L, i -> i + 1).limit(num).reduce(0L, Long::sum);
        long fim = System.currentTimeMillis();
        System.out.println("Tempo passado: "+(fim - inicio)+" ms");
    }

    private static void somarStreamParallel(long num) {
        System.out.println("Soma Stream Parallel");
        long inicio = System.currentTimeMillis();
        long result = 0;
        result = Stream.iterate(1L, i -> i + 1).limit(num).parallel().reduce(0L, Long::sum);
        long fim = System.currentTimeMillis();
        System.out.println("Tempo passado: "+(fim - inicio)+" ms");
    }

    //ele primeio vai qebrar os valores para depois iterar
    //usando o rangeClosed
    private static void somarRangeClosedStream(long num) {
        System.out.println("Soma Range Closed Stream");
        long inicio = System.currentTimeMillis();
        long result = 0;
        result = LongStream.rangeClosed(1L, num).reduce(0L, Long::sum);
        long fim = System.currentTimeMillis();
        System.out.println("Tempo passado: "+(fim - inicio)+" ms");
    }

    private static void somarRangeClosedParrallel(long num) {
        System.out.println("Soma Range Closed Parallel");
        long inicio = System.currentTimeMillis();
        long result = 0;
        result = LongStream.rangeClosed(1L, num).parallel().reduce(0L, Long::sum);
        long fim = System.currentTimeMillis();
        System.out.println("Tempo passado: "+(fim - inicio)+" ms");
    }
}