package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.piece.strategy.pawn.BlackPawnDefaultMovingStrategy;
import chess.domain.piece.strategy.pawn.BlackPawnStartingPointMovingStrategy;
import chess.domain.piece.strategy.pawn.PawnCaptureMovingStrategy;
import chess.domain.piece.strategy.pawn.WhitePawnDefaultMovingStrategy;
import chess.domain.piece.strategy.pawn.WhitePawnStartingPointMovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Pawn extends Piece {

    private static final List<MovingStrategy> BLACK_STRATEGIES = List.of(
            new BlackPawnStartingPointMovingStrategy(),
            new BlackPawnDefaultMovingStrategy(),
            new PawnCaptureMovingStrategy(List.of(Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT))
    );

    private static final List<MovingStrategy> WHITE_STRATEGIES = List.of(
            new WhitePawnStartingPointMovingStrategy(),
            new WhitePawnDefaultMovingStrategy(),
            new PawnCaptureMovingStrategy(List.of(Direction.TOP_LEFT, Direction.TOP_RIGHT))
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
                .anyMatch(pawnMovingStrategy -> pawnMovingStrategy.canMove(board, source, target));

        if (!canMove) {
            throw new IllegalArgumentException();
        }
    }
}
