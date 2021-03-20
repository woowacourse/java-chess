package chess.domain.piece;

import chess.domain.Color;

public class Empty extends Piece {
    public Empty() {
        this(Position.EMPTY, ".", Color.NONE);
    }

    public Empty(Position position, String name, Color color) {
        super(position, name, color);
    }

    @Override
    public void move(Position target, CurrentPieces currentPieces) {
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다.");
    }
}
