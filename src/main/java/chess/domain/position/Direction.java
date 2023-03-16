package chess.domain.position;

public enum Direction {
    N(0, 1),
    E(1, 0),
    W(-1, 0),
    S(0, -1),
    NE(1, 1),
    SE(1, -1),
    SW(-1, -1),
    NW(-1, 1);
    
    private final int x;
    private final int y;
    
    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}
