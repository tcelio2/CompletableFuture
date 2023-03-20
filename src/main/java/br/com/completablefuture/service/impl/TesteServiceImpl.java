package br.com.completablefuture.service.impl;

import br.com.completablefuture.gateway.ConvenioGateway;
import br.com.completablefuture.gateway.PropostaGateway;
import br.com.completablefuture.service.TesteService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

@Service
public class TesteServiceImpl implements TesteService {

    @Autowired
    private ConvenioGateway convenioGateway;
    @Autowired
    private PropostaGateway propostaGateway;

    @Override
    public String calcularBeneficioFuture() {
        long start = System.currentTimeMillis();
        Future<List<String>> listFuture = propostaGateway.consultaPropostas();
        Future<String> convenio = convenioGateway.consultarBeneficio();
        long end = System.currentTimeMillis();
        try {
            List<String> strings = listFuture.get();
            System.out.println(convenio.get()+"--"+strings.get(0));
            System.out.println("Tempo de espera sem assincronia: "+(System.currentTimeMillis() - start)+" ms");
            return "OK";
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String calcularBeneficioCompletableFuture() {
        long start = System.currentTimeMillis();
        CompletableFuture<List<String>> listFuture = propostaGateway.consultaPropostas2();
        CompletableFuture<String> convenio = convenioGateway.consultarBeneficio2();
        long end = System.currentTimeMillis();
        try {
            CompletableFuture.allOf(listFuture, convenio).join();
            List<String> strings = listFuture.get();
            System.out.println(convenio.get()+"--"+strings.get(0));
            System.out.println("Tempo de espera Completable Future: "+(System.currentTimeMillis() - start)+" ms");
            return "OK";
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String calcularBeneficioCompletableFutureAndAssyncSpring() {
        long start = System.currentTimeMillis();
        CompletableFuture<List<String>> listFuture = propostaGateway.consultaPropostas3();
        CompletableFuture<String> convenio = convenioGateway.consultarBeneficio3();
        long end = System.currentTimeMillis();
        try {
            CompletableFuture.allOf(listFuture, convenio).join();
            List<String> strings = listFuture.get();
            System.out.println(convenio.get()+"--"+strings.get(0));
            System.out.println("Tempo de espera Completable Future + @Assync: "+(System.currentTimeMillis() - start)+" ms");
            return "OK";
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
