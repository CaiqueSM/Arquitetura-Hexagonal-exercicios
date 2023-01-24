package teste.unidade.dominio.servico;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import conta.sistema.dominio.servico.Transferencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de Transferencia")
public class TesteTransferencia {

    BigDecimal cem = new BigDecimal(100);
    BigDecimal vinte = new BigDecimal(20);
    Conta contaDebito;
    Conta contaCredito;
    Transferencia transferencia;

    @BeforeEach
    void preparar(){
        contaDebito = new Conta(1, cem, "Test User 01");
        contaCredito = new Conta(2, cem, "Test User 02");
        transferencia = new Transferencia();
    }

    @Test
    @DisplayName("valor nulo como obrigatório")
    public void TestParamValorNulo(){
        try {
            transferencia.transferencia(null, contaDebito, contaCredito);
            fail("valor transferência obrigatório");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor da transferência é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta de Débito como obrigatório")
    public void TestParamContaDebitoNulo(){
        try {
            transferencia.transferencia(vinte, null, contaCredito);
            fail("conta de débito obrigatório");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta de Crédito como obrigatório")
    public void TestParamContaCreditoNulo(){
        try {
            transferencia.transferencia(vinte, contaDebito, null);
            fail("conta de crédito obrigatório");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Transferência 20 reais")
    public void TestTransferenciaValida(){
        try {
            transferencia.transferencia(vinte, contaDebito, contaCredito);
            assertEquals(contaDebito.getSaldo(), cem.subtract(vinte), "Saldo confere");
            assertEquals(contaCredito.getSaldo(), cem.add(vinte), "Saldo confere");
        }catch (NegocioException e) {
            fail("Deve transferir com sucesso");
            System.out.println(e.getMessage());
        }
    }
}
