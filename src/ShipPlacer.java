import java.util.*;

public class ShipPlacer {
    public static void placeShips(BattleshipBoard board) {
        int[] shipSizes = {5, 4, 3, 3, 2};
        Random rand = new Random();

        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                boolean vertical = rand.nextBoolean();
                int row = rand.nextInt(10);
                int col = rand.nextInt(10);
                if (canPlace(board, size, row, col, vertical)) {
                    Ship ship = new Ship(size);
                    for (int i = 0; i < size; i++) {
                        int r = row + (vertical ? i : 0);
                        int c = col + (vertical ? 0 : i);
                        board.getGrid()[r][c].setShip(ship);
                    }
                    board.addShip(ship);
                    placed = true;
                }
            }
        }
    }

    private static boolean canPlace(BattleshipBoard board, int size, int row, int col, boolean vertical) {
        for (int i = 0; i < size; i++) {
            int r = row + (vertical ? i : 0);
            int c = col + (vertical ? 0 : i);
            if (r >= 10 || c >= 10 || board.getGrid()[r][c].hasShip()) return false;
        }
        return true;
    }
}

