package br.ufpb.projetoLP;

import java.util.Objects;

public class Pedido {
    private int numeroPedido;
    private String nomeRefeicao;
    private String tipoRefeicao;
    private double precoRefeicao;
    private String tamanho;

    public static final String TIPO_INDIVIDUAL = "Individual";
    public static final String TIPO_CASAL = "Casal";
    public static final String TIPO_FAMILIA = "Familia";

    public static final String TIPO_ENTRADA = "Entrada";
    public static final String TIPO_PRATO_PRINCIPAL = "Prato Principal";
    public static final String TIPO_SOBREMESA = "Sobremesa";
    public static final String TIPO_BEBIDA = "Bebida";

    public Pedido(int numeroPedido, String nomeRefeicao, String tipoRefeicao, double precoRefeicao, String tamanho) {
        this.numeroPedido = numeroPedido;
        this.tipoRefeicao = tipoRefeicao;
        this.precoRefeicao = precoRefeicao;
        this.tamanho = tamanho;
        this.nomeRefeicao = nomeRefeicao;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }
    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }
    public String getTipoRefeicao() {
        return tipoRefeicao;
    }
    public void setTipoRefeicao(String tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }
    public double getPrecoRefeicao() {
        return precoRefeicao;
    }
    public void setPrecoRefeicao(double precoRefeicao) {
        this.precoRefeicao = precoRefeicao;
    }
    public String getTamanho() {
        return tamanho;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    public String getNomeRefeicao() {
        return nomeRefeicao;
    }
    public void setNomeRefeicao(String nomeRefeicao) {
        this.nomeRefeicao = nomeRefeicao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return numeroPedido == pedido.numeroPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroPedido);
    }

    @Override
    public String toString() {
        return "Pedido: " + "N.º " + numeroPedido + " " + nomeRefeicao + " R$ " + String.format("%.2f", precoRefeicao) +
                "\nTipo " + tipoRefeicao +
                "\nTamanho: " + tamanho;
    }
}
