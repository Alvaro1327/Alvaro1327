package br.ufpb.projetoLP;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ProgramaRestauranteGUI {

    private FuncionarioInterface funcionario;
    private PedidoInterface pedido;
    private JFrame mainFrame;

    public ProgramaRestauranteGUI() {
        this.funcionario = new FuncionarioList();
        this.pedido = new PedidoList();
        initializeMainFrame();
    }

    private void initializeMainFrame() {
        mainFrame = new JFrame("Sistema de Gerenciamento do Restaurante");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Sistema de Gerenciamento do Restaurante");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JButton pratosPedidosBtn = new JButton("Pratos e Pedidos");
        pratosPedidosBtn.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(pratosPedidosBtn, gbc);

        JButton funcionariosBtn = new JButton("Funcionários e Gerência");
        funcionariosBtn.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(funcionariosBtn, gbc);

        JButton dadosBtn = new JButton("Gerenciamento de Dados");
        dadosBtn.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(dadosBtn, gbc);

        JButton sairBtn = new JButton("Sair");
        sairBtn.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(sairBtn, gbc);

        pratosPedidosBtn.addActionListener(e -> showPratosPedidosMenu());
        funcionariosBtn.addActionListener(e -> showFuncionariosMenu());
        dadosBtn.addActionListener(e -> showDadosMenu());
        sairBtn.addActionListener(e -> {
            mainFrame.dispose();
            JOptionPane.showMessageDialog(null, "Saindo do programa...");
        });

        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    private void showPratosPedidosMenu() {
        JFrame frame = new JFrame("Pratos e Pedidos");
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton cadastrarBtn = new JButton("Cadastrar Prato");
        JButton apagarBtn = new JButton("Apagar Prato");
        JButton buscarNumeroBtn = new JButton("Buscar Prato Por Número");
        JButton buscarRefeicaoBtn = new JButton("Buscar Prato Por Refeição");
        JButton listarBtn = new JButton("Listar Pedidos");
        JButton voltarBtn = new JButton("Voltar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(cadastrarBtn, gbc);
        gbc.gridy = 1;
        panel.add(apagarBtn, gbc);
        gbc.gridy = 2;
        panel.add(buscarNumeroBtn, gbc);
        gbc.gridy = 3;
        panel.add(buscarRefeicaoBtn, gbc);
        gbc.gridy = 4;
        panel.add(listarBtn, gbc);
        gbc.gridy = 5;
        panel.add(voltarBtn, gbc);

        cadastrarBtn.addActionListener(e -> cadastrarPrato());
        apagarBtn.addActionListener(e -> apagarPrato());
        buscarNumeroBtn.addActionListener(e -> buscarPratoPorNumero());
        buscarRefeicaoBtn.addActionListener(e -> buscarPratoPorRefeicao());
        listarBtn.addActionListener(e -> listarPedidos());
        voltarBtn.addActionListener(e -> frame.dispose());

        frame.add(panel);
        frame.setVisible(true);
    }

    private void cadastrarPrato() {
        JFrame frame = new JFrame("Cadastrar Prato");
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField numeroField = new JTextField();
        JTextField nomeField = new JTextField();
        JComboBox<String> tipoRefeicaoCombo = new JComboBox<>(new String[]{"Entrada", "Prato Principal", "Sobremesa", "Bebida"});
        JTextField precoField = new JTextField();
        JComboBox<String> tamanhoCombo = new JComboBox<>(new String[]{"Individual", "Casal", "Família"});

        panel.add(new JLabel("Número do Pedido:"));
        panel.add(numeroField);
        panel.add(new JLabel("Nome da refeição:"));
        panel.add(nomeField);
        panel.add(new JLabel("Tipo da refeição:"));
        panel.add(tipoRefeicaoCombo);
        panel.add(new JLabel("Preço da refeição:"));
        panel.add(precoField);
        panel.add(new JLabel("Tamanho:"));
        panel.add(tamanhoCombo);

        JButton cadastrarBtn = new JButton("Cadastrar");
        JButton cancelarBtn = new JButton("Cancelar");

        cadastrarBtn.addActionListener(e -> {
            try {
                int numeroPedido = Integer.parseInt(numeroField.getText());
                if (pedido.getPedidos().stream().anyMatch(p -> p.getNumeroPedido() == numeroPedido)) {
                    JOptionPane.showMessageDialog(frame, "Número do pedido já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String nomeRefeicao = nomeField.getText();
                String tipoRefeicao = "";
                switch (tipoRefeicaoCombo.getSelectedIndex()) {
                    case 0: tipoRefeicao = Pedido.TIPO_ENTRADA; break;
                    case 1: tipoRefeicao = Pedido.TIPO_PRATO_PRINCIPAL; break;
                    case 2: tipoRefeicao = Pedido.TIPO_SOBREMESA; break;
                    case 3: tipoRefeicao = Pedido.TIPO_BEBIDA; break;
                }

                double precoRefeicao = Double.parseDouble(precoField.getText());
                String tamanho = "";
                switch (tamanhoCombo.getSelectedIndex()) {
                    case 0: tamanho = Pedido.TIPO_INDIVIDUAL; break;
                    case 1: tamanho = Pedido.TIPO_CASAL; break;
                    case 2: tamanho = Pedido.TIPO_FAMILIA; break;
                }

                Pedido novoPedido = new Pedido(numeroPedido, nomeRefeicao, tipoRefeicao, precoRefeicao, tamanho);
                pedido.cadastrarPedido(novoPedido);
                JOptionPane.showMessageDialog(frame, "Pedido cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Por favor, insira valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NumeroJaExisteException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarBtn.addActionListener(e -> frame.dispose());

        panel.add(cadastrarBtn);
        panel.add(cancelarBtn);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void apagarPrato() {
        String input = JOptionPane.showInputDialog(mainFrame, "Qual número do pedido que deseja cancelar:", "Cancelar Pedido", JOptionPane.QUESTION_MESSAGE);
        if (input == null) return;

        try {
            int numeroPedido = Integer.parseInt(input);
            boolean cancelou = pedido.cancelarPedido(numeroPedido);
            if (cancelou) {
                JOptionPane.showMessageDialog(mainFrame, "Pedido cancelado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Falha: Pedido não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumeroNaoEncontradoException e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPratoPorNumero() {
        String input = JOptionPane.showInputDialog(mainFrame, "Qual número do pedido que deseja procurar:", "Buscar Pedido", JOptionPane.QUESTION_MESSAGE);
        if (input == null) return;

        try {
            int numeroPedido = Integer.parseInt(input);
            Pedido pedidoAchado = pedido.buscarPedidoPorNumero(numeroPedido);
            JOptionPane.showMessageDialog(mainFrame, pedidoAchado.toString(), "Detalhes do Pedido", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumeroNaoEncontradoException e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPratoPorRefeicao() {
        JFrame frame = new JFrame("Buscar por Tipo de Refeição");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"Entrada", "Prato Principal", "Sobremesa", "Bebida"});
        JButton buscarBtn = new JButton("Buscar");
        JButton cancelarBtn = new JButton("Cancelar");

        panel.add(new JLabel("Selecione o tipo de refeição:"));
        panel.add(tipoCombo);
        panel.add(buscarBtn);
        panel.add(cancelarBtn);

        buscarBtn.addActionListener(e -> {
            String tipoRefeicao = "";
            switch (tipoCombo.getSelectedIndex()) {
                case 0: tipoRefeicao = Pedido.TIPO_ENTRADA; break;
                case 1: tipoRefeicao = Pedido.TIPO_PRATO_PRINCIPAL; break;
                case 2: tipoRefeicao = Pedido.TIPO_SOBREMESA; break;
                case 3: tipoRefeicao = Pedido.TIPO_BEBIDA; break;
            }

            try {
                List<Pedido> pedidosPorTipo = pedido.pedidosPorTipoRefeicao(tipoRefeicao);
                if (pedidosPorTipo.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nenhum pedido encontrado para o tipo de refeição: " + tipoRefeicao, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder sb = new StringBuilder("Pedidos encontrados:\n\n");
                    for (Pedido p : pedidosPorTipo) {
                        sb.append(p.toString()).append("\n\n");
                    }
                    JTextArea textArea = new JTextArea(sb.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(500, 400));
                    JOptionPane.showMessageDialog(frame, scrollPane, "Pedidos Encontrados", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (RefeicaoNaoEncontradaException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarBtn.addActionListener(e -> frame.dispose());

        frame.add(panel);
        frame.setVisible(true);
    }

    private void listarPedidos() {
        List<Pedido> listaPedidos = pedido.getPedidos();
        if (listaPedidos.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Nenhum pedido encontrado.", "Lista de Pedidos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder("Lista de Pedidos:\n\n");
            for (Pedido p : listaPedidos) {
                sb.append(p.toString()).append("\n\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 400));
            JOptionPane.showMessageDialog(mainFrame, scrollPane, "Lista de Pedidos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showFuncionariosMenu() {
        JFrame frame = new JFrame("Funcionários e Gerência");
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton contratarBtn = new JButton("Contratar Funcionário");
        JButton despedirBtn = new JButton("Despedir Funcionário");
        JButton listarBtn = new JButton("Listar Funcionários");
        JButton voltarBtn = new JButton("Voltar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(contratarBtn, gbc);
        gbc.gridy = 1;
        panel.add(despedirBtn, gbc);
        gbc.gridy = 2;
        panel.add(listarBtn, gbc);
        gbc.gridy = 3;
        panel.add(voltarBtn, gbc);

        contratarBtn.addActionListener(e -> contratarFuncionario());
        despedirBtn.addActionListener(e -> despedirFuncionario());
        listarBtn.addActionListener(e -> listarFuncionarios());
        voltarBtn.addActionListener(e -> frame.dispose());

        frame.add(panel);
        frame.setVisible(true);
    }

    private void contratarFuncionario() {
        JFrame frame = new JFrame("Contratar Funcionário");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField telefoneField = new JTextField();
        JComboBox<String> cargoCombo = new JComboBox<>(new String[]{"Garçom", "Cozinheiro", "Gerente"});

        panel.add(new JLabel("Nome do funcionário:"));
        panel.add(nomeField);
        panel.add(new JLabel("CPF (xxx.xxx.xxx-xx):"));
        panel.add(cpfField);
        panel.add(new JLabel("Telefone ((xx) 9xxxx-xxxx):"));
        panel.add(telefoneField);
        panel.add(new JLabel("Cargo:"));
        panel.add(cargoCombo);

        JButton contratarBtn = new JButton("Contratar");
        JButton cancelarBtn = new JButton("Cancelar");

        contratarBtn.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String telefone = telefoneField.getText();
                String cargo = "";
                switch (cargoCombo.getSelectedIndex()) {
                    case 0: cargo = Funcionario.TIPO_GARCOM; break;
                    case 1: cargo = Funcionario.TIPO_COZINHEIRO; break;
                    case 2: cargo = Funcionario.TIPO_GERENTE; break;
                }

                Funcionario novoFuncionario = new Funcionario(nome, cpf, telefone, cargo);
                funcionario.contratarFuncionario(novoFuncionario);
                JOptionPane.showMessageDialog(frame, "Funcionário contratado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } catch (FuncionarioJaExisteException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarBtn.addActionListener(e -> frame.dispose());

        panel.add(contratarBtn);
        panel.add(cancelarBtn);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void despedirFuncionario() {
        JFrame frame = new JFrame("Despedir Funcionário");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();

        panel.add(new JLabel("Nome do funcionário:"));
        panel.add(nomeField);
        panel.add(new JLabel("CPF do funcionário:"));
        panel.add(cpfField);

        JButton despedirBtn = new JButton("Despedir");
        JButton cancelarBtn = new JButton("Cancelar");

        despedirBtn.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                boolean cancelou = funcionario.despedirFuncionario(nome, cpf);
                if (cancelou) {
                    JOptionPane.showMessageDialog(frame, "Funcionário de nome " + nome + " e de CPF: " + cpf + ", demitido.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Falha no programa.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FuncionarioNaoExisteException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarBtn.addActionListener(e -> frame.dispose());

        panel.add(despedirBtn);
        panel.add(cancelarBtn);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void listarFuncionarios() {
        List<Funcionario> listaFuncionarios = funcionario.getListarFuncionario();
        if (listaFuncionarios.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Nenhum funcionário encontrado", "Lista de Funcionários", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder("Lista de Funcionários:\n\n");
            for (Funcionario f : listaFuncionarios) {
                sb.append(f.toString()).append("\n\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 400));
            JOptionPane.showMessageDialog(mainFrame, scrollPane, "Lista de Funcionários", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showDadosMenu() {
        JFrame frame = new JFrame("Gerenciamento de Dados");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton salvarBtn = new JButton("Salvar Dados");
        JButton recuperarBtn = new JButton("Recuperar Dados");
        JButton voltarBtn = new JButton("Voltar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(salvarBtn, gbc);
        gbc.gridy = 1;
        panel.add(recuperarBtn, gbc);
        gbc.gridy = 2;
        panel.add(voltarBtn, gbc);

        salvarBtn.addActionListener(e -> {
            try {
                funcionario.salvarDados();
                pedido.salvarDados();
                JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao salvar os dados. Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        recuperarBtn.addActionListener(e -> {
            try {
                funcionario.recuperarDados();
                pedido.recuperarDados();
                JOptionPane.showMessageDialog(frame, "Dados recuperados com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao recuperar os dados. Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        voltarBtn.addActionListener(e -> frame.dispose());

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProgramaRestauranteGUI());
    }
}
