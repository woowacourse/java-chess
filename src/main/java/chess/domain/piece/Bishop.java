package chess.domain.piece;

import chess.domain.position.Position;
import java.math.BigDecimal;
import java.util.List;

public class Bishop extends Piece {

    private static final String BISHOP_SCORE = "3";

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Bishop(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, List<Piece> pieces) {
        return getPosition().isDiagonalWay(to);
    }

    @Override
    public BigDecimal getPoint() {
        return new BigDecimal(BISHOP_SCORE);
    }
}
