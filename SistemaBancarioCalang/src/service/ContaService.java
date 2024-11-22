package service;

import data.Conta;
import data.ContasRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.InsufficientBalanceException;
import exceptions.ContaNotFoundException;

public class ContaService {
    private static final Logger logger = Logger.getLogger(ContaService.class.getName());
    private final ContasRepository contasRepository;

    public ContaService(ContasRepository contasRepository) {
        this.contasRepository = contasRepository;
    }

    public void addConta(Conta conta) {
        contasRepository.addConta(conta);
        logger.log(Level.INFO, "Conta added: {0}", conta);
    }

    public Optional<Conta> getContaById(int id) {
        Optional<Conta> contaOpt = contasRepository.getContaById(id);
        if (contaOpt.isEmpty()){
            logger.log(Level.WARNING, "Conta with id {0} not found", id);
            throw new ContaNotFoundException("Conta com id " + id + " não encontrada.");
        }
        return contaOpt;
    }

    public List<Conta> getAllContas() {
        return contasRepository.getAllContas();
    }

    public boolean updateConta(Conta updatedConta) {
        boolean result = contasRepository.updateConta(updatedConta);
        if (result) {
            logger.log(Level.INFO, "Conta updated: {0}", updatedConta);
        } else {
            logger.log(Level.WARNING, "Conta with id {0} not found", updatedConta.getId());
            throw new ContaNotFoundException("Conta com id " + updatedConta.getId() + " não encontrada.");
        }
        return result;
    }

    public boolean deleteConta(int id) {
        boolean result = contasRepository.deleteConta(id);
        if (result) {
            logger.log(Level.INFO, "Conta with id {0} deleted", id);
        } else {
            logger.log(Level.WARNING, "Failed to delete Conta with id {0}", id);
        }
        return result;
    }

    public boolean depositar(int id, double amount) {
        Optional<Conta> contaOpt = contasRepository.getContaById(id);
        if (contaOpt.isPresent()) {
            Conta conta = contaOpt.get();
            conta.setBalance(conta.getBalance() + amount);
            logger.log(Level.INFO, "Deposited {0} to Conta with id {1}", new Object[]{amount, id});
            return true;
        }
        logger.log(Level.WARNING, "Failed to deposit {0} to Conta with id {1}", new Object[]{amount, id});
        return false;
    }

    public  boolean retirar(int id, double amount) {
        Optional<Conta> contaOpt = contasRepository.getContaById(id);
        if (contaOpt.isPresent()) {
            Conta conta = contaOpt.get();
            if (conta.getBalance() >= amount) {
                conta.setBalance(conta.getBalance() - amount);
                logger.log(Level.INFO, "Withdrew {0} from Conta with id {1}", new Object[]{amount, id});
                return true;
            } else {
                logger.log(Level.WARNING, "Insufficient balance for Conta with id {0}", id);
                throw new InsufficientBalanceException("Saldo insuficiente para a Conta com id " + id);
            }
        }
        logger.log(Level.WARNING, "Failed to withdraw {0} from Conta with id {1}", new Object[]{amount, id});
        return false;
    }

    public double verificarSaldo(int id) {
        Optional<Conta> contaOpt = contasRepository.getContaById(id);
        if (contaOpt.isPresent()) {
            return contaOpt.get().getBalance();
        }
        logger.log(Level.WARNING, "Failed to verify balance for Conta with id {0}", id);
        return -1;
    }
}