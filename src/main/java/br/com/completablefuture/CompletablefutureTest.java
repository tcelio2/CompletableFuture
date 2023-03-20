package br.com.completablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletablefutureTest {

    public static void main(String[] args) {
        Loja americanas = new Loja();
        Loja wallmart = new Loja();
        Loja submarino = new Loja();
        Loja amazon = new Loja();
        Loja alibaba = new Loja();

        long start = System.currentTimeMillis();
        Future<Double> americanasPrecoAssync = americanas.getPrecoAssync();
        Future<Double> wallmartPrecoAssync = wallmart.getPrecoAssync();
        Future<Double> submarinoPrecoAssync = submarino.getPrecoAssync();
        Future<Double> amazonPrecoAssync = amazon.getPrecoAssync();
        Future<Double> alibabaPrecoAssync = alibaba.getPrecoAssync();
        long end = System.currentTimeMillis();
        System.out.println("Tempo invocação: "+(end - start)+" ms");
        demorar();
        try {
            System.out.println("Tempo Americanas "+americanasPrecoAssync.get()+" ms");
            System.out.println("Tempo Wallmart "+wallmartPrecoAssync.get()+" ms");
            System.out.println("Tempo Submarino "+submarinoPrecoAssync.get()+" ms");
            System.out.println("Tempo Amazon "+amazonPrecoAssync.get()+" ms");
            System.out.println("Tempo Alibaba "+alibabaPrecoAssync.get()+" ms");
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tempo parao resultado final: "+(System.currentTimeMillis() - start)+" ms");
    }
    private static void demorar() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        Loja americanas = new Loja();
//        Loja wallmart = new Loja();
//        Loja submarino = new Loja();
//        Loja amazon = new Loja();
//        Loja alibaba = new Loja();
//
//        long start = System.currentTimeMillis();
//        System.out.println("Americanas: "+americanas.getPreco());
//        System.out.println("Wallmart: "+wallmart.getPreco());
//        System.out.println("Submarino: "+submarino.getPreco());
//        System.out.println("Amazon: "+amazon.getPreco());
//        System.out.println("Alibaba: "+alibaba.getPreco());
//        long end = System.currentTimeMillis();
//        System.out.println("Tempo passado: "+(end - start)+" ms");
//
//    }
}
