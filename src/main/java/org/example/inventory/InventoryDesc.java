package org.example.inventory;

import org.example.battle.ProcessBattle;
import org.example.items.Item;
import org.example.items.ItemType;
import org.example.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InventoryDesc {
    private JLabel topLeftImageLabel;
    private JDialog inventoryDialog;
    private JPanel inventoryPanel;
    private JPanel rightPanel;

    public void openInventory(ProcessBattle processBattle) {
        Player player = processBattle.getPlayer();
        Item[][] playerInventory = player.getInventory();

        JDialog inventoryDialog = new JDialog((Frame) null, "Inventory", true);
        inventoryDialog.setLayout(new BorderLayout(10, 10));
        inventoryDialog.setResizable(false);
        inventoryDialog.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Inventory", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Cooper Black", Font.BOLD, 20));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inventoryDialog.add(titleLabel, BorderLayout.NORTH);

        inventoryPanel = new JPanel(new GridLayout(5, 8, 10, 50)); // 5 строк и 8 столбцов

        for (int x = 0; x < playerInventory.length; x++) {
            for (int y = 0; y < playerInventory[x].length; y++) {
                final Item item = player.getInventory()[x][y];
                final JLabel cellLabel = new JLabel();
                cellLabel.setPreferredSize(new Dimension(50, 50));

                if (item != null) {
                    ImageIcon imageIcon = new ImageIcon(getClass().getResource(item.getImagePath()));
                    Image img = imageIcon.getImage().getScaledInstance(81, 79, Image.SCALE_SMOOTH);
                    cellLabel.setIcon(new ImageIcon(img));
                    cellLabel.addMouseListener(getInventoryCellsMouseListener(cellLabel, processBattle, x, y));
                }

                cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellLabel.setHorizontalAlignment(SwingConstants.CENTER);
                cellLabel.setVerticalAlignment(SwingConstants.CENTER);

                inventoryPanel.add(cellLabel);
            }
        }

        drawLeftPanel(inventoryDialog, inventoryPanel);
        rightPanel = drawRightPanel(inventoryDialog, player);

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setPreferredSize(new Dimension(100, 50));
        closeButton.setMinimumSize(new Dimension(100, 50));
        closeButton.setMaximumSize(new Dimension(100, 50));
        closeButton.addActionListener(e -> inventoryDialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        inventoryDialog.add(buttonPanel, BorderLayout.SOUTH);

        inventoryDialog.setSize(1600, 800);
        inventoryDialog.setLocationRelativeTo(null);
        inventoryDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        inventoryDialog.setVisible(true);
    }

    private void drawLeftPanel(JDialog inventoryDialog, JPanel inventoryPanel) {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(400, 800));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inventoryDialog.add(leftPanel, BorderLayout.WEST);

        JPanel topLeftPanelContainer = new JPanel(new BorderLayout());
        topLeftPanelContainer.setMaximumSize(
                new Dimension(400, 200));
        topLeftPanelContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setPreferredSize(new Dimension(200, 200));
        topLeftPanel.setMaximumSize(new Dimension(200, 200));
        topLeftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        topLeftImageLabel = new JLabel();
        topLeftImageLabel.setPreferredSize(new Dimension(190, 189));
        topLeftImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLeftImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        topLeftPanel.add(topLeftImageLabel, BorderLayout.CENTER);
        topLeftPanelContainer.add(topLeftPanel, BorderLayout.WEST);

        JPanel topRightPanel = new JPanel();
        topRightPanel.setPreferredSize(new Dimension(200, 200));
        topRightPanel.setMaximumSize(new Dimension(200, 200));
        topRightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topLeftPanelContainer.add(topRightPanel, BorderLayout.EAST);

        leftPanel.add(topLeftPanelContainer);

        JPanel bottomLeftPanelContainer = new JPanel(new BorderLayout());
        bottomLeftPanelContainer.setMaximumSize(
                new Dimension(400, 412));
        bottomLeftPanelContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel bottomLeftPanel = new JPanel();
        bottomLeftPanel.setPreferredSize(new Dimension(400, 412));
        bottomLeftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomLeftPanelContainer.add(bottomLeftPanel, BorderLayout.WEST);
        leftPanel.add(bottomLeftPanelContainer);

        inventoryDialog.add(inventoryPanel, BorderLayout.CENTER);
    }

    private JPanel drawRightPanel(JDialog inventoryDialog, Player player) {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(400, 800));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rightPanel.setBackground(Color.BLACK);
        inventoryDialog.add(rightPanel, BorderLayout.EAST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        int cellSize = 110;

        // Шлем
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel helmetLabel = createEquipmentSlot(player.getEquipment().getEquipment().get(ItemType.HELM));
        helmetLabel.setPreferredSize(new Dimension(cellSize, cellSize));
        helmetLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rightPanel.add(helmetLabel, gbc);

        // Амулет
        gbc.gridx = 2;
        gbc.gridy = 0;
        JLabel amuletLabel = createEquipmentSlot(player.getEquipment().getEquipment().get(ItemType.AMULET));
        amuletLabel.setPreferredSize(new Dimension(cellSize, cellSize));
        amuletLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rightPanel.add(amuletLabel, gbc);

        // Левая рука (меч)
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel leftHandLabel = createEquipmentSlot(player.getEquipment().getEquipment().get(ItemType.LEFT_HAND));
        leftHandLabel.setPreferredSize(new Dimension(cellSize, cellSize));
        leftHandLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rightPanel.add(leftHandLabel, gbc);

        // Броня (центр)
        gbc.gridx = 1;
        gbc.gridy = 2;
        JLabel armorLabel = createEquipmentSlot(player.getEquipment().getEquipment().get(ItemType.ARMOR));
        armorLabel.setPreferredSize(new Dimension(cellSize, cellSize));
        armorLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rightPanel.add(armorLabel, gbc);

        // Правая рука (меч)
        gbc.gridx = 2;
        gbc.gridy = 2;
        JLabel rightHandLabel = createEquipmentSlot(player.getEquipment().getEquipment().get(ItemType.RIGHT_HAND));
        rightHandLabel.setPreferredSize(new Dimension(cellSize, cellSize));
        rightHandLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rightPanel.add(rightHandLabel, gbc);

        // Штаны
        gbc.gridx = 1;
        gbc.gridy = 3;
        JLabel pantsLabel = createEquipmentSlot(player.getEquipment().getEquipment().get(ItemType.PANTS));
        pantsLabel.setPreferredSize(new Dimension(cellSize, cellSize));
        pantsLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rightPanel.add(pantsLabel, gbc);

        // Сапоги
        gbc.gridx = 1;
        gbc.gridy = 4;
        JLabel bootsLabel = createEquipmentSlot(player.getEquipment().getEquipment().get(ItemType.BOOTS));
        bootsLabel.setPreferredSize(new Dimension(cellSize, cellSize));
        bootsLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rightPanel.add(bootsLabel, gbc);

        return rightPanel;
    }

    private JLabel createEquipmentSlot(Item item) {
        JLabel slotLabel = new JLabel();
        slotLabel.setPreferredSize(new Dimension(50, 50));

        if (item != null) {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(item.getImagePath()));
            Image img = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            slotLabel.setIcon(new ImageIcon(img));
            slotLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Логика для экипировки
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ImageIcon hoveredImageIcon = new ImageIcon(getClass().getResource(item.getImagePath()));
                    Image hoveredImg = hoveredImageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    topLeftImageLabel.setIcon(new ImageIcon(hoveredImg));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    topLeftImageLabel.setIcon(null);
                }
            });
        }

        slotLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        slotLabel.setHorizontalAlignment(SwingConstants.CENTER);
        slotLabel.setVerticalAlignment(SwingConstants.CENTER);

        return slotLabel;
    }

    public void updateInventory(ProcessBattle processBattle) {
        Player player = processBattle.getPlayer();


        Component[] components;
        if (inventoryPanel != null) {
            components = inventoryPanel.getComponents();
        } else {
            components = new Component[player.getInventoryCellAmount()];
        }

        int index = 0;
        Item[][] playerInventory = player.getInventory();

        for (int x = 0; x < playerInventory.length; x++) {
            for (int y = 0; y < playerInventory[x].length; y++) {
                JLabel cellLabel = (JLabel) components[index++];
                Item item = playerInventory[x][y];

                if (item != null) {
                    ImageIcon imageIcon = new ImageIcon(getClass().getResource(item.getImagePath()));
                    Image img = imageIcon.getImage().getScaledInstance(81, 79, Image.SCALE_SMOOTH);
                    cellLabel.setIcon(new ImageIcon(img));
                } else {
                    cellLabel.setIcon(null);
                }

                cellLabel.addMouseListener(getInventoryCellsMouseListener(cellLabel, processBattle, x, y));
            }
        }
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private MouseAdapter getInventoryCellsMouseListener(
            JLabel cellLabel,
            ProcessBattle processBattle,
            final int x,
            final int y) {
        Player player = processBattle.getPlayer();
        Item[][] playerInventory = player.getInventory();
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                player.equipItem(x, y);

                Item oldItem = playerInventory[x][y];
                if (oldItem != null) {
                    ImageIcon imageIcon = new ImageIcon
                            (getClass().getResource(playerInventory[x][y].getImagePath()));
                    Image img = imageIcon.getImage().getScaledInstance(81, 79, Image.SCALE_SMOOTH);
                    cellLabel.setIcon(new ImageIcon(img));
                } else {
                    cellLabel.setIcon(null);
                }

                processBattle.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Item oldItem = playerInventory[x][y];
                if (oldItem != null) {
                    ImageIcon hoveredImageIcon = new ImageIcon(
                            getClass().getResource(playerInventory[x][y].getImagePath()));
                    Image hoveredImg = hoveredImageIcon.getImage()
                            .getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    topLeftImageLabel.setIcon(new ImageIcon(hoveredImg));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                topLeftImageLabel.setIcon(null);
            }
        };
    }
}
