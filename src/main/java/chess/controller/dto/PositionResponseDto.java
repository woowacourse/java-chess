package chess.controller.dto;

public class PositionResponseDto {

    private final int x;
    private final int y;

    public PositionResponseDto(final int x, final int y) {
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
