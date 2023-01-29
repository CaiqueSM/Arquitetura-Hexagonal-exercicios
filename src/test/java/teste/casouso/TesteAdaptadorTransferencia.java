package teste.casouso;

import conta.sistema.casouso.porta.PortaTransferencia;
import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Caso de uso - serviço de transferência")
@ContextConfiguration(classes = build1.class)
@ExtendWith(SpringExtension.class)
public class TesteAdaptadorTransferencia {

    Integer contaCredito = 10;
    Integer contaDebito = 20;
    Integer contaInexistente = 30;
    BigDecimal cem = new BigDecimal(100);
    BigDecimal cinquenta = new BigDecimal(50);

    @Inject
    PortaTransferencia porta;

    @Test
    @DisplayName("Pesquisa conta com numero nulo")
    void testeIDNulo(){
        try {
            Conta conta = porta.getConta(null);
            assertTrue(conta == null, "Conta deve estar nula.");
        }catch (NegocioException e) {
           fail("A conta deve estar nula.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Pesquisa conta com numero inexistente")
    void testeIDInexistente(){
        try {
            Conta conta = porta.getConta(contaInexistente);
            assertTrue(conta == null, "Conta deve estar nula.");
        }catch (NegocioException e) {
            fail("A conta deve estar nula.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Pesquisa conta com numero existente")
    void testeIDExistente(){
        try {
            Conta conta = porta.getConta(contaCredito);
            assertTrue(conta != null, "A conta não deve estar nula.");
            System.out.println(conta);
        }catch (NegocioException e) {
            fail("A conta não deve estar nula.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta crédito obrigatória")
    void testeContaCreditoObrigatoria(){
        try {
            porta.transferir(contaDebito,null, cinquenta);
            fail("A conta de crédito obrigatória.");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta débito obrigatória")
    void testeContaDébitoObrigatoria(){
        try {
            porta.transferir(null, contaCredito, cinquenta);
            fail("A conta de débito obrigatória.");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta débito obrigatória")
    void testeValorObrigatorio(){
        try {
            porta.transferir(contaDebito, contaCredito, null);
            fail("Valor é obrigatório");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta débito inexistente")
    void testeContaDebitoInexistente(){
        try {
            porta.transferir(contaInexistente, contaCredito, cinquenta);
            fail("Conta débito é inexistente");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta débito é inexistente.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta crédito inexistente")
    void testeContaCreditoInexistente(){
        try {
            porta.transferir(contaDebito, contaInexistente, cinquenta);
            fail("Conta crédito é inexistente");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta crédito é inexistente.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta crédito e débito identicas")
    void testeContaCreditoDebitoIdenticas(){
        try {
            porta.transferir(contaDebito, contaDebito, cinquenta);
            fail("Conta crédito e débito devem ser diferentes");
        }catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta débito e crédito devem ser diferentes.");
            System.out.println(e.getMessage());
        }
    }
}
