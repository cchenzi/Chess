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
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import model.Bispo;
import model.Cavalo;
import model.Peao;
import model.Peca;
import model.Rainha;
import model.Rei;
import model.Torre;
import model.Xadrez;

/**
 *
 * @author felip
 */
public class TelaJogo extends javax.swing.JFrame {

    Controller controller;
    JButton[][] matrizBotoes;
    Map<String, String> mapaPecaId = new HashMap<>();

    // Containers
    private JFrame window;
    private JPanel jPanelMatrizBotoes;
    private JPanel jPanelLateral;

    public TelaJogo(Controller controller) {
        this.controller = controller;
        matrizBotoes = new JButton[8][8];
        inicializarMapaPecaId();
        inicializarUI();
    }

    private void inicializarMapaPecaId() {
//        mapaPecaId.put("1", new Peao());
//        mapaPecaId.put("2", new Torre());
//        mapaPecaId.put("3", new Cavalo());
//        mapaPecaId.put("4", new Bispo());
//        mapaPecaId.put("5", new Rainha());
//        mapaPecaId.put("6", new Rei());
        mapaPecaId.put("1", "Peao");
        mapaPecaId.put("2", "Torre");
        mapaPecaId.put("3", "Cavalo");
        mapaPecaId.put("4", "Bispo");
        mapaPecaId.put("5", "Rainha");
        mapaPecaId.put("6", "Rei");
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

    private int[] getButtonPosition(JButton button) {
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
        jPanelLateral = new JPanel();
        jPanelLateral.setBorder(new EmptyBorder(20, 20, 20, 30));
        // SETAR LAYOUT
    }

    private void criarJPanelMatrizBotoes() {
        jPanelMatrizBotoes = new JPanel(new GridLayout(8, 8, 0, 0));
        jPanelMatrizBotoes.setBorder(new EmptyBorder(20, 20, 20, 20));
        criarMatrizBotoes();
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
        int[][] matriz = ((Xadrez) controller.getJogo()).getTabuleiro();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matriz[i][j] != 0) {
                    // IMPLEMENTAR SET ICON
                }
            }
        }
    }

    private JButton criarBotao(String id, int cor) {
        JButton button = new JButton();
        button.setMargin(new Insets(1, 3, 1, 4));
        button.setPreferredSize(new Dimension(80, 80));
        button.setName(id);
        if (cor == 1) {
            button.setBackground(Color.red);
        } else {
            button.setBackground(Color.blue);
        }
        button.addActionListener((ActionEvent e) -> {
            // IMPLEMENTAR
        });

        return button;
    }

}
