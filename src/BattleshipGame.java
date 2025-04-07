public class BattleshipGame {
    private BattleshipBoard board;
    private BattleshipGUI gui;

    public BattleshipGame() {
        board = new BattleshipBoard();
        gui = new BattleshipGUI(this, board);
    }

    public void processClick(int row, int col) {
        board.fire(row, col);
        gui.update();

        if (board.allShipsSunk()) {
            gui.showMessage("You WIN! Play again?");
        } else if (board.getStrikeCount() >= 3) {
            gui.showMessage("You LOST! Play again?");
        }
    }

    public void resetGame() {
        board = new BattleshipBoard();
        gui.reset(board);
    }
}



