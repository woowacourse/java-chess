package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Collection;

public class King extends Piece {

    private static final int KING_SCORE = 0;

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new King(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, Collection<Piece> pieces) {
        return getPosition().isAdjacent(to);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double score() {
        return KING_SCORE;
    }
}
