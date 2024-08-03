package org.example.util;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                this.backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
                if (backgroundImage == null) {
                    throw new Exception("Image not found");
                }
                System.out.println("Image loaded successfully from " + imagePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.RED);
                g.drawString("Image not found", getWidth() / 2 - 50, getHeight() / 2);
            }
        }
    }
