import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import conta.bancaria.ContaBancaria;


public class App {
    private static int ultimoNumeroConta = 0;

    public static void main(String[] args) {
        Map<String, ContaBancaria> contas = new HashMap<>();
        boolean sair = false;
        Scanner sc = new Scanner(System.in);

        while (!sair) {
            System.out.println("\n----- Menu -----");
            System.out.println("1. Criar nova conta");
            System.out.println("2. Acessar conta existente");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                
                case 1:
                    String numeroConta = gerarNumeroConta();
                    System.out.print("Digite a senha da nova conta: ");
                    String senhaConta = sc.next();
                    contas.put(numeroConta, new ContaBancaria(numeroConta, senhaConta));
                    System.out.println("Nova conta criada com sucesso! Número da conta: " + numeroConta);
                    break;

                case 2:
                    System.out.print("Digite o número da conta: ");
                    String numeroContaAtual = sc.next();
                    ContaBancaria conta = contas.get(numeroContaAtual);

                    if (conta != null) {
                        System.out.print("Digite a senha da conta: ");
                        String senhaContaAtual = sc.next();
                        if (conta.verificarSenha(senhaContaAtual)) {
                            boolean sairConfig = false;

                            while (!sairConfig) { 
                                System.out.println("\n----- Menu da Conta " + numeroContaAtual + " -----");
                                System.out.println("1. Depositar");
                                System.out.println("2. Sacar");
                                System.out.println("3. Consultar Saldo");
                                System.out.println("4. Voltar ao menu principal");
                                System.out.print("Escolha uma opção: ");
                                int opcaoConta = sc.nextInt();
                                
                                switch(opcaoConta) {
                                    case 1:
                                        System.out.print("Digite o valor a ser depositado: ");
                                        double valorDeposito = sc.nextDouble();
                                        conta.depositar(valorDeposito);
                                        break;

                                    case 2:
                                        System.out.print("Digite o valor a ser sacado: ");
                                        double valorSacar = sc.nextDouble();
                                        conta.sacar(valorSacar);
                                        break;

                                    case 3:
                                        double saldoAtual = conta.consultarSaldo();
                                        System.out.println("Saldo atual: R$" + saldoAtual);
                                        break;
                                    
                                    case 4:
                                        sairConfig = true;
                                        break;

                                    default:
                                        System.out.println("Opção inválida. Tente novamente.");

                                }

                            }
                        } else {
                            System.out.println("Senha incorreta. Acesso não autorizado.");
                        }
                    } else {
                        System.out.println("Conta não encontrada...");
                    }

                    break;
                
                case 3:
                    System.out.println("Saindo... \n");
                    sair = true;
                    sc.close();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                }

        }
    }

    private static String gerarNumeroConta() {
        ultimoNumeroConta++;
        return String.format("%06d", ultimoNumeroConta);
    }
}