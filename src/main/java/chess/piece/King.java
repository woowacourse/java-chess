package chess.piece;

import chess.position.Position;
import java.math.BigDecimal;

public class King extends Piece{

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new King(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, Pieces pieces) {
        return getPosition().isAdjacent(to);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public BigDecimal getPoint() {
        return BigDecimal.ZERO;
    }
}
