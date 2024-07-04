package com.ors.zona_fit;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.ors.zona_fit.gui.ZonaFitForma;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class ZonaFitSwing {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ZonaFitSwing.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
        SwingUtilities.invokeLater(()->{
            ZonaFitForma zonaFitForma = context.getBean(ZonaFitForma.class);
            zonaFitForma.setVisible(true);
        });
    }
}
