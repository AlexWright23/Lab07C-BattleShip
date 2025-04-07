import javax.swing.*;
import java.awt.*;

public class BattleshipGUI extends JFrame {
    private JButton[][] buttons;
    private JLabel stats;
    private BattleshipGame game;
    private BattleshipBoard board;

    public BattleshipGUI(BattleshipGame game, BattleshipBoard board) {
        this.game = game;
        this.board = board;
        buttons = new JButton[10][10];

        setTitle("Battleship Game");
        setSize(600, 700);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel gridPanel = new JPanel(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                JButton btn = new JButton("~");
                int row = i;
                int col = j;
                btn.addActionListener(e -> game.processClick(row, col));
                buttons[i][j] = btn;
                gridPanel.add(btn);
            }

        stats = new JLabel("Hits: 0  Misses: 0  Strikes: 0");
        JButton playAgain = new JButton("Play Again");
        playAgain.addActionListener(e -> game.resetGame());

        JButton quit = new JButton("Quit");
        quit.addActionListener(e -> System.exit(0));

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(playAgain);
        bottomPanel.add(quit);

        add(stats, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void update() {
        BattleshipCell[][] grid = board.getGrid();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                BattleshipCell cell = grid[i][j];
                if (cell.isClicked()) {
                    if (cell.hasShip()) {
                        buttons[i][j].setText("X");
                        buttons[i][j].setBackground(Color.RED);
                    } else {
                        buttons[i][j].setText("M");
                        buttons[i][j].setBackground(Color.YELLOW);
                    }
                    buttons[i][j].setEnabled(false);
                }
            }
        stats.setText("Hits: " + board.getTotalHit() +
                "  Misses: " + board.getTotalMiss() +
                "  Strikes: " + board.getStrikeCount());
    }

    public void showMessage(String message) {
        int result = JOptionPane.showConfirmDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            game.resetGame();
        } else {
            System.exit(0);
        }
    }

    public void reset(BattleshipBoard newBoard) {
        this.board = newBoard;
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setText("~");
                buttons[i][j].setBackground(null);
                buttons[i][j].setEnabled(true);
            }
        update();
    }
}

