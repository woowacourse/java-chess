package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.move.InvalidMove;
import chess.domain.position.move.PieceMove;

public class Empty extends Piece {

    public Empty() {
        super(Camp.NEUTRAL, PieceSymbol.EMPTY);
    }

    @Override
    public PieceMove getMovement(Position from, Position to) {
        return new InvalidMove();
    }

    @Override
    boolean isPieceRule(Position from, Position to) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
