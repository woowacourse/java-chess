package chess.controller.dto;

public class PositionResponseDto {

    int x;
    int y;

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
