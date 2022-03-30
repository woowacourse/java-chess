package chess.domain.piece.strategy;

import static chess.domain.position.Direction.BOTTOM;
import static chess.domain.position.Direction.BOTTOM_LEFT;
import static chess.domain.position.Direction.BOTTOM_RIGHT;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import java.util.List;

public class BlackPawnMovingStrategy extends PawnMovingStrategy {

    private static final int RANK_INDEX_STARTING_POINT = 1;
    private static final List<Direction> DIRECTIONS = List.of(BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT);

    public BlackPawnMovingStrategy() {
        super(RANK_INDEX_STARTING_POINT, DIRECTIONS);
    }

    @Override
    public void validateSameColor(Piece targetPiece) {
        if (targetPiece.isBlack()) {
            throw new IllegalArgumentException("공격은 다른 진영만 가능합니다.");
        }
    }
}
