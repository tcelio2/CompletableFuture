package br.com.completablefuture.gateway.impl;

import br.com.completablefuture.gateway.ConvenioGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ConvenioGatewayImpl implements ConvenioGateway {

    @Autowired
    private ForkJoinPool forkJoinPool;

    private static ExecutorService executorService = Executors.newFixedThreadPool(1);


    @Override
    public Future<String> consultarBeneficio() {
        demorar();
        return new AsyncResult<>("2000.00");
    }


    @Override
    public CompletableFuture<String> consultarBeneficio2() {
        return CompletableFuture.supplyAsync(this::getvalue, executorService);
    }

    private String getvalue() {
        demorar();
        return "2000.00";
    }
    @Async
    @Override
    public CompletableFuture<String> consultarBeneficio3() {
        return CompletableFuture.supplyAsync(this::getvalue);
    }

    private void demorar() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
