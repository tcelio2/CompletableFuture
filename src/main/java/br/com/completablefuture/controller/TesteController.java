package br.com.completablefuture.controller;


import br.com.completablefuture.service.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class TesteController {

    @Autowired
    private TesteService testeService;

    @GetMapping("beneficio/{id}")
    public String calcularBeneficio(@PathVariable("id") Integer id) {
            testeService.calcularBeneficioFuture();
            testeService.calcularBeneficioCompletableFuture();
            testeService.calcularBeneficioCompletableFutureAndAssyncSpring();
        return "OK";
    }
}
