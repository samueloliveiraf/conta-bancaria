package conta.bancaria;


public class ContaBancaria {
    private String numeroConta;
    private double saldo;
    private String senhaConta;

    public ContaBancaria(String numeroConta, String senhaConta) {
        this.numeroConta = numeroConta;
        this.saldo = 0.0;
        this.senhaConta = senhaConta;
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito realizado: R$" + valor);
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado: R$" + valor);
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    public double consultarSaldo() {
        return saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public boolean verificarSenha(String senha) {
        return senha.equals(senhaConta);
    }
}
