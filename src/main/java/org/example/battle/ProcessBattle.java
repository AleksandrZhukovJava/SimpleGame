package org.example.battle;

import org.example.enemy.model.AbstractEnemy;
import org.example.enemy.model.EnemyStorage;
import org.example.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessBattle extends JPanel implements ActionListener {
    private final Timer timer;
    private final Player player;
    private final JButton restartButton;
    private final EnemyStorage enemyChooser = new EnemyStorage();
    private final DrawBattleDesc gameDesc = new DrawBattleDesc();
    private final LevelUpChoosingDesc levelUpChoosing = new LevelUpChoosingDesc();
    private AbstractEnemy enemy;
    private boolean gameOver = false;
    private boolean playerTurn = true;

    // Панель и текстовая область для отображения журнала
    private final JTextArea logArea;

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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameDesc.drawDesc(g, player, enemy);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            if (playerTurn) {
                enemy.attack(player);
                appendLog("%s наносит %s урона".formatted(enemy.getName(), enemy.getDamage()));
            } else {
                player.attack(enemy);
                appendLog("%s наносит %s урона".formatted(player.getName(), player.getCurrentDamage()));
            }
            playerTurn = !playerTurn;
            repaint();
            checkBattleIsEnd();
        }
    }

    private void checkBattleIsEnd() {
        if (enemy.getCurrentHealth() <= 0) {
            appendLog("%s повержен! Получено %s опыта\n".formatted(enemy.getName(), enemy.getExperienceValue()));
            gameOver = true;
            timer.stop();
            restartButton.setVisible(true);
            player.addExperience(enemy.getExperienceValue());
            if (player.checkLevelUp()) {
                levelUpChoosing.showLevelUpDialog(this);
                appendLog("%s достигает нового уровня %s!\n".formatted(player.getName(), player.getLevel()));
            }
            showResetButton();
        } else if (player.getCurrentHealth() <= 0) {
            gameOver = true;
            timer.stop();
            endGame();
        }
    }


    private void endGame() {
        // Создаем модальное диалоговое окно
        JDialog dialog = new JDialog((Frame) null, "Game Over", true);
        dialog.setLayout(new BorderLayout());

        // Создаем панель для кнопки
        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Начать заново");
        restartButton.addActionListener(e -> {
            dialog.dispose();
            player.backToDefault();
            resetGame();
        });
        buttonPanel.add(restartButton);

        // Добавляем сообщение и кнопку в диалоговое окно
        dialog.add(new JLabel("Вы проиграли, ваш уровень: %s".formatted(player.getLevel()), SwingConstants.CENTER),
                BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this); // Центрируем окно по отношению к основному окну
        dialog.setVisible(true);
    }

    private void resetGame() {
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

    public AbstractEnemy getEnemy() {
        return enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public void showResetButton() {
        restartButton.setEnabled(true);
    }

    private void appendLog(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength()); // Прокручиваем к последнему добавленному сообщению
    }

    private void clearLog() {
        logArea.setText(""); // Очищаем текстовое поле
    }
}
