package br.com.completablefuture.gateway;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface ConvenioGateway {

    Future<String> consultarBeneficio();

    CompletableFuture<String> consultarBeneficio2();

    CompletableFuture<String> consultarBeneficio3();
}
