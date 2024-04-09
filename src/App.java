import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import conta.bancaria.ContaBancaria;


public class App {
    private static int ultimoNumeroConta = 0;

    public static void main(String[] args) {
        Map<String, ContaBancaria> contas = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n----- Menu -----");
            System.out.println("1. Criar nova conta");
            System.out.println("2. Acessar conta existente");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();

            if (opcao == 1) {
                String numeroConta = gerarNumeroConta();
                System.out.print("Digite a senha da nova conta: ");
                String senhaConta = sc.next();
                contas.put(numeroConta, new ContaBancaria(numeroConta, senhaConta));
                System.out.println("Nova conta criada com sucesso! Número da conta: " + numeroConta);
            } else if (opcao == 2) {
                System.out.print("Digite o número da conta: ");
                String numeroConta = sc.next();
                ContaBancaria conta = contas.get(numeroConta);
                if (conta != null) {
                    System.out.print("Digite a senha da conta: ");
                    String senhaConta = sc.next();
                    if (conta.verificarSenha(senhaConta)) {
                        while (true) {
                            System.out.println("\n----- Menu da Conta " + numeroConta + " -----");
                            System.out.println("1. Depositar");
                            System.out.println("2. Sacar");
                            System.out.println("3. Consultar Saldo");
                            System.out.println("4. Voltar ao menu principal");
                            System.out.print("Escolha uma opção: ");
                            int opcaoConta = sc.nextInt();

                            if (opcaoConta == 1) {
                                System.out.print("Digite o valor a ser depositado: ");
                                double valor = sc.nextDouble();
                                conta.depositar(valor);
                            } else if (opcaoConta == 2) {
                                System.out.print("Digite o valor a ser sacado: ");
                                double valor = sc.nextDouble();
                                conta.sacar(valor);
                            } else if (opcaoConta == 3) {
                                double saldoAtual = conta.consultarSaldo();
                                System.out.println("Saldo atual: R$" + saldoAtual);
                            } else if (opcaoConta == 4) {
                                break;
                            } else {
                                System.out.println("Opção inválida. Tente novamente.");
                            }
                        }
                    } else {
                        System.out.println("Senha incorreta. Acesso não autorizado.");
                    }
                } else {
                    System.out.println("Conta não encontrada.");
                }
            } else if (opcao == 3) {
                System.out.println("Encerrando o programa...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close();
    }

    private static String gerarNumeroConta() {
        ultimoNumeroConta++;
        return String.format("%06d", ultimoNumeroConta);
    }
}