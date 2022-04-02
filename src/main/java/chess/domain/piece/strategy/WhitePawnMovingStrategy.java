package chess.domain.piece.strategy;

import static chess.domain.position.Direction.TOP;
import static chess.domain.position.Direction.TOP_LEFT;
import static chess.domain.position.Direction.TOP_RIGHT;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import java.util.List;

public class WhitePawnMovingStrategy extends PawnMovingStrategy {

    private static final int RANK_INDEX_STARTING_POINT = 6;
    private static final List<Direction> DIRECTIONS = List.of(TOP, TOP_LEFT, TOP_RIGHT);

    public WhitePawnMovingStrategy() {
        super(RANK_INDEX_STARTING_POINT, DIRECTIONS);
    }

    @Override
    public void validateSameColor(Piece targetPiece) {
        if (!targetPiece.isBlack()) {
            throw new IllegalArgumentException("공격은 다른 진영만 가능합니다.");
        }
    }
}
