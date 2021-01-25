package ru.barbarenko.lesson8;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
import ru.barbarenko.lesson8.common.BoardItem;
import ru.barbarenko.lesson8.common.GameBoard;

/**
 * Окно игры в крестики-нолики
 * @author Alex Dl.
 *
 */
class GameFrame extends JFrame {

    private static final long serialVersionUID = 355435345345435L;

    // размер игровой доски
    private  final int BOARD_SIZE = 5;
    // размер ячейки игровой доски, px
    private  final int FIELD_WIDTH = 34;
    // количество крестиков или ноликов в непрерывной линии, при котором
    // засчитывается выигрыш
    private  final int WIN_COUNT = 5;
    // размеры окна, px
    private final int FRAME_DEFAULT_WIDH = 280;
    private final int FRAME_DEFAULT_HEIGTH = 250;

    // если true - первыми начинают игру крестики
    private boolean playerIsX = true;
    private JPanel gamePanel;

    // счет игры
    private int xCount = 0;
    private int oCount = 0;

    private GameBoard board;

    public GameFrame() {

        board = new GameBoard(BOARD_SIZE);

        // создание меню
        JMenuBar mainMenu = new JMenuBar();
        setJMenuBar(mainMenu);
        JMenu fileMenu = new JMenu("Файл");
        JMenu helpMenu = new JMenu("Справка");
        JMenuItem exitItem = new JMenuItem("Выход");
        JMenuItem clearItem = new JMenuItem("Новая игра");
        fileMenu.add(exitItem);
        fileMenu.add(clearItem);
        JMenuItem aboutItem = new JMenuItem("О программе");
        helpMenu.add(aboutItem);
        mainMenu.add(fileMenu);
        mainMenu.add(helpMenu);

        // панель на которой размещена игровая доска
        gamePanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        JScrollPane scrollFrame = new JScrollPane(gamePanel);
        gamePanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(FRAME_DEFAULT_WIDH, FRAME_DEFAULT_HEIGTH));

        for (int i = 0; i < board.getSize(); i++) {

            for (int j = 0; j < board.getSize(); j++) {

                JButton btn = new JButton(board.getItemAt(i, j).getName());
                btn.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_WIDTH));
                btn.setAction(new ButtonClickAction(i, j));
                gamePanel.add(btn);
            }
        }

        setContentPane(scrollFrame);
        pack();

        // события
        exitItem.addActionListener(new ExitActionListener());
        aboutItem.addActionListener(new AboutActionListener());
        clearItem.addActionListener(new ClearActionListener());

        SwingUtilities.updateComponentTreeUI(mainMenu);
        SwingUtilities.updateComponentTreeUI(gamePanel);
    }

    // Очистить игровую доску
    private void clearGamePanel() {

        playerIsX = true;
        int count = 0;

        //очистка элементов игровой доски - тех, которые были использованы
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {

                BoardItem item = board.getItemAt(i, j);
                if (!item.equals(BoardItem.UNDEFINED)){
                    JButton button = (JButton) gamePanel.getComponents()[count];
                    button.setText(new String());
                    button.setEnabled(true);
                    button.setBackground(null);
                    button.setForeground(null);
                }
                count++;
            }
        }

        board = new GameBoard(BOARD_SIZE);
    }

    // действие при нажатии на кнопку (игровую ячейку)
    private class ButtonClickAction extends AbstractAction {

        private static final long serialVersionUID = 1L;

        // индекс ячейки игровой доски
        private int i, j;

        public ButtonClickAction(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton source = (JButton) e.getSource();

            // клик по пустому полю
            if (!board.getItemAt(i, j).equals(BoardItem.UNDEFINED))
                return;

            // установка крестика или нолика на поле
            board.setItemAt(playerIsX ? BoardItem.X : BoardItem.O, i, j);
            source.setText(board.getItemAt(i, j).getName());
            source.setEnabled(false);

            // меняем игрока
            playerIsX = !playerIsX;

            // проверка, есть ли победитель
            BoardItem winner = board.getNextWinner(WIN_COUNT);

            if (winner != null && !winner.equals(BoardItem.UNDEFINED)) {

                String msg;

                if (winner.equals(BoardItem.X)) {
                    msg = "крестиков";
                    xCount++;
                } else {
                    msg = "ноликов";
                    oCount++;
                }

                renderGameField();

                JOptionPane.showMessageDialog(GameFrame.this, "Победа " + msg
                                + ", счет " + " - крестики (" + xCount + "): нолики ("
                                + oCount + ")", "Победа",
                        JOptionPane.INFORMATION_MESSAGE);

                clearGamePanel();

            }
        }
    }

    private void renderGameField() {
        int count = 0;
        for (int i = 0; i < board.getSize(); i++)
            for (int j = 0; j < board.getSize(); j++) {
                JButton buff = (JButton) gamePanel.getComponents()[count++];
                buff.setText(board.getItemAt(i, j).getName());

                if (board.getItemAt(i, j).equals(BoardItem.O_WIN_FIELD)
                        || board.getItemAt(i, j).equals(BoardItem.X_WIN_FIELD)) {
                    buff.setOpaque(true);
                    buff.setForeground(Color.BLACK);
                }
            }
    }

    // выход из приложения
    private class ExitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    // сброс игры
    private class ClearActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            clearGamePanel();
            xCount = 0;
            oCount = 0;
        }
    }

    // окно "о программе"
    private class AboutActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            JOptionPane.showMessageDialog(GameFrame.this, "Крестики-нолики "
                            + BOARD_SIZE + "x" + BOARD_SIZE
                            + ". \nДля победы составьте линию из " + WIN_COUNT
                            + " крестиков или ноликов.", "О программе",
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }

}