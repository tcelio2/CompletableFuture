package br.com.completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Completablefuture2Test {

    public static void main(String[] args) {
        List<Loja> lojas = asList(
                new Loja("Americanas"),
                new Loja("Casas Bahia"),
                new Loja("Wallmart"),
                new Loja("Ponto Frio")
        );
        //acharPrecos(lojas);
        acharPrecos2(lojas);
        //acharPrecos3(lojas);
        acharPrecos4(lojas);
    }

    private static List<String> acharPrecos(List<Loja> lojas) {
        System.out.println("Stream sequencial");
        long start = System.currentTimeMillis();
        List<String> collect = lojas.stream().map(loja -> String.format("%S o preco eh: %.2f", loja.getNome(), loja.getPreco()))//getpreco sincrono
                .collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("Preco para pegar o resultado: "+(end - start)+ " ms");
        System.out.println(collect);
        return collect;
    }

    private static List<String> acharPrecos2(List<Loja> lojas) {
        System.out.println("Stream Paralelas");
        long start = System.currentTimeMillis();
        List<String> collect = lojas.parallelStream()
                .map(loja -> String.format("%S o preco eh: %.2f", loja.getNome(), loja.getPreco()))//getpreco sincrono
                .collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("Preco para pegar o resultado: "+(end - start)+ " ms");
        System.out.println(collect);
        return collect;
    }

    private static List<String> acharPrecos3(List<Loja> lojas) {
        System.out.println("Stream com Completablefuture");
        long start = System.currentTimeMillis();
        List<String> collect = lojas.stream()
                .map(loja -> CompletableFuture.supplyAsync(( ()
                        -> String.format("%S o preco eh: %.2f", loja.getNome(), loja.getPreco())
                        ))).map(CompletableFuture::join)
                .collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("Preco para pegar o resultado: "+(end - start)+ " ms");
        System.out.println(collect);
        return collect;
    }
    private static List<String> acharPrecos4(List<Loja> lojas) {
        System.out.println("Stream com Completablefuture dividido");
        long start = System.currentTimeMillis();
        List<CompletableFuture<String>> precoFuturo = lojas.stream()
                .map(loja -> CompletableFuture.supplyAsync(
                        () -> String.format("%S o preco eh: %.2f", loja.getNome(), loja.getPreco())
                )).collect(Collectors.toList());
        System.out.println("Tempo de invocação: "+(System.currentTimeMillis() - start)+ " ms");
        List<String> collect = precoFuturo.stream().map(CompletableFuture::join).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("Tempo final "+(end - start)+ " ms");
        System.out.println(collect);
        return collect;
    }
}
