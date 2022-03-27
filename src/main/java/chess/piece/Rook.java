package chess.piece;

import chess.position.Position;
import java.math.BigDecimal;
import java.util.List;

public class Rook extends Piece{

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Rook(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, List<Piece> pieces) {
        return getPosition().isVerticalWay(to) || getPosition().isHorizontalWay(to);
    }

    @Override
    public BigDecimal getPoint() {
        return new BigDecimal("5");
    }
}
