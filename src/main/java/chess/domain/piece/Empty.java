package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;

public class Empty extends Piece {
    private static final String EMPTY_ERROR = "[ERROR] 기물이 존재하지 않습니다.";
    public static final Empty EMPTY = new Empty(Position.EMPTY, ".", Color.NONE);

    public Empty(Position position, String name, Color color) {
        super(position, name, color);
    }

    @Override
    public void move(Position target, CurrentPieces currentPieces) {
        throw new IllegalArgumentException(EMPTY_ERROR);
    }
}
