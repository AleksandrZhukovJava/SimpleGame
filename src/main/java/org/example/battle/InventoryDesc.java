package org.example.battle;

import org.example.items.Item;
import org.example.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InventoryDesc {
    private JLabel topLeftImageLabel;

    public void openInventory(ProcessBattle processBattle) {
        Player player = processBattle.getPlayer();
        Item[][] playerInventory = player.getInventory();

        JDialog inventoryDialog = new JDialog((Frame) null, "Inventory", true);
        inventoryDialog.setLayout(new BorderLayout(10, 10));
        inventoryDialog.setResizable(false);
        inventoryDialog.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Создаем заголовок
        JLabel titleLabel = new JLabel("Inventory", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Cooper Black", Font.BOLD, 20));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inventoryDialog.add(titleLabel, BorderLayout.NORTH);

        // Создаем панель для ячеек с GridLayout
        JPanel inventoryPanel = new JPanel(new GridLayout(5, 8, 10, 50)); // 5 строк и 8 столбцов

        // Создаем ячейки
        for (int x = 0; x < playerInventory.length; x++) {
            for (int y = 0; y < playerInventory[x].length; y++) {
                final int finalX = x;
                final int finalY = y;
                final Item item = player.getInventory()[x][y];
                final JLabel cellLabel = new JLabel();
                cellLabel.setPreferredSize(new Dimension(50, 50));

                if (item != null) {
                    ImageIcon imageIcon = new ImageIcon(getClass().getResource(item.getImagePath()));
                    Image img = imageIcon.getImage().getScaledInstance(81, 79, Image.SCALE_SMOOTH);
                    cellLabel.setIcon(new ImageIcon(img));
                    cellLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            player.equipItem(finalX, finalY);

                            Item oldItem = playerInventory[finalX][finalY];
                            if (oldItem != null) {
                                ImageIcon imageIcon = new ImageIcon
                                        (getClass().getResource(playerInventory[finalX][finalY].getImagePath()));
                                Image img = imageIcon.getImage().getScaledInstance(81, 79, Image.SCALE_SMOOTH);
                                cellLabel.setIcon(new ImageIcon(img));
                            } else {
                                cellLabel.setIcon(null);
                            }
                            processBattle.repaint();
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Item oldItem = playerInventory[finalX][finalY];
                            if (oldItem != null) {
                                ImageIcon hoveredImageIcon = new ImageIcon(
                                        getClass().getResource(playerInventory[finalX][finalY].getImagePath()));
                                Image hoveredImg = hoveredImageIcon.getImage()
                                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                                topLeftImageLabel.setIcon(new ImageIcon(hoveredImg));
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            topLeftImageLabel.setIcon(null);
                        }
                    });
                }

                cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellLabel.setHorizontalAlignment(SwingConstants.CENTER);
                cellLabel.setVerticalAlignment(SwingConstants.CENTER);

                inventoryPanel.add(cellLabel);
            }
        }

        drawLeftPanel(inventoryDialog, inventoryPanel);

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setPreferredSize(new Dimension(100, 50));
        closeButton.setMinimumSize(new Dimension(100, 50));
        closeButton.setMaximumSize(new Dimension(100, 50));
        closeButton.addActionListener(e -> inventoryDialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        inventoryDialog.add(buttonPanel, BorderLayout.SOUTH);

        inventoryDialog.setSize(1200, 800);
        inventoryDialog.setLocationRelativeTo(null);
        inventoryDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        inventoryDialog.setVisible(true);
    }

    private void drawLeftPanel(JDialog inventoryDialog, JPanel inventoryPanel) {
        // Создаем панель слева с BoxLayout
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(400, 800));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inventoryDialog.add(leftPanel, BorderLayout.WEST);

        // Создаем верхнюю квадратную панель и добавляем ее в верхнюю часть leftPanel
        JPanel topLeftPanelContainer = new JPanel(new BorderLayout());
        topLeftPanelContainer.setMaximumSize(
                new Dimension(400, 200)); // Устанавливаем максимальные размеры для контейнера
        topLeftPanelContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setPreferredSize(new Dimension(200, 200)); // размеры квадратной панели
        topLeftPanel.setMaximumSize(new Dimension(200, 200)); // максимальные размеры квадратной панели
        topLeftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        topLeftImageLabel = new JLabel();
        topLeftImageLabel.setPreferredSize(new Dimension(190, 189)); // Устанавливаем размер лейбла для изображения
        topLeftImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLeftImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        topLeftPanel.add(topLeftImageLabel, BorderLayout.CENTER); // Добавляем лейбл в центр панели
        topLeftPanelContainer.add(topLeftPanel, BorderLayout.WEST);

        // Создаем второй квадрат и добавляем его вправо от первого
        JPanel topRightPanel = new JPanel();
        topRightPanel.setPreferredSize(new Dimension(200, 200)); // размеры второго квадрата
        topRightPanel.setMaximumSize(new Dimension(200, 200)); // максимальные размеры второго квадрата
        topRightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topLeftPanelContainer.add(topRightPanel, BorderLayout.EAST);

        leftPanel.add(topLeftPanelContainer);

        // Создаем нижнюю прямоугольную панель и добавляем ее в оставшуюся часть leftPanel
        JPanel bottomLeftPanelContainer = new JPanel(new BorderLayout());
        bottomLeftPanelContainer.setMaximumSize(
                new Dimension(400, 412)); // Устанавливаем максимальные размеры для контейнера
        bottomLeftPanelContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel bottomLeftPanel = new JPanel();
        bottomLeftPanel.setPreferredSize(new Dimension(400, 412)); // размеры прямоугольной панели
        bottomLeftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomLeftPanelContainer.add(bottomLeftPanel, BorderLayout.WEST);
        leftPanel.add(bottomLeftPanelContainer);

        // Добавляем панель с ячейками в окно
        inventoryDialog.add(inventoryPanel, BorderLayout.CENTER);
    }

    private void showCellInfo(Item item) {
        JDialog cellInfoDialog = new JDialog((Frame) null, "Cell Information", true);
        cellInfoDialog.setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel(item.getName(), SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cellInfoDialog.add(infoLabel, BorderLayout.CENTER);

        JButton closeInfoButton = new JButton("Close");
        closeInfoButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeInfoButton.setPreferredSize(new Dimension(100, 50));
        closeInfoButton.setMinimumSize(new Dimension(100, 50));
        closeInfoButton.setMaximumSize(new Dimension(100, 50));
        closeInfoButton.addActionListener(e -> cellInfoDialog.dispose()); // Закрываем диалоговое окно

        JPanel infoButtonPanel = new JPanel();
        infoButtonPanel.add(closeInfoButton);
        cellInfoDialog.add(infoButtonPanel, BorderLayout.SOUTH);

        cellInfoDialog.setSize(200, 250);
        cellInfoDialog.setLocationRelativeTo(null);
        cellInfoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        cellInfoDialog.setVisible(true);
    }
}
