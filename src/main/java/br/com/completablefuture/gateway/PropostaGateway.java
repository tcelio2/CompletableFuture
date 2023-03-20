package br.com.completablefuture.gateway;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface PropostaGateway {

    Future<List<String>> consultaPropostas();

    CompletableFuture<List<String>> consultaPropostas2();

    CompletableFuture<List<String>> consultaPropostas3();
}
