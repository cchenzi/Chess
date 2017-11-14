/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Exceptions.JogadaInvalidaException;
import control.Controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import model.Peca;
import model.Xadrez;

/**
 *
 * @author felip
 */
public class TelaJogo extends javax.swing.JFrame {

    private Controller controller;
    private JToggleButton[][] matrizBotoes;

    private JToggleButton pecaSelecionada, destinoPecaSelecionada;

    // Containers
    private JFrame window;
    private JPanel jPanelMatrizBotoes;
    private JPanel jPanelLateral;

    // Labels dinamicos
    private JLabel jogadorVez, pontuacaoP1, pontuacaoP2;

    public TelaJogo(Controller controller) {
        this.controller = controller;
        matrizBotoes = new JToggleButton[8][8];
        inicializarUI();
    }

    private void inicializarUI() {
        criarWindow();
        criarJPanelLateral();
        criarJPanelMatrizBotoes();

        window.add(jPanelMatrizBotoes);
        window.add(jPanelLateral);

        window.pack();
        window.setVisible(true);
    }

    private int[] getButtonPosition(JToggleButton button) {
        int[] position = new int[2];
        position[0] = Integer.parseInt(button.getName().split(",")[0]);
        position[1] = Integer.parseInt(button.getName().split(",")[1]);

        return position;
    }

    // MÉTODOS P/ CRIACAO DA UI
    private void criarWindow() {
        window = new JFrame("Xadrez");
        window.setLayout(new FlowLayout(FlowLayout.CENTER));
        window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fecharTela();
            }
        });
    }

    private void fecharTela() {
        window.dispose();
    }

    private void criarJPanelLateral() {
        jPanelLateral = new JPanel(new GridBagLayout());

        jogadorVez = new JLabel("Vez de: " + controller.getJogo().getJogadores()[0].getNome());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        jPanelLateral.add(jogadorVez, gridBagConstraints);

        JLabel jLabel1 = new JLabel("PONTUAÇÃO");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(jLabel1, gridBagConstraints);

        JLabel jLabel2 = new JLabel(controller.getJogo().getJogadores()[0].getNome() + ": ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(jLabel2, gridBagConstraints);

        pontuacaoP1 = new JLabel("" + controller.getJogo().getJogadores()[0].getPontuacao());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(pontuacaoP1, gridBagConstraints);

        JLabel jLabel3 = new JLabel(controller.getJogo().getJogadores()[1].getNome() + ": ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(jLabel3, gridBagConstraints);

        pontuacaoP2 = new JLabel("" + controller.getJogo().getJogadores()[1].getPontuacao());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(pontuacaoP2, gridBagConstraints);

        JButton jButton1 = new JButton("Renunciar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(jButton1, gridBagConstraints);
        jButton1.addActionListener((ActionEvent e) -> {
            int valorRenuncia = JOptionPane.showConfirmDialog(null, controller.getJogo().getJogadores()[controller.getJogo().getJogadorVez()].getNome() + ", voce tem certeza disso?");
            if (valorRenuncia == 0) {
                controller.getJogo().preparaProximaJogada();
                anunciarVencedor();
            }
        });

        JButton jButton2 = new JButton("Empate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(jButton2, gridBagConstraints);
        jButton2.addActionListener((ActionEvent e) -> {
            int valorEmpate = JOptionPane.showConfirmDialog(null, "Ambos os jogadores concordam com o empate?");
            if (valorEmpate == 0) {
                anunciarEmpate();
            }
        });

    }

    private void criarJPanelMatrizBotoes() {
        jPanelMatrizBotoes = new JPanel(new GridLayout(8, 8, 0, 0));
        jPanelMatrizBotoes.setBorder(new EmptyBorder(20, 20, 20, 20));
        criarMatrizBotoes();
        atualizarMatrizBotoes();
        habilitarPecasDisponiveis();
    }

    private void criarMatrizBotoes() {
        int nLinhas = 8;
        int nColunas = 8;
        for (int i = 0; i < nLinhas; i++) {
            for (int j = 0; j < nColunas; j++) {
                String id = i + "," + j;
                int cor = 1;
                if (i % 2 != j % 2) {
                    cor = 2;
                }
                matrizBotoes[i][j] = criarBotao(id, cor);   // COR: 1 = BRANCO, 2 = PRETO
                jPanelMatrizBotoes.add(matrizBotoes[i][j]);
            }
        }
    }

    private void atualizarMatrizBotoes() {
        Peca[][] matriz = ((Xadrez) controller.getJogo()).getTabuleiro();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matriz[i][j] != null) {
                    String nomeArquivo = matriz[i][j].getClass().getName().replace(".", "_").split("_")[1] + "_" + matriz[i][j].getCor() + ".png";
                    matrizBotoes[i][j].setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/main/java/img/" + nomeArquivo.toLowerCase()));
                } else {
                    matrizBotoes[i][j].setIcon(null);
                }
            }
        }
    }

    private JToggleButton criarBotao(String id, int cor) {
        JToggleButton button = new JToggleButton();
        button.setMargin(new Insets(1, 3, 1, 4));
        button.setPreferredSize(new Dimension(80, 80));
        button.setName(id);
        if (cor == 1) {
            button.setBackground(new Color(255, 255, 240)); // COR = MARFIM
        } else {
            button.setBackground(new Color(205, 133, 63));  // COR = MARROM
        }
        button.addActionListener((ActionEvent e) -> {
            definirToggleButtonActionListener((JToggleButton) e.getSource());
        });
        return button;
    }

    private void atualizarLabels() {
        jogadorVez.setText("Vez de: " + controller.getJogo().getJogadores()[controller.getJogo().getJogadorVez()].getNome());
        pontuacaoP1.setText("" + controller.getJogo().getJogadores()[0].getPontuacao());
        pontuacaoP2.setText("" + controller.getJogo().getJogadores()[1].getPontuacao());
    }

    private void anunciarVencedor() {
        JOptionPane.showMessageDialog(null, "XEQUE-MATE!!");
        JOptionPane.showMessageDialog(null, "PARABÉNS " + controller.getJogo().getJogadores()[controller.getJogo().getJogadorVez()].getNome());
        fecharTela();
    }

    private void anunciarEmpate() {
        JOptionPane.showMessageDialog(null, "Foi declarado empate!");
        fecharTela();
    }

    private void limparSelecao() {
        pecaSelecionada.setSelected(false);
        destinoPecaSelecionada.setSelected(false);
        pecaSelecionada = null;
        destinoPecaSelecionada = null;
    }

    private boolean contemPeca(JToggleButton button) {
        int i = getButtonPosition(button)[0];
        int j = getButtonPosition(button)[1];
        return ((Xadrez) controller.getJogo()).getTabuleiro()[i][j] != null;
    }

    private void habilitarPecasDisponiveis() {
        String cor;
        if (controller.getJogo().getJogadorVez() == 0) {
            cor = "BRANCO";
        } else {
            cor = "PRETO";
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JToggleButton button = matrizBotoes[i][j];
                if (contemPeca(button) && getCorPeca(button).equals(cor)) {
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
            }
        }
    }

    private void habilitarTodosBotoes() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matrizBotoes[i][j].setEnabled(true);
            }
        }
    }

    private String getCorPeca(JToggleButton button) {
        int i = getButtonPosition(button)[0];
        int j = getButtonPosition(button)[1];
        return ((Xadrez) controller.getJogo()).getTabuleiro()[i][j].getCor();
    }

    private void definirToggleButtonActionListener(JToggleButton button) {
        // TEMPLATE METHOD
        if (pecaSelecionada == null) {
            pecaSelecionada = button;
            habilitarTodosBotoes();
        } else {
            if (button == pecaSelecionada) {
                pecaSelecionada.setSelected(true);
                JOptionPane.showMessageDialog(null, "Uma vez selecionada, esta peça tem de ser jogada!");
            } else {
                destinoPecaSelecionada = button;
                try {
                    fazerJogada();
                } catch (JogadaInvalidaException ex) {
                    JOptionPane.showMessageDialog(null, "Jogada inválida!");
                } finally {
                    limparSelecao();
                    habilitarPecasDisponiveis();
                }
            }
        }
    }

    private void fazerJogada() throws JogadaInvalidaException {
        controller.getJogo().fazerJogada(getButtonPosition(pecaSelecionada), getButtonPosition(destinoPecaSelecionada));

        if (controller.getJogo().verificaFimJogo()) {
            anunciarVencedor();
        } else {
            // PREPARA PROXIMA RODADA
            controller.getJogo().preparaProximaJogada();
            atualizarMatrizBotoes();
            atualizarLabels();
        }
    }

}
