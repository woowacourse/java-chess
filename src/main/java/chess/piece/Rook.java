package chess.piece;

import chess.position.Position;
import java.math.BigDecimal;

public class Rook extends Piece{

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Rook(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, Pieces pieces) {
        return isValidWay(to) && !hasObstacle(to, pieces);
    }

    private boolean isValidWay(Position to) {
        return getPosition().isVerticalWay(to) || getPosition().isHorizontalWay(to);
    }

    private boolean hasObstacle(Position to, Pieces pieces) {
        return pieces.hasObstacleOnLinearPath(getPosition(), to);
    }

    @Override
    public BigDecimal getPoint() {
        return new BigDecimal("5");
    }
}
