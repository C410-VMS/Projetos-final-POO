package GuildaGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Exceptions.*;
import inventario.*;
import personagens.*;

public class TelaCriacaoPersonagem extends JFrame {
    private static final long serialVersionUID = 1L;

    private static class RegistroPersonagem {
        final Personagem personagem;
        final Inventario inventario;
        final List<Item> itensMochila;

        RegistroPersonagem(Personagem personagem) {
            this.personagem = personagem;
            this.inventario = new Inventario();
            this.itensMochila = new ArrayList<>();
        }
    }

    private JTextField txtNome;
    private JComboBox<String> cbClasse;
    private JTextField txtForca;
    private JTextField txtInteligencia;
    private JTextField txtDestreza;
    private JTextField txtVida;

    private JTable tabelaPersonagens;
    private DefaultTableModel modeloTabela;
    private final List<RegistroPersonagem> registros;

    private JPanel painelInventarioContainer;
    private TitledBorder bordaInventario;
    private JComboBox<String> cbTipoItem;
    private JTextField txtNomeItem;
    private JComboBox<Raridade> cbRaridade;
    private JLabel lblValorItem;
    private JTextField txtValorItem;
    private JComboBox<String> cbSlotArmadura;
    private JButton btnAdicionarItem;

    private JLabel lblArmaEquipada;
    private JLabel lblSlotCabeca;
    private JLabel lblSlotPeito;
    private JLabel lblSlotPernas;
    private JButton btnDesequiparArma;
    private JButton btnDesequiparCabeca;
    private JButton btnDesequiparPeito;
    private JButton btnDesequiparPernas;

    private JList<String> listaMochila;
    private DefaultListModel<String> modeloListaMochila;
    private JButton btnUsarEquiparItem;
    private JButton btnRemoverItem;

    public TelaCriacaoPersonagem() {
        this.registros = new ArrayList<>();
        inicializarInterface();
        atualizarPainelInventario();
    }

    private void inicializarInterface() {
        setTitle("Sistema de Criação, Registro e Inventário de Personagens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1120, 680);
        setMinimumSize(new Dimension(960, 600));
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(new EmptyBorder(12, 12, 12, 12));
        setContentPane(painelPrincipal);

        JLabel lblTitulo = new JLabel("Gestão de Personagens e Inventário");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JSplitPane splitDireita = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                criarPainelRegistro(),
                criarPainelInventario()
        );
        splitDireita.setDividerLocation(240);
        splitDireita.setResizeWeight(0.4);

        JSplitPane splitPrincipal = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                criarPainelFormulario(),
                splitDireita
        );
        splitPrincipal.setDividerLocation(310);
        splitPrincipal.setResizeWeight(0.28);

        painelPrincipal.add(splitPrincipal, BorderLayout.CENTER);
    }

    private JPanel criarPainelFormulario() {
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setBorder(BorderFactory.createTitledBorder("Novo Personagem"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        painelForm.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        txtNome = new JTextField(15);
        painelForm.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        painelForm.add(new JLabel("Classe:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        cbClasse = new JComboBox<>(new String[] { "Arcano", "Arqueiro", "Guerreiro" });
        painelForm.add(cbClasse, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        painelForm.add(new JLabel("Força:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        txtForca = new JTextField(10);
        painelForm.add(txtForca, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.3;
        painelForm.add(new JLabel("Inteligência:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        txtInteligencia = new JTextField(10);
        painelForm.add(txtInteligencia, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.3;
        painelForm.add(new JLabel("Destreza:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        txtDestreza = new JTextField(10);
        painelForm.add(txtDestreza, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.3;
        painelForm.add(new JLabel("Vida:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        txtVida = new JTextField(10);
        painelForm.add(txtVida, gbc);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        JButton btnLimpar = new JButton("Limpar");
        JButton btnCriar = new JButton("Criar Personagem");

        btnLimpar.addActionListener(e -> limparCamposPersonagem());
        btnCriar.addActionListener(e -> registrarPersonagem());

        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnCriar);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        gbc.insets = new Insets(14, 6, 6, 6);
        painelForm.add(painelBotoes, gbc);

        gbc.gridy = 7; gbc.weighty = 1.0;
        painelForm.add(new JPanel(), gbc);

        return painelForm;
    }

    private JPanel criarPainelRegistro() {
        JPanel painelRegistro = new JPanel(new BorderLayout(6, 6));
        painelRegistro.setBorder(BorderFactory.createTitledBorder("Personagens Registrados (selecione para gerenciar o inventário)"));

        String[] colunas = {
                "Nome", "Classe", "Força", "Inteligência", "Destreza", "Vida", "Poder Combate", "Itens na Mochila"
        };

        modeloTabela = new DefaultTableModel(colunas, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaPersonagens = new JTable(modeloTabela);
        tabelaPersonagens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPersonagens.getTableHeader().setReorderingAllowed(false);

        tabelaPersonagens.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                atualizarPainelInventario();
            }
        });

        painelRegistro.add(new JScrollPane(tabelaPersonagens), BorderLayout.CENTER);

        JPanel painelAcoesTabela = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRemover = new JButton("Remover Personagem");
        btnRemover.addActionListener(e -> removerPersonagemSelecionado());
        painelAcoesTabela.add(btnRemover);

        painelRegistro.add(painelAcoesTabela, BorderLayout.SOUTH);
        return painelRegistro;
    }

    private JPanel criarPainelInventario() {
        painelInventarioContainer = new JPanel(new BorderLayout(10, 10));
        bordaInventario = BorderFactory.createTitledBorder("Inventário");
        painelInventarioContainer.setBorder(bordaInventario);

        JPanel painelNovoItem = new JPanel(new GridBagLayout());
        painelNovoItem.setBorder(BorderFactory.createTitledBorder("Adicionar Item à Mochila"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 5, 4, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        cbTipoItem = new JComboBox<>(new String[] { "Arma", "Armadura", "Poção" });
        txtNomeItem = new JTextField(12);
        cbRaridade = new JComboBox<>(Raridade.values());
        lblValorItem = new JLabel("Dano:");
        txtValorItem = new JTextField(6);
        cbSlotArmadura = new JComboBox<>(new String[] { "0 - Cabeça", "1 - Peito", "2 - Pernas" });
        cbSlotArmadura.setEnabled(false);
        btnAdicionarItem = new JButton("Adicionar");

        cbTipoItem.addActionListener(e -> atualizarCamposTipoItem());
        btnAdicionarItem.addActionListener(e -> adicionarItemAoSelecionado());

        gbc.gridx = 0; gbc.gridy = 0;
        painelNovoItem.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        painelNovoItem.add(cbTipoItem, gbc);

        gbc.gridx = 2;
        painelNovoItem.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        painelNovoItem.add(txtNomeItem, gbc);

        gbc.gridx = 4; gbc.weightx = 0;
        painelNovoItem.add(new JLabel("Raridade:"), gbc);
        gbc.gridx = 5;
        painelNovoItem.add(cbRaridade, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        painelNovoItem.add(lblValorItem, gbc);
        gbc.gridx = 1;
        painelNovoItem.add(txtValorItem, gbc);

        gbc.gridx = 2;
        painelNovoItem.add(new JLabel("Slot Armadura:"), gbc);
        gbc.gridx = 3;
        painelNovoItem.add(cbSlotArmadura, gbc);

        gbc.gridx = 4; gbc.gridwidth = 2;
        painelNovoItem.add(btnAdicionarItem, gbc);

        painelInventarioContainer.add(painelNovoItem, BorderLayout.NORTH);

        JPanel painelCentro = new JPanel(new GridLayout(1, 2, 10, 0));

        JPanel painelEquipamentos = new JPanel(new GridBagLayout());
        painelEquipamentos.setBorder(BorderFactory.createTitledBorder("Equipamentos em Uso"));
        GridBagConstraints gbcEq = new GridBagConstraints();
        gbcEq.insets = new Insets(5, 6, 5, 6);
        gbcEq.fill = GridBagConstraints.HORIZONTAL;

        lblArmaEquipada = new JLabel("[Vazio]");
        lblSlotCabeca = new JLabel("[Vazio]");
        lblSlotPeito = new JLabel("[Vazio]");
        lblSlotPernas = new JLabel("[Vazio]");

        btnDesequiparArma = new JButton("Desequipar");
        btnDesequiparCabeca = new JButton("Desequipar");
        btnDesequiparPeito = new JButton("Desequipar");
        btnDesequiparPernas = new JButton("Desequipar");

        btnDesequiparArma.addActionListener(e -> desequiparArma());
        btnDesequiparCabeca.addActionListener(e -> desequiparArmadura(0));
        btnDesequiparPeito.addActionListener(e -> desequiparArmadura(1));
        btnDesequiparPernas.addActionListener(e -> desequiparArmadura(2));

        adicionarLinhaEquipamento(painelEquipamentos, gbcEq, 0, "Arma:", lblArmaEquipada, btnDesequiparArma);
        adicionarLinhaEquipamento(painelEquipamentos, gbcEq, 1, "Cabeça:", lblSlotCabeca, btnDesequiparCabeca);
        adicionarLinhaEquipamento(painelEquipamentos, gbcEq, 2, "Peito:", lblSlotPeito, btnDesequiparPeito);
        adicionarLinhaEquipamento(painelEquipamentos, gbcEq, 3, "Pernas:", lblSlotPernas, btnDesequiparPernas);

        gbcEq.gridy = 4; gbcEq.weighty = 1.0;
        painelEquipamentos.add(new JPanel(), gbcEq);

        JPanel painelMochila = new JPanel(new BorderLayout(6, 6));
        painelMochila.setBorder(BorderFactory.createTitledBorder("Mochila"));

        modeloListaMochila = new DefaultListModel<>();
        listaMochila = new JList<>(modeloListaMochila);
        listaMochila.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        painelMochila.add(new JScrollPane(listaMochila), BorderLayout.CENTER);

        JPanel painelBotoesMochila = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        btnUsarEquiparItem = new JButton("Usar / Equipar");
        btnRemoverItem = new JButton("Descartar");

        btnUsarEquiparItem.addActionListener(e -> usarOuEquiparItemSelecionado());
        btnRemoverItem.addActionListener(e -> removerItemMochilaSelecionado());

        painelBotoesMochila.add(btnUsarEquiparItem);
        painelBotoesMochila.add(btnRemoverItem);
        painelMochila.add(painelBotoesMochila, BorderLayout.SOUTH);

        painelCentro.add(painelEquipamentos);
        painelCentro.add(painelMochila);
        painelInventarioContainer.add(painelCentro, BorderLayout.CENTER);

        return painelInventarioContainer;
    }

    private void adicionarLinhaEquipamento(JPanel painel, GridBagConstraints gbc, int linha,
                                           String titulo, JLabel lblValor, JButton btnAcao) {
        gbc.gridy = linha;
        gbc.gridx = 0; gbc.weightx = 0.2;
        painel.add(new JLabel(titulo), gbc);

        gbc.gridx = 1; gbc.weightx = 0.6;
        painel.add(lblValor, gbc);

        gbc.gridx = 2; gbc.weightx = 0.2;
        painel.add(btnAcao, gbc);
    }

    private void atualizarCamposTipoItem() {
        String tipo = (String) cbTipoItem.getSelectedItem();
        if ("Arma".equals(tipo)) {
            lblValorItem.setText("Dano:");
            cbSlotArmadura.setEnabled(false);
        } else if ("Armadura".equals(tipo)) {
            lblValorItem.setText("Defesa:");
            cbSlotArmadura.setEnabled(true);
        } else {
            lblValorItem.setText("Cura:");
            cbSlotArmadura.setEnabled(false);
        }
    }

    private void registrarPersonagem() {
        try {
            String nome = txtNome.getText();
            String classe = (String) cbClasse.getSelectedItem();

            int forca = Integer.parseInt(txtForca.getText().trim());
            int inteligencia = Integer.parseInt(txtInteligencia.getText().trim());
            int destreza = Integer.parseInt(txtDestreza.getText().trim());
            int vida = Integer.parseInt(txtVida.getText().trim());

            Atributos atributos = new Atributos(forca, inteligencia, destreza, vida);
            Personagem personagem = instanciarPorClasse(classe, nome, atributos);

            RegistroPersonagem registro = new RegistroPersonagem(personagem);
            registros.add(registro);
            adicionarLinhaTabela(registro);
            limparCamposPersonagem();

            int novaLinha = registros.size() - 1;
            tabelaPersonagens.setRowSelectionInterval(novaLinha, novaLinha);

            JOptionPane.showMessageDialog(
                    this,
                    "Personagem registrado com sucesso!\n" + personagem.toString(),
                    "Registro Concluído",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os atributos com números inteiros válidos.",
                    "Erro de Entrada",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (NomeInvalidoException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "O nome do personagem não pode estar vazio.",
                    "Nome Inválido",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (AtributoInvalidoException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Todos os atributos devem ser maiores que zero.",
                    "Atributo Inválido",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (AtributoNuloException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nome ou atributos não podem ser nulos.",
                    "Dados Nulos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private Personagem instanciarPorClasse(String classe, String nome, Atributos atributos)
            throws NomeInvalidoException, AtributoNuloException {
        switch (classe) {
            case "Arcano":
                return new Arcano(nome, atributos);
            case "Arqueiro":
                return new Arqueiro(nome, atributos);
            case "Guerreiro":
                return new Guerreiro(nome, atributos);
            default:
                throw new IllegalArgumentException("Classe não suportada: " + classe);
        }
    }

    private void adicionarLinhaTabela(RegistroPersonagem reg) {
        Personagem p = reg.personagem;
        Atributos attr = p.getAtributos();
        Object[] linha = {
                p.getNome(),
                p.getClass().getSimpleName(),
                attr.getForca(),
                attr.getInteligencia(),
                attr.getDestreza(),
                attr.getVida(),
                String.format("%.2f", p.calcularPoderCombate()),
                reg.itensMochila.size()
        };
        modeloTabela.addRow(linha);
    }

    private void removerPersonagemSelecionado() {
        int linhaSelecionada = tabelaPersonagens.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um personagem na tabela para remover.",
                    "Aviso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        registros.remove(linhaSelecionada);
        modeloTabela.removeRow(linhaSelecionada);
        atualizarPainelInventario();
    }

    private RegistroPersonagem getRegistroSelecionado() {
        int linha = tabelaPersonagens.getSelectedRow();
        if (linha < 0 || linha >= registros.size()) {
            return null;
        }
        return registros.get(linha);
    }

    private void adicionarItemAoSelecionado() {
        RegistroPersonagem reg = getRegistroSelecionado();
        if (reg == null) {
            return;
        }

        try {
            String tipo = (String) cbTipoItem.getSelectedItem();
            String nomeItem = txtNomeItem.getText();
            Raridade raridade = (Raridade) cbRaridade.getSelectedItem();
            int valor = Integer.parseInt(txtValorItem.getText().trim());

            Item novoItem;
            if ("Arma".equals(tipo)) {
                novoItem = new Arma(nomeItem, raridade, valor);
            } else if ("Armadura".equals(tipo)) {
                int slot = cbSlotArmadura.getSelectedIndex();
                novoItem = new Armadura(nomeItem, raridade, valor, slot);
            } else {
                novoItem = new Pocao(nomeItem, raridade, valor);
            }

            reg.inventario.adcionarItem(novoItem);
            reg.itensMochila.add(novoItem);

            txtNomeItem.setText("");
            txtValorItem.setText("");
            atualizarPainelInventario();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Informe um valor numérico inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NomeInvalidoException ex) {
            JOptionPane.showMessageDialog(this, "O nome do item não pode estar vazio.", "Nome Inválido", JOptionPane.WARNING_MESSAGE);
        } catch (AtributoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, "O valor de dano, defesa ou cura deve ser maior que zero.", "Valor Inválido", JOptionPane.WARNING_MESSAGE);
        } catch (SlotInvalidoException ex) {
            JOptionPane.showMessageDialog(this, "Slot de armadura inválido.", "Slot Inválido", JOptionPane.ERROR_MESSAGE);
        } catch (CapacidadeInventarioExcedidaException ex) {
            JOptionPane.showMessageDialog(this, "Capacidade máxima (10 unidades deste item) atingida na mochila.", "Mochila Cheia", JOptionPane.WARNING_MESSAGE);
        } catch (AtributoNuloException ex) {
            JOptionPane.showMessageDialog(this, "Dados do item não podem ser nulos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void usarOuEquiparItemSelecionado() {
        RegistroPersonagem reg = getRegistroSelecionado();
        int indiceItem = listaMochila.getSelectedIndex();
        if (reg == null || indiceItem == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item na mochila.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Item item = reg.itensMochila.get(indiceItem);

        try {
            Item armaAntes = reg.inventario.getArmaEquip();
            Item[] corpoAntes = {
                    reg.inventario.getItemCorpo(0),
                    reg.inventario.getItemCorpo(1),
                    reg.inventario.getItemCorpo(2)
            };

            reg.inventario.usarItem(item);
            reg.itensMochila.remove(item);

            if (armaAntes != null && reg.inventario.getArmaEquip() != armaAntes) {
                reg.itensMochila.add(armaAntes);
            }
            for (int s = 0; s < 3; s++) {
                if (corpoAntes[s] != null && reg.inventario.getItemCorpo(s) != corpoAntes[s]) {
                    reg.itensMochila.add(corpoAntes[s]);
                }
            }

            if (item instanceof IConsumivel) {
                JOptionPane.showMessageDialog(this, "Você consumiu: " + item.getNome(), "Item Consumido", JOptionPane.INFORMATION_MESSAGE);
            }

            atualizarPainelInventario();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível usar/equipar o item: " + ex.getClass().getSimpleName(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerItemMochilaSelecionado() {
        RegistroPersonagem reg = getRegistroSelecionado();
        int indiceItem = listaMochila.getSelectedIndex();
        if (reg == null || indiceItem == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item na mochila para descartar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Item item = reg.itensMochila.get(indiceItem);
        try {
            reg.inventario.removerItem(item);
            reg.itensMochila.remove(item);
            atualizarPainelInventario();
        } catch (AtributoNuloException | ItemNaoEncontradoException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao remover item da mochila.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void desequiparArma() {
        RegistroPersonagem reg = getRegistroSelecionado();
        if (reg == null || reg.inventario.getArmaEquip() == null) {
            return;
        }

        Item arma = reg.inventario.getArmaEquip();
        try {
            ((IEquipavel) arma).desequipar(reg.inventario);
            reg.itensMochila.add(arma);
            atualizarPainelInventario();
        } catch (CapacidadeInventarioExcedidaException ex) {
            JOptionPane.showMessageDialog(this, "Mochila cheia para guardar a arma.", "Mochila Cheia", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao desequipar arma.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void desequiparArmadura(int slot) {
        RegistroPersonagem reg = getRegistroSelecionado();
        if (reg == null) {
            return;
        }

        try {
            Item armadura = reg.inventario.getItemCorpo(slot);
            if (armadura == null) {
                return;
            }
            ((IEquipavel) armadura).desequipar(reg.inventario);
            reg.itensMochila.add(armadura);
            atualizarPainelInventario();
        } catch (CapacidadeInventarioExcedidaException ex) {
            JOptionPane.showMessageDialog(this, "Mochila cheia para guardar a armadura.", "Mochila Cheia", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao desequipar armadura.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarPainelInventario() {
        RegistroPersonagem reg = getRegistroSelecionado();
        boolean temSelecao = (reg != null);

        cbTipoItem.setEnabled(temSelecao);
        txtNomeItem.setEnabled(temSelecao);
        cbRaridade.setEnabled(temSelecao);
        txtValorItem.setEnabled(temSelecao);
        cbSlotArmadura.setEnabled(temSelecao && "Armadura".equals(cbTipoItem.getSelectedItem()));
        btnAdicionarItem.setEnabled(temSelecao);
        listaMochila.setEnabled(temSelecao);
        btnUsarEquiparItem.setEnabled(temSelecao);
        btnRemoverItem.setEnabled(temSelecao);

        modeloListaMochila.clear();

        if (!temSelecao) {
            bordaInventario.setTitle("Inventário (selecione um personagem acima)");
            lblArmaEquipada.setText("[Vazio]");
            lblSlotCabeca.setText("[Vazio]");
            lblSlotPeito.setText("[Vazio]");
            lblSlotPernas.setText("[Vazio]");
            btnDesequiparArma.setEnabled(false);
            btnDesequiparCabeca.setEnabled(false);
            btnDesequiparPeito.setEnabled(false);
            btnDesequiparPernas.setEnabled(false);
            painelInventarioContainer.repaint();
            return;
        }

        bordaInventario.setTitle("Inventário de " + reg.personagem.getNome()
                + " (" + reg.personagem.getClass().getSimpleName() + ")");

        Item arma = reg.inventario.getArmaEquip();
        lblArmaEquipada.setText(arma != null ? arma.exibirDetalhes() : "[Vazio]");
        btnDesequiparArma.setEnabled(arma != null);

        try {
            Item cabeca = reg.inventario.getItemCorpo(0);
            Item peito = reg.inventario.getItemCorpo(1);
            Item pernas = reg.inventario.getItemCorpo(2);

            lblSlotCabeca.setText(cabeca != null ? cabeca.exibirDetalhes() : "[Vazio]");
            lblSlotPeito.setText(peito != null ? peito.exibirDetalhes() : "[Vazio]");
            lblSlotPernas.setText(pernas != null ? pernas.exibirDetalhes() : "[Vazio]");

            btnDesequiparCabeca.setEnabled(cabeca != null);
            btnDesequiparPeito.setEnabled(peito != null);
            btnDesequiparPernas.setEnabled(pernas != null);
        } catch (SlotInvalidoException ignored) {
        }

        for (Item item : reg.itensMochila) {
            modeloListaMochila.addElement(item.exibirDetalhes());
        }

        int linhaSelecionada = tabelaPersonagens.getSelectedRow();
        if (linhaSelecionada != -1) {
            modeloTabela.setValueAt(reg.itensMochila.size(), linhaSelecionada, 7);
        }

        painelInventarioContainer.repaint();
    }

    private void limparCamposPersonagem() {
        txtNome.setText("");
        txtForca.setText("");
        txtInteligencia.setText("");
        txtDestreza.setText("");
        txtVida.setText("");
        cbClasse.setSelectedIndex(0);
        txtNome.requestFocus();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            new TelaCriacaoPersonagem().setVisible(true);
        });
    }
}