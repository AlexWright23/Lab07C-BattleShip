public class BattleshipCell {
    private int row, col;
    private boolean clicked;
    private Ship ship;

    public BattleshipCell(int row, int col) {
        this.row = row;
        this.col = col;
        this.clicked = false;
        this.ship = null;
    }

    public void click() { clicked = true; }
    public boolean isClicked() { return clicked; }
    public boolean hasShip() { return ship != null; }
    public Ship getShip() { return ship; }
    public void setShip(Ship ship) { this.ship = ship; }
}
