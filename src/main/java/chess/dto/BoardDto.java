package chess.dto;

import chess.domain.piece.Color;
import chess.domain.position.Position;

public class BoardDto {

    private final Position position;
    private final String name;
    private final Color color;


    public BoardDto(Position position, String name, Color color) {
        this.position = position;
        this.name = name;
        this.color = color;
    }

    public String getPosition() {
        return position.getPosition();
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
