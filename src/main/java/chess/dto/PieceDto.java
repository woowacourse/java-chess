package chess.dto;

import chess.domain.piece.Color;
import chess.domain.piece.Name;

public class PieceDto {
    private Name name;
    private Color color;

    public void setName(Name name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Name getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
