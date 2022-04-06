package chess.domain.piece;

import chess.domain.position.Position;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {

    private static final int ROOK_SCORE = 5;

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Rook(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, Collection<Piece> pieces) {
        return getPosition().isVerticalWay(to) || getPosition().isHorizontalWay(to);
    }

    @Override
    public double score() {
        return ROOK_SCORE;
    }
}
