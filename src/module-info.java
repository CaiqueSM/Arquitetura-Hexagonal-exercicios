module conta.sistema {

    requires javax.inject;
    requires spring.tx;

    // expondo porta de entrada (driver)
    exports conta.sistema.casouso.imp;

    // expondo sistema negocio
    exports conta.sistema.dominio.servico;

    // expondo adaptadores de saídas (driven)
    exports conta.adaptador;

    //expondo testes
    exports teste.unidade.dominio.modelo;
    exports teste.unidade.dominio.servico;
    exports teste.casouso;
}