package chess.domain.piece;

import chess.domain.board.Position;

public class EmptySpace extends Piece {

    public EmptySpace() {
        super("blank", Color.BLACK);
    }

    @Override
    public Direction findValidDirection(Position current, Position target) {
        throw new UnsupportedOperationException();
    }
}
