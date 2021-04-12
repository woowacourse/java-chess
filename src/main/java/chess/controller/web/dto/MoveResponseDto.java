package chess.controller.web.dto;

import chess.domain.piece.attribute.Color;

public class MoveResponseDto {
    private final boolean isEnd;
    private final String nextColor;

    public MoveResponseDto(boolean isEnd, Color nextColor) {
        this.isEnd = isEnd;
        this.nextColor = nextColor.name();
    }

    public boolean isEnd() {
        return isEnd;
    }

    public String getNextColor() {
        return nextColor;
    }
}
