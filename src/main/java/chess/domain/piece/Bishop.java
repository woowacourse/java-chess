package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Collection;

public class Bishop extends Piece {

    private static final int BISHOP_SCORE = 3;

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Bishop(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, Collection<Piece> pieces) {
        return getPosition().isDiagonalWay(to);
    }

    @Override
    public double score() {
        return BISHOP_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
