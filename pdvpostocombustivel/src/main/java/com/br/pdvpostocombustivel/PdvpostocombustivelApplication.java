package com.br.pdvpostocombustivel;

import com.br.pdvpostocombustivel.frontend.view.MenuPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

@SpringBootApplication
public class PdvpostocombustivelApplication {

    public static void main(String[] args) {
        // Desativa o modo "headless" para permitir janelas gráficas
        System.setProperty("java.awt.headless", "false");

        // Inicia o Spring Boot
        SpringApplication.run(PdvpostocombustivelApplication.class, args);

        // Agora inicia a interface Swing
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new MenuPrincipal().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
