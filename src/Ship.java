public class Ship {
    private int size;
    private int hits;
    private boolean sunk;

    public Ship(int size) {
        this.size = size;
        this.hits = 0;
        this.sunk = false;
    }

    public void hit() {
        hits++;
        if (hits >= size) sunk = true;
    }

    public boolean isSunk() { return sunk; }
    public void setSunk(boolean sunk) { this.sunk = sunk; }
    public int getSize() { return size; }
}

