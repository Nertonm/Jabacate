package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContasRepository {
    private final List<Conta> contas = new ArrayList<>();

    public void addConta(Conta conta) {
        contas.add(conta);
    }

    public Optional<Conta> getContaByName(String name) {
        return contas.stream().filter(conta -> conta.getName().equalsIgnoreCase(name)).findFirst();
    }

    public List<Conta> getAllContas() {
        return new ArrayList<>(contas);
    }

    public boolean deleteConta(String name) {
        return contas.removeIf(conta -> conta.getName().equalsIgnoreCase(name));
    }
}