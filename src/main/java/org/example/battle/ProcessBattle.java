package org.example.battle;

import org.example.enemy.model.AbstractEnemy;
import org.example.enemy.model.EnemyStorage;
import org.example.inventory.InventoryDesc;
import org.example.items.Item;
import org.example.options.BattleSpeed;
import org.example.player.Player;
import org.example.player.dto.Attack;
import org.example.player.dto.AttackResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ProcessBattle extends JPanel implements ActionListener {
    private final Timer timer;
    private final Player player;
    private final JButton restartButton;
    private final EnemyStorage enemyChooser = new EnemyStorage();
    private final DrawBattleDesc gameDesc = new DrawBattleDesc();
    private final LevelUpChoosingDesc levelUpChoosingDesc = new LevelUpChoosingDesc();
    private final EndGameDesc endGameDesc = new EndGameDesc();
    private final InventoryDesc inventoryDesc = new InventoryDesc();
    private JTextArea logArea;
    private AbstractEnemy enemy;
    private boolean gameOver = false;
    private boolean playerTurn = new Random().nextBoolean();
    private JButton inventoryButton;

    public ProcessBattle() {
        setLayout(null);
        timer = new Timer(BattleSpeed.FAST.getTurnSpeed(), this);
        player = new Player();
        enemy = enemyChooser.getRandomLvlOneEnemy();
        timer.start();

        restartButton = new JButton("Next battle");
        restartButton.setBounds(250, 400, 300, 50);
        restartButton.addActionListener(e -> resetGame());
        add(restartButton);
        restartButton.setVisible(false);

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
        inventoryButton.addActionListener(e -> inventoryDesc.openInventory(this));
        add(inventoryButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon(getClass().getResource("/static/background/battle.jpg")).getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
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
                appendLog(
                        "%s наносит критический урон %s!".formatted(player.getName(), playerAttack.getDamage()));
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
        revalidate();
        repaint();
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

        Item drop = enemy.getDrop();
        if (drop != null) {
            player.addItemToInventory(drop);
            appendLog("%s выбивает %s!".formatted(player.getName(), drop.getName()));
            inventoryDesc.updateInventory(this);
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

    private void appendLog(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength()); // Прокручиваем к последнему добавленному сообщению
    }

    private void clearLog() {
        logArea.setText(""); // Очищаем текстовое поле
    }
}
