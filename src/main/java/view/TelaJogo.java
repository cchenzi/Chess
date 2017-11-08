/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import model.Jogador;
import model.Observer;
import model.Xadrez;
import model.Xadrez.PecasEnum;

/**
 *
 * @author felip
 */
public class TelaJogo extends javax.swing.JFrame implements Observer {

    private Controller controller;
    private JToggleButton[][] matrizBotoes;
    
    private JToggleButton pecaSelecionadaInicio, destinoPecaSelecionada;

    // Containers
    private JFrame window;
    private JPanel jPanelMatrizBotoes;
    private JPanel jPanelLateral;
    
    // Labels dinamicos
    private JLabel pontuacaoP1, pontuacaoP2;

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
        
        JLabel jLabel1 = new JLabel("PONTUAÇÃO");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(jLabel1, gridBagConstraints);

        JLabel jLabel2 = new JLabel(controller.getJogo().getJogadores()[0].getNome() + ": ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(jLabel2, gridBagConstraints);

        pontuacaoP1 = new JLabel("" + controller.getJogo().getJogadores()[0].getPontuacao());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(pontuacaoP1, gridBagConstraints);

        JLabel jLabel3 = new JLabel(controller.getJogo().getJogadores()[1].getNome() + ": ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(jLabel3, gridBagConstraints);

        pontuacaoP2 = new JLabel("" + controller.getJogo().getJogadores()[1].getPontuacao());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(pontuacaoP2, gridBagConstraints);

        JButton jButton1 = new JButton("Renunciar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(jButton1, gridBagConstraints);

        JButton jButton2 = new JButton("Empate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelLateral.add(jButton2, gridBagConstraints);
    }

    private void criarJPanelMatrizBotoes() {
        jPanelMatrizBotoes = new JPanel(new GridLayout(8, 8, 0, 0));
        jPanelMatrizBotoes.setBorder(new EmptyBorder(20, 20, 20, 20));
        criarMatrizBotoes();
        preencherMatrizBotoes();
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

    private void preencherMatrizBotoes() {
        PecasEnum[][] matriz = ((Xadrez) controller.getJogo()).getTabuleiro();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matriz[i][j] != null) {
                    String nomeArquivo = matriz[i][j].toString().toLowerCase() + ".png";
                    matrizBotoes[i][j].setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/main/java/img/" + nomeArquivo));  // FAZER DINAMICAMENTE
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
            button.setBackground(new Color(205, 133, 63));
        }
        button.addActionListener((ActionEvent e) -> {
            if (pecaSelecionadaInicio == null) {
                pecaSelecionadaInicio = (JToggleButton)e.getSource();
            } else {
               destinoPecaSelecionada = (JToggleButton)e.getSource();
            }
        });

        return button;
    }
    
    private void atualizarLabelsPontuacao() {
        pontuacaoP1.setText("" + controller.getJogo().getJogadores()[0].getPontuacao());
        pontuacaoP2.setText("" + controller.getJogo().getJogadores()[1].getPontuacao());
    }

    @Override
    public void requisitarJogada(Jogador jogador) {
        while (pecaSelecionadaInicio == null) {}
        while (destinoPecaSelecionada == null){}
        ((Xadrez)controller.getJogo()).movimentarPeca(jogador, getButtonPosition(pecaSelecionadaInicio), getButtonPosition(destinoPecaSelecionada));
        
        pecaSelecionadaInicio = null;
        destinoPecaSelecionada = null;
    }

}
