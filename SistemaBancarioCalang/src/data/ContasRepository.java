package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContasRepository {
    private final List<Conta> contas = new ArrayList<>();

    public void addConta(Conta conta) {
        contas.add(conta);
    }

    public Optional<Conta> getContaById(int id) {
        return contas.stream().filter(conta -> conta.getId() == id).findFirst();
    }

    public List<Conta> getAllContas() {
        return new ArrayList<>(contas);
    }

    public boolean updateConta(Conta updatedConta) {
        Optional<Conta> existingContaOpt = getContaById(updatedConta.getId());
        if (existingContaOpt.isPresent()) {
            Conta existingConta = existingContaOpt.get();
            existingConta.setName(updatedConta.getName());
            existingConta.setBalance(updatedConta.getBalance());
            return true;
        }
        return false;
    }

    public boolean deleteConta(int id) {
        return contas.removeIf(conta -> conta.getId() == id);
    }
}