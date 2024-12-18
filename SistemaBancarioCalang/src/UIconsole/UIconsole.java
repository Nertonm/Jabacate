package UIconsole;

import java.util.Scanner;

import exceptions.ContaNotFoundException;
import service.ContaFacade;

import exceptions.InsufficientBalanceException;

public class UIconsole {
    private final ContaFacade contaFacade;
    private final Scanner scanner;

    public UIconsole(ContaFacade contaFacade) {
        this.contaFacade = contaFacade;
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar nova conta");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Verificar saldo");
            System.out.println("5. Listar contas");
            System.out.println("6. Deletar conta");
            System.out.println("7. Sair");
            int choice = Integer.parseInt(scanner.nextLine());
            try {
                switch (choice) {
                    case 1:
                        addConta();
                        break;
                    case 2:
                        depositar();
                        break;
                    case 3:
                        retirar();
                        break;
                    case 4:
                        verificarSaldo();
                        break;
                    case 5:
                        getAllContas();
                        break;
                    case 6:
                        deleteConta();
                        break;
                    case 7:
                        System.out.println("Obrigado por usar o sistema bancário.");
                        return;
                    default:
                        System.out.println("Opção Invalida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
            }
            catch (ContaNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (InsufficientBalanceException e) {
                System.out.println("Saldo insuficiente.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }
    }

    private void addConta() {
        System.out.print("Digite o nome do titular da conta: ");
        String name = scanner.nextLine();
        double balance = 0;
        contaFacade.addConta(name, balance);
        System.out.println("Conta criada para " + name);
    }

    private void depositar() {
        try {
            System.out.print("Digite o nome do titular da conta: ");
            String name = scanner.nextLine();
            System.out.print("Digite a quantia a ser depositada: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
            }
            if (contaFacade.depositar(name, amount)) {
                System.out.println("Depósito de R$" + amount + "realizado com sucesso.");
            } else {
                System.out.println("Conta não encontrada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número válido.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void retirar() {
        try {
            System.out.print("Digite o nome do titular da conta: ");
            String name = scanner.nextLine();
            System.out.print("Digite a quantia a ser retirada: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                throw new IllegalArgumentException("O valor da retirada deve ser positivo.");
            }
            try {
                if (contaFacade.retirar(name, amount)) {
                    System.out.println("Saque de R$" + amount + " realizada com sucesso.");
                } else {
                    System.out.println("Conta não encontrada.");
                }
            } catch (InsufficientBalanceException e) {
                System.out.println("Saldo insuficiente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número válido.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void verificarSaldo() {
        System.out.print("Digite o nome do titular da conta: ");
        String name = scanner.nextLine();
        double saldo = contaFacade.verificarSaldo(name);
        if (saldo >= 0) {
            System.out.println("Saldo da conta: R$" + saldo);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private void getAllContas() {
        contaFacade.getAllContas().forEach(conta ->
                System.out.println("Nome: " + conta.getName() + ", Saldo: R$" + conta.getBalance())
        );
    }

    private void deleteConta() {
        System.out.print("Digite o nome do titular da conta: ");
        String name = scanner.nextLine();
        if (contaFacade.deleteConta(name)) {
            System.out.println("Conta deletada com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
}
