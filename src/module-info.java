module conta.sistema {

    requires javax.inject;
    requires spring.tx;

    // expondo porta de entrada (driver)
    exports conta.sistema.casouso.imp;
    exports conta.sistema.casouso.porta;

    // expondo sistema negocio
    exports conta.sistema.dominio.servico;
    exports conta.sistema.dominio.modelo;

    // expondo adaptadores de saídas (driven)
    exports conta.adaptador;
    exports conta.sistema.porta;

    //expondo testes
    exports teste.unidade.dominio.modelo;
    exports teste.unidade.dominio.servico;
    exports teste.casouso;

    //spring reflections
    opens conta.sistema.casouso.porta;
    opens conta.sistema.casouso.imp;
    opens conta.sistema.dominio.servico;
    opens conta.adaptador;
}