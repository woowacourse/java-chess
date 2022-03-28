package chess.piece;

import chess.position.Position;
import java.math.BigDecimal;
import java.util.List;

public class Knight extends Piece {

    private static final String KING_SCORE = "2.5";
    private static final int MOVE_ONE_STEP_RULE = 1;
    private static final int MOVE_TWO_STEP_RULE = 2;

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Knight(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, List<Piece> pieces) {
        int horizontalDistance = getPosition().getHorizontalDistance(to);
        int verticalDistance = getPosition().getVerticalDistance(to);
        return (horizontalDistance == MOVE_ONE_STEP_RULE && verticalDistance == MOVE_TWO_STEP_RULE) ||
            (horizontalDistance == MOVE_TWO_STEP_RULE && verticalDistance == MOVE_ONE_STEP_RULE);
    }

    @Override
    public BigDecimal getPoint() {
        return new BigDecimal(KING_SCORE);
    }
}
