import java.util.*;

public class BattleshipBoard {
    private BattleshipCell[][] grid;
    private List<Ship> ships;
    private int missCount;
    private int strikeCount;
    private int totalMiss;
    private int totalHit;

    public BattleshipBoard() {
        grid = new BattleshipCell[10][10];
        ships = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                grid[i][j] = new BattleshipCell(i, j);
        ShipPlacer.placeShips(this);
        missCount = 0;
        strikeCount = 0;
        totalMiss = 0;
        totalHit = 0;
    }

    public void fire(int row, int col) {
        BattleshipCell cell = grid[row][col];
        if (cell.isClicked()) return;
        cell.click();
        if (cell.hasShip()) {
            cell.getShip().hit();
            totalHit++;
            missCount = 0;
            if (cell.getShip().isSunk()) {
                cell.getShip().setSunk(true);
            }
        } else {
            missCount++;
            totalMiss++;
            if (missCount == 5) {
                strikeCount++;
                missCount = 0;
            }
        }
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public BattleshipCell[][] getGrid() {
        return grid;
    }

    public int getStrikeCount() { return strikeCount; }
    public int getTotalHit() { return totalHit; }
    public int getTotalMiss() { return totalMiss; }

    public boolean allShipsSunk() {
        return ships.stream().allMatch(Ship::isSunk);
    }
}

