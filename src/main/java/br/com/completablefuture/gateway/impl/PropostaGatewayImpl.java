package br.com.completablefuture.gateway.impl;

import br.com.completablefuture.gateway.PropostaGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Component
public class PropostaGatewayImpl implements PropostaGateway {
    @Autowired
    private ForkJoinPool forkJoinPool;
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Override
    public Future<List<String>> consultaPropostas() {
        demorar();
        return new AsyncResult<>(Arrays.asList("98871", "982212", "983311"));
    }
    @Override
    public CompletableFuture<List<String>> consultaPropostas2() {
        return CompletableFuture.supplyAsync(() -> getValue("2"), executorService);
    }
    @Async
    @Override
    public CompletableFuture<List<String>> consultaPropostas3() {
        return CompletableFuture.supplyAsync(() -> getValue("2"));
    }

    private List<String> getValue(String id) {
        demorar();
        return Arrays.asList("98871", "982212", "983311", id);
    }

    private void demorar() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
