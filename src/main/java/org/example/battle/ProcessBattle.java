package org.example.battle;

import org.example.enemy.model.AbstractEnemy;
import org.example.enemy.model.EnemyStorage;
import org.example.player.Player;
import org.example.player.dto.Attack;
import org.example.player.dto.AttackResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class ProcessBattle extends JPanel implements ActionListener {
    private final Timer timer;
    private final Player player;
    private final JButton restartButton;
    private final EnemyStorage enemyChooser = new EnemyStorage();
    private final DrawBattleDesc gameDesc = new DrawBattleDesc();
    private final LevelUpChoosingDesc levelUpChoosingDesc = new LevelUpChoosingDesc();
    private final EndGameDesc endGameDesc = new EndGameDesc();
    private AbstractEnemy enemy;
    private boolean gameOver = false;
    private boolean playerTurn = new Random().nextBoolean();
    private final JTextArea logArea;

    private JButton inventoryButton;

    public ProcessBattle() {
        setLayout(null);
        timer = new Timer(BattleSpeed.FAST.getTurnSpeed(), this);
        player = new Player();
        enemy = enemyChooser.getRandomLvlOneEnemy();
        timer.start();

        restartButton = new JButton("Next battle");
        restartButton.setBounds(350, 500, 100, 30);
        restartButton.addActionListener(e -> resetGame());
        add(restartButton);
        restartButton.setVisible(false);

        // Инициализация панели для журнала
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(200, 150, 400, 200); // Центрируем панель в окне
        add(scrollPane);

        // Создаем и добавляем кнопку инвентаря
        inventoryButton = new JButton("Inventory");
        inventoryButton.setFont(new Font("Arial", Font.BOLD, 16));
        inventoryButton.setBounds(50, 520, 120, 30); // Установите положение и размер кнопки
        inventoryButton.addActionListener(e -> openInventory());
        add(inventoryButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameDesc.drawDesc(g, player, enemy);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            if (!playerTurn) {
                enemyTurn();
            } else {
                playerTurn();
            }
            playerTurn = !playerTurn;
            repaint();

            if (enemy.getCurrentHealth() <= 0) {
                playerWin();
            } else if (player.getCurrentHealth() <= 0) {
                enemyWin();
            }
        }
    }

    private void enemyTurn() {
        Attack enemyAttack = enemy.getAttack();
        AttackResult enemyAttackResult = player.takeHit(enemyAttack);
        if (enemyAttackResult.isMiss()) {
            appendLog("%s промахивается!".formatted(enemy.getName()));
        } else {
            appendLog("%s наносит %s урона".formatted(enemy.getName(), enemyAttack.getDamage()));
        }
    }

    private void playerTurn() {
        Attack playerAttack = player.getAttack();
        AttackResult playerAttackResult = enemy.takeHit(playerAttack);
        if (playerAttackResult.isMiss()) {
            appendLog("%s промахивается!".formatted(player.getName()));
        } else {
            if (playerAttack.isCritical()) {
                appendLog("%s наносит критичкский урон %s!".formatted(player.getName(), playerAttack.getDamage()));
            } else {
                appendLog("%s наносит %s урона".formatted(player.getName(), playerAttack.getDamage()));
            }
        }

    }

    public void resetGame() {
        player.refreshEntity();
        enemy.refreshEntity();
        enemy = enemyChooser.getRandomLvlOneEnemy();
        playerTurn = true;
        gameOver = false;
        restartButton.setVisible(false);
        timer.start();
        repaint();
        clearLog();
    }

    private void playerWin() {
        appendLog("%s повержен! Получено %s опыта\n".formatted(enemy.getName(), enemy.getExperienceValue()));
        gameOver = true;
        timer.stop();
        restartButton.setVisible(true);
        player.addExperience(enemy.getExperienceValue());
        while (player.checkLevelUp()) {
            levelUpChoosingDesc.execute(this);
            appendLog("%s достигает нового уровня %s!\n".formatted(player.getName(), player.getLevel()));
        }
        showResetButton();
    }

    private void enemyWin() {
        gameOver = true;
        timer.stop();
        endGameDesc.execute(this);
    }

    public AbstractEnemy getEnemy() {
        return enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public void showResetButton() {
        restartButton.setEnabled(true);
    }

    /**
     * Метод для открытия инвентаря
     */
    private void openInventory() {
        JDialog inventoryDialog = new JDialog((Frame) null, "Inventory", true);
        inventoryDialog.setLayout(new BorderLayout());
        inventoryDialog.setResizable(false);

        // Создаем заголовок
        JLabel titleLabel = new JLabel("Inventory", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Cooper Black", Font.BOLD, 20));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inventoryDialog.add(titleLabel, BorderLayout.NORTH);

        // Создаем панель для ячеек с GridLayout
        JPanel inventoryPanel = new JPanel(new GridLayout(5, 8, 20, 30)); // 8 строк и 5 столбцов

        // Создаем ячейки
        for (int i = 0; i < 40; i++) {
            JLabel cellLabel = new JLabel();
            cellLabel.setPreferredSize(new Dimension(50, 50));
            if (i < 15) {
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/static/inventory/inventory_default.jpg"));
                Image img = imageIcon.getImage().getScaledInstance(76, 63, Image.SCALE_SMOOTH);
                cellLabel.setIcon(new ImageIcon(img));
            }
            cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cellLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cellLabel.setVerticalAlignment(SwingConstants.CENTER);

            cellLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showCellInfo(cellLabel);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    //todo окно с информацией
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    //todo закрытие окна с информацией
                }
            });

            inventoryPanel.add(cellLabel);
        }

        // Добавляем панель с ячейками в окно
        inventoryDialog.add(inventoryPanel, BorderLayout.CENTER);

        // Создаем кнопку закрытия
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setPreferredSize(new Dimension(100, 50)); // Делаем кнопку квадратной
        closeButton.setMinimumSize(new Dimension(100, 50));
        closeButton.setMaximumSize(new Dimension(100, 50));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryDialog.dispose(); // Закрываем диалоговое окно
            }
        });

        // Добавляем кнопку закрытия в нижнюю часть окна
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        inventoryDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Устанавливаем размер окна и показываем его
        inventoryDialog.setSize(800, 600);
        inventoryDialog.setLocationRelativeTo(null);
        inventoryDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        inventoryDialog.setVisible(true);
    }

    private void showCellInfo(JLabel cell) {
        JDialog cellInfoDialog = new JDialog((Frame) null, "Cell Information", true);
        cellInfoDialog.setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel("Cell clicked!", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cellInfoDialog.add(infoLabel, BorderLayout.CENTER);

        JButton closeInfoButton = new JButton("Close");
        closeInfoButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeInfoButton.setPreferredSize(new Dimension(100, 50));
        closeInfoButton.setMinimumSize(new Dimension(100, 50));
        closeInfoButton.setMaximumSize(new Dimension(100, 50));
        closeInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cellInfoDialog.dispose(); // Закрываем диалоговое окно
            }
        });

        JPanel infoButtonPanel = new JPanel();
        infoButtonPanel.add(closeInfoButton);
        cellInfoDialog.add(infoButtonPanel, BorderLayout.SOUTH);

        cellInfoDialog.setSize(300, 150);
        cellInfoDialog.setLocationRelativeTo(null);
        cellInfoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        cellInfoDialog.setVisible(true);
    }

    private void appendLog(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength()); // Прокручиваем к последнему добавленному сообщению
    }

    private void clearLog() {
        logArea.setText(""); // Очищаем текстовое поле
    }
}
