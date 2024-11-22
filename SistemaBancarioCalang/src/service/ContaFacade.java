package service;

import data.Conta;
import exceptions.InsufficientBalanceException;

import java.util.List;


public class ContaFacade {
    private final ContaService contaService;

    public ContaFacade(ContaService contaService) {
        this.contaService = contaService;
    }

    public void addConta(String name, double balance) {
        contaService.addConta(name,  balance);
    }

    public List<Conta> getAllContas() {
        return contaService.getAllContas();
    }

    public boolean deleteConta(String name) {
        return contaService.deleteConta(name);
    }

    public boolean depositar(String name, double amount) {
        return contaService.depositar(name, amount);
    }

    public boolean retirar(String name, double amount) throws InsufficientBalanceException {
        return contaService.retirar(name, amount);
    }

    public double verificarSaldo(String name) {
        return contaService.verificarSaldo(name);
    }
}