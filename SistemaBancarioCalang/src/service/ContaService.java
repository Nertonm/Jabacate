package service;

import data.Conta;
import data.ContasRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.InsufficientBalanceException;

public class ContaService {
    private static final Logger logger = Logger.getLogger(ContaService.class.getName());
    private final ContasRepository contasRepository;

    public ContaService() {
        this.contasRepository = new ContasRepository();
    }

    public void addConta(String name, double balance) {
        Conta conta = new Conta(name, balance);
        contasRepository.addConta(conta);
        logger.log(Level.INFO, "Conta added: {0}", conta);
    }

    public List<Conta> getAllContas() {
        return contasRepository.getAllContas();
    }

    public boolean deleteConta(String name) {
        boolean result = contasRepository.deleteConta(name);
        if (result) {
            logger.log(Level.INFO, "Conta with name {0} deleted", name);
        } else {
            logger.log(Level.WARNING, "Failed to delete Conta with name {0}", name);
        }
        return result;
    }

    public boolean depositar(String name, double amount) {
        Optional<Conta> contaOpt = contasRepository.getContaByName(name);
        if (contaOpt.isPresent()) {
            Conta conta = contaOpt.get();
            conta.setBalance(conta.getBalance() + amount);
            logger.log(Level.INFO, "Deposited {0} to Conta with id {1}", new Object[]{amount, name});
            return true;
        }
        logger.log(Level.WARNING, "Failed to deposit {0} to Conta with id {1}", new Object[]{amount, name});
        return false;
    }

    public  boolean retirar(String name, double amount) {
        Optional<Conta> contaOpt = contasRepository.getContaByName(name);
        if (contaOpt.isPresent()) {
            Conta conta = contaOpt.get();
            if (conta.getBalance() >= amount) {
                conta.setBalance(conta.getBalance() - amount);
                logger.log(Level.INFO, "Withdrew {0} from Conta with name {1}", new Object[]{amount, name});
                return true;
            } else {
                logger.log(Level.WARNING, "Insufficient balance for Conta with name {0}",  name);
                throw new InsufficientBalanceException("Saldo insuficiente para a Conta com name " + name);
            }
        }
        logger.log(Level.WARNING, "Failed to withdraw {0} from Conta with name {1}", new Object[]{amount, name});
        return false;
    }

    public double verificarSaldo(String name) {
        Optional<Conta> contaOpt = contasRepository.getContaByName(name);
        if (contaOpt.isPresent()) {
            return contaOpt.get().getBalance();
        }
        logger.log(Level.WARNING, "Failed to verify balance for Conta with id {0}", name);
        return -1;
    }
}