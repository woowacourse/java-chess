package chess.domain.piece;

public class PositionDistance {
    private final int xDistance;
    private final int yDistance;

    public PositionDistance(int xDistance, int yDistance) {
        this.xDistance = xDistance;
        this.yDistance = yDistance;
    }

    public int getXDistance() {
        return xDistance;
    }

    public int getYDistance() {
        return yDistance;
    }
}
