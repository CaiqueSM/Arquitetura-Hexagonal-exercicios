package conta.sistema.dominio.modelo;

import java.math.BigDecimal;

import static conta.sistema.dominio.modelo.Erro.obrigatorio;
import static conta.sistema.dominio.modelo.Erro.saldoInsuficiente;
import static java.util.Objects.isNull;

public class Conta {
    private Integer numero;
    private BigDecimal saldo;
    private String correntista;

    public Conta() {
        this.numero = 0;
        this.saldo = BigDecimal.ZERO;
        this.correntista = "Não informado.";
    }

    public Conta(Integer numero, BigDecimal saldo, String correntista) {
        this.numero = numero;
        this.saldo = saldo;
        this.correntista = correntista;
    }

    public void creditar(BigDecimal credito) throws NegocioException {
        if (isNull(credito)){
            obrigatorio("Valor crédito");
        }

        if (credito.compareTo(BigDecimal.ZERO) <= 0){
            obrigatorio("Valor crédito");
        }

        saldo = saldo.add(credito);
    }

    public void debitar(BigDecimal debito) throws NegocioException {
        if (isNull(debito)){
            obrigatorio("Valor debito");
        }

        if (debito.compareTo(BigDecimal.ZERO) <= 0){
            obrigatorio("Valor debito");
        }

        if (debito.compareTo(debito) > 0){
            saldoInsuficiente();
        }

        saldo = saldo.subtract(debito);
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getCorrentista() {
        return correntista;
    }

    public void setCorrentista(String correntista) {
        this.correntista = correntista;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", saldo=" + saldo +
                ", correntista='" + correntista + '\'' +
                '}';
    }
}
