package chess.dto;

public class ChessPositionDto {
    private int sourceX;
    private int sourceY;
    private int targetX;
    private int targetY;

    public ChessPositionDto(int sourceX, int sourceY, int targetX, int targetY) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public int getSourceX() {
        return sourceX;
    }

    public int getSourceY() {
        return sourceY;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    @Override
    public String toString() {
        return "ChessPositionDto{" +
                "sourceX=" + sourceX +
                ", sourceY=" + sourceY +
                ", targetX=" + targetX +
                ", targetY=" + targetY +
                '}';
    }
}
