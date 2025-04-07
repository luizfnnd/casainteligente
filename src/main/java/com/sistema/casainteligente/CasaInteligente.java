package com.sistema.casainteligente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CasaInteligente extends JFrame {
    private JPanel grid;
    private Random random;

    public CasaInteligente() {
        random = new Random();
        setTitle("Casa Inteligente - Múltiplos Cômodos");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));

        JButton atualizarBtn = new JButton("Atualizar Ambiente");
        atualizarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAmbientes();
            }
        });

        grid = new JPanel(new GridLayout(0, 2, 20, 20)); // 2 colunas com espaçamento
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(atualizarBtn, BorderLayout.NORTH);
        add(new JScrollPane(grid), BorderLayout.CENTER);

        atualizarAmbientes();
        setVisible(true);
    }

    private void atualizarAmbientes() {
        grid.removeAll();
        String[] comodos = {"Quarto", "Sala", "Cozinha", "Banheiro", "Garagem"};

        for (String comodo : comodos) {
            grid.add(criarAmbiente(comodo));
        }

        grid.revalidate(); // Atualiza o layout
        grid.repaint(); // Re-renderiza os componentes
    }

    private JPanel criarAmbiente(String nome) {
        int temperatura = random.nextInt(46) - 5;
        int umidade = random.nextInt(101);
        int luminosidade = random.nextInt(101);

        String ar = (temperatura > 26) ? "Ar-condicionado: Ligado" : "Ar-condicionado: Desligado";
        String luz = (luminosidade < 30 && temperatura < 28) ? "Luz: Ligada" : "Luz: Desligada";
        String status = "Ambiente estável.";

        if (temperatura > 35) status = "⚠️ Muito quente! Ventilação necessária.";
        else if (umidade < 30) status = "⚠️ Ar seco! Use umidificador.";

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("🏠 " + nome);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel tempLabel = new JLabel("🌡️ Temperatura: " + temperatura + " °C");
        JLabel umidLabel = new JLabel("💧 Umidade: " + umidade + "%");
        JLabel luzLabel = new JLabel("💡 Luminosidade: " + luminosidade + "%");
        JLabel arLabel = new JLabel(ar);
        JLabel luzStatus = new JLabel(luz);
        JLabel statusLabel = new JLabel("Status: " + status);

        card.add(titulo);
        card.add(tempLabel);
        card.add(umidLabel);
        card.add(luzLabel);
        card.add(arLabel);
        card.add(luzStatus);
        card.add(statusLabel);

        return card;
    }

    public static void main(String[] args) {
        new CasaInteligente();
    }
}
