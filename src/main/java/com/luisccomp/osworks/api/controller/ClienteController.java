package com.luisccomp.osworks.api.controller;

import com.luisccomp.osworks.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {
    @GetMapping("/clientes")
    public List<Cliente> listar() {
        var cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("João das Couves");
        cliente1.setEmail("joaodascouves@algaworks.com");
        cliente1.setTeleofne("34 99999-1111");

        var cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Maria da Silva");
        cliente2.setEmail("mariadasilva@algaworks.com");
        cliente2.setTeleofne("34 77777-1111");

        return Arrays.asList(cliente1, cliente2);
    }
}
