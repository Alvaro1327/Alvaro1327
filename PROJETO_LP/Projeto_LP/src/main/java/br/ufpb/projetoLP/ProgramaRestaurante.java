package br.ufpb.projetoLP;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.*;

public class ProgramaRestaurante {
    public static void main(String[] args) {

        FuncionarioInterface funcionario = new FuncionarioList();
        PedidoInterface pedido = new PedidoList();

        boolean sair = false;
        while (!sair) {
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    "Digite uma opção:\n"
                            + "1. Pratos e Pedidos\n"
                            + "2. Funcionários e Gerência\n"
                            + "3. Gerenciamento de Dados\n"
                            + "0. Sair\n"));

            switch (opcao) {
                case 1:
                    int opcaoPratosPedidos = Integer.parseInt(JOptionPane.showInputDialog(
                            "Pratos e Pedidos:\n"
                                    + "1. Cadastrar Prato\n"
                                    + "2. Apagar Prato\n"
                                    + "3. Buscar Prato Por Número\n"
                                    + "4. Buscar Prato Por Refeição\n"
                                    + "5. Listar Pedidos\n"));
                    switch (opcaoPratosPedidos) {
                        case 1:
                            try {
                                int numeroPedido = Integer.parseInt(JOptionPane.showInputDialog("Número do Pedido: "));
                                if (pedido.getPedidos().stream().anyMatch(p -> p.getNumeroPedido() == numeroPedido)) {
                                    throw new NumeroJaExisteException("Número do pedido já existe!");
                                }
                                String nomeRefeicao = JOptionPane.showInputDialog("Nome da refeição: ");

                                String tipoRefeicao = "";
                                int tipoRefeicaoOpcao = Integer.parseInt(JOptionPane.showInputDialog(
                                        "Tipo da refeição: " +
                                                "\n1 - Entrada " +
                                                "\n2 - Prato Principal " +
                                                "\n3 - Sobremesa " +
                                                "\n4 - Bebida "));

                                switch (tipoRefeicaoOpcao) {
                                    case 1:
                                        tipoRefeicao = Pedido.TIPO_ENTRADA;
                                        break;
                                    case 2:
                                        tipoRefeicao = Pedido.TIPO_PRATO_PRINCIPAL;
                                        break;
                                    case 3:
                                        tipoRefeicao = Pedido.TIPO_SOBREMESA;
                                        break;
                                    case 4:
                                        tipoRefeicao = Pedido.TIPO_BEBIDA;
                                        break;
                                }
                                double precoRefeicao = Double.parseDouble(JOptionPane.showInputDialog("Qual valor dessa refeição: "));

                                String tamanho = "";
                                int tamanhoOpcao = Integer.parseInt(JOptionPane.showInputDialog(
                                        "Qual tipo de prato: " +
                                                "\n1 - Individual" +
                                                "\n2 - Casal" +
                                                "\n3 - Família"));

                                switch (tamanhoOpcao) {
                                    case 1:
                                        tamanho = Pedido.TIPO_INDIVIDUAL;
                                        break;
                                    case 2:
                                        tamanho = Pedido.TIPO_CASAL;
                                        break;
                                    case 3:
                                        tamanho = Pedido.TIPO_FAMILIA;
                                        break;
                                }

                                Pedido novoPedido = new Pedido(numeroPedido, nomeRefeicao, tipoRefeicao, precoRefeicao, tamanho);
                                pedido.cadastrarPedido(novoPedido);
                            } catch (NumeroJaExisteException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
                            }
                            break;
                        case 2:
                            try {
                                int NumeroPedidoCancelar = Integer.parseInt(JOptionPane.showInputDialog("Qual número do pedido que deseja cancelar: "));
                                boolean cancelou = pedido.cancelarPedido(NumeroPedidoCancelar);
                                if (cancelou) {
                                    JOptionPane.showMessageDialog(null, "Pedido cancelado com sucesso!");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Falha: Pedido não encontrado!");
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
                            } catch (NumeroNaoEncontradoException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                            break;
                        case 3:
                            try {
                                int numeroPedidoBuscar = Integer.parseInt(JOptionPane.showInputDialog("Qual número do pedido que deseja procurar: "));
                                Pedido pedidoAchado = pedido.buscarPedidoPorNumero(numeroPedidoBuscar);
                                JOptionPane.showMessageDialog(null, pedidoAchado.toString());
                            } catch (NumeroNaoEncontradoException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
                            }
                            break;
                        case 4:
                            try {
                                int opcaoBusca = Integer.parseInt(JOptionPane.showInputDialog(
                                        "Tipo da refeição: " +
                                                "\n1 - Entrada " +
                                                "\n2 - Prato Principal " +
                                                "\n3 - Sobremesa " +
                                                "\n4 - Bebida "));
                                String tipoRefeicaoBuscar = "";
                                switch (opcaoBusca) {
                                    case 1:
                                        tipoRefeicaoBuscar = Pedido.TIPO_ENTRADA;
                                        break;
                                    case 2:
                                        tipoRefeicaoBuscar = Pedido.TIPO_PRATO_PRINCIPAL;
                                        break;
                                    case 3:
                                        tipoRefeicaoBuscar = Pedido.TIPO_SOBREMESA;
                                        break;
                                    case 4:
                                        tipoRefeicaoBuscar = Pedido.TIPO_BEBIDA;
                                        break;
                                }

                                List<Pedido> pedidosPorTipo = pedido.pedidosPorTipoRefeicao(tipoRefeicaoBuscar);
                                if (pedidosPorTipo.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Nenhum pedido encontrado para o tipo de refeição: " + tipoRefeicaoBuscar);
                                } else {
                                    StringBuilder sb = new StringBuilder("Pedidos encontrados:\n");
                                    for (Pedido p : pedidosPorTipo) {
                                        sb.append(p.toString()).append("\n");
                                    }
                                    JOptionPane.showMessageDialog(null, sb.toString());
                                }
                            } catch (RefeicaoNaoEncontradaException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
                            }
                            break;
                        case 5:
                            List<Pedido> listaPedidos = pedido.getPedidos();
                            if (listaPedidos.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Nenhum pedido encontrado.");
                            } else {
                                StringBuilder sb = new StringBuilder("Lista de Pedidos:\n");
                                for (Pedido p : listaPedidos) {
                                    sb.append(p.toString()).append("\n");
                                }
                                JOptionPane.showMessageDialog(null, sb.toString());
                            }
                            break;
                    }
                    break;

                case 2:
                    int opcaoFuncionariosGerencia = Integer.parseInt(JOptionPane.showInputDialog(
                            "Funcionários e Gerência:\n"
                                    + "1. Contratar Funcionário\n"
                                    + "2. Despedir Funcionário\n"
                                    + "3. Listar Funcionários\n"));
                    switch (opcaoFuncionariosGerencia) {
                        case 1:
                            try {
                                String nomeFuncionario = JOptionPane.showInputDialog("Nome do funcionário: ");
                                String cpfFuncionario = JOptionPane.showInputDialog(
                                        "CPF do funcionário: \n" +
                                                "Formato: xxx.xxx.xxx-xx");
                                String numeroFuncionario = JOptionPane.showInputDialog(
                                        "N.º Telefone funcionário: \n" +
                                                "Formato: (xx) 9xxxx-xxxx");
                                String cargoFuncionario = "";
                                int cargoFuncionarioOpcao = Integer.parseInt(JOptionPane.showInputDialog(
                                        "Cargos existentes:" +
                                                "\n1 - Garçom" +
                                                "\n2 - Cozinheiro" +
                                                "\n3 - Gerente"));
                                switch (cargoFuncionarioOpcao) {
                                    case 1:
                                        cargoFuncionario = Funcionario.TIPO_GARCOM;
                                        break;
                                    case 2:
                                        cargoFuncionario = Funcionario.TIPO_COZINHEIRO;
                                        break;
                                    case 3:
                                        cargoFuncionario = Funcionario.TIPO_GERENTE;
                                        break;
                                }
                                Funcionario novoFuncionario = new Funcionario(nomeFuncionario, cpfFuncionario, numeroFuncionario, cargoFuncionario);
                                funcionario.contratarFuncionario(novoFuncionario);
                            } catch (FuncionarioJaExisteException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                String nomeCancelar = JOptionPane.showInputDialog("Insira o nome do funcionário que deseja demitir: ");
                                String cpfCancelar = JOptionPane.showInputDialog("Insira o CPF do funcionário que deseja demitir: ");
                                boolean cancelou = funcionario.despedirFuncionario(nomeCancelar, cpfCancelar);
                                if (cancelou) {
                                    JOptionPane.showMessageDialog(null, "Funcionário de nome " + nomeCancelar + " e de CPF: " + cpfCancelar + ", demitido.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Falha no programa.");
                                }
                            } catch (FuncionarioNaoExisteException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                            break;

                        case 3:
                            List<Funcionario> listaFuncionarios = funcionario.getListarFuncionario();
                            if (listaFuncionarios.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Nenhum funcionário encontrado");
                            } else {
                                StringBuilder sb = new StringBuilder("Lista de Funcionários:\n" +
                                        "\n");
                                for (Funcionario f : listaFuncionarios){
                                    sb.append(f.toString()).append("\n");
                                }
                                JOptionPane.showMessageDialog(null, sb.toString());
                            }
                            break;
                    }
                    break;

                case 3:
                    int opcaoDados = Integer.parseInt(JOptionPane.showInputDialog(
                            "Gerenciamento de Dados:\n"
                                    + "1. Salvar Dados\n"
                                    + "2. Recuperar Dados\n"));
                    switch (opcaoDados) {
                        case 1:
                            try {
                                funcionario.salvarDados();
                                pedido.salvarDados();
                                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso.");
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Erro ao salvar os dados. Tente novamente mais tarde.");
                            }
                            break;

                        case 2:
                            try {
                                funcionario.recuperarDados();
                                pedido.recuperarDados();
                                JOptionPane.showMessageDialog(null, "Dados recuperados com sucesso.");
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Erro ao recuperar os dados. Tente novamente mais tarde.");
                            }
                            break;
                    }
                    break;

                case 0:
                    sair = true;
                    JOptionPane.showMessageDialog(null, "Saindo do programa...");
                    break;
            }
        }
    }
}