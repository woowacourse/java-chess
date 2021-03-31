package chess.domain.dto;

import chess.domain.piece.info.Color;

public class PieceDto {
    private final Color color;
    private final String name;
    private final String position;

    public PieceDto(Color color, String name, String position) {
        this.color = color;
        this.name = name;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}
