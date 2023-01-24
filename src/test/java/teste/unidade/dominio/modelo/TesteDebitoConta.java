package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de Débito de Conta")
public class TesteDebitoConta {

    BigDecimal cem = new BigDecimal(100);
    Conta contaValida;

    @BeforeEach
    void preparar(){
        contaValida = new Conta(1, cem, "Test User");
    }

    @Test
    @DisplayName("valor débito nulo como obrigatório")
    public void TestParamDebitoNulo(){
        try {
            contaValida.debitar(null);
            fail("valor débito obrigatório");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("valor débito nulo como obrigatório")
    public void TestParamDebitoNegativo(){
        try {
            contaValida.debitar(new BigDecimal(-10));
            fail("valor débito deve ser maior que zero");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("valor débito nulo como obrigatório")
    public void TestParamDebitoZero(){
        try {
            contaValida.debitar(new BigDecimal(0));
            fail("valor débito não pode ser zero");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("valor débito acima do saldo")
    public void TestParamDebitoAcimaDoSaldo(){
        try {
            contaValida.debitar(cem.add(BigDecimal.ONE));
            fail("valor débito acima do saldo");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "saldo é insuficiente.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("valor débito valido")
    public void TestParamDebitoValido(){
        try {
            contaValida.debitar(cem);
            assertEquals(contaValida.getSaldo(), BigDecimal.ZERO, "O saldo corresponde.");
        }catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
    }

}
