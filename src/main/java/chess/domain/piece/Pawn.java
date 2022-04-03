package chess.domain.piece;

import static chess.domain.position.Direction.BOTTOM;
import static chess.domain.position.Direction.BOTTOM_LEFT;
import static chess.domain.position.Direction.BOTTOM_RIGHT;
import static chess.domain.position.Direction.TOP;
import static chess.domain.position.Direction.TOP_LEFT;
import static chess.domain.position.Direction.TOP_RIGHT;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.piece.strategy.pawn.PawnCaptureMovingStrategy;
import chess.domain.piece.strategy.pawn.PawnDefaultMovingStrategy;
import chess.domain.piece.strategy.pawn.PawnStartingPointMovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public class Pawn extends Piece {

    private static final List<MovingStrategy> BLACK_STRATEGIES = List.of(
            new PawnStartingPointMovingStrategy(1, BOTTOM),
            new PawnDefaultMovingStrategy(BOTTOM),
            new PawnCaptureMovingStrategy(List.of(BOTTOM_LEFT, BOTTOM_RIGHT))
    );

    private static final List<MovingStrategy> WHITE_STRATEGIES = List.of(
            new PawnStartingPointMovingStrategy(6, TOP),
            new PawnDefaultMovingStrategy(TOP),
            new PawnCaptureMovingStrategy(List.of(TOP_LEFT, TOP_RIGHT))
    );

    private final List<MovingStrategy> movingStrategies;

    public Pawn(Color color) {
        super(PieceType.PAWN, color);
        if (color.isBlack()) {
            this.movingStrategies = BLACK_STRATEGIES;
            return;
        }

        this.movingStrategies = WHITE_STRATEGIES;
    }

    @Override
    public void validateMove(Board board, Position source, Position target) {
        boolean canMove = movingStrategies.stream()
                .anyMatch(movingStrategy -> movingStrategy.canMove(board, source, target));

        if (!canMove) {
            throw new IllegalArgumentException("기물을 이동할 수 없습니다.");
        }
    }
}
