package chess.domain.piece;

import chess.domain.board.Position;

public class EmptySpace extends Piece {

    public EmptySpace() {
        super(PieceType.NONE, Color.BLACK);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Direction findValidDirection(Position current, Position target) {
        throw new UnsupportedOperationException();
    }
}
