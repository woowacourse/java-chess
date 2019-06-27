package chess.domain.dto;

import chess.domain.board.Position;
import chess.domain.board.Square;

public class MovableDto {
    private int src;

    public Square getSrc() {
        return new Square(new Position((int)(src*0.1)), new Position(src%10));
    }

    public void setSrc(int src) {
        this.src = src;
    }
}
