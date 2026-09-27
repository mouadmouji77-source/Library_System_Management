package controller;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.*;

//Nous avons créer cette loading page par ChatGPT

public class LoadingScreen {
    public static void main(String[] args) {
        
        SplashScreen splash = new SplashScreen();
        splash.showSplash();

        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(3000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            splash.dispose();
            try {
				showMainApplication();
			} catch (UnsupportedLookAndFeelException e) {
				
			}
        });
    }

    private static void showMainApplication() throws UnsupportedLookAndFeelException {
    	UIManager.setLookAndFeel(new NimbusLookAndFeel());
        BibliothecaireController bc = new BibliothecaireController();
    }
}

class SplashScreen extends JWindow {
	
	public SplashScreen() {
        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout());

        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
        logoLabel.setIcon(logo);

        JLabel loadingLabel = new JLabel("Loading...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);

        content.add(logoLabel, BorderLayout.CENTER);
        content.add(loadingLabel, BorderLayout.NORTH);
        content.add(progressBar, BorderLayout.SOUTH);

        setContentPane(content);
    }

    public void showSplash() {
        setVisible(true);
    }
}
