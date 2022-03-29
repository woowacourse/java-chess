package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.pawn.BlackPawnCaptureMovingStrategy;
import chess.domain.piece.strategy.pawn.BlackPawnDefaultMovingStrategy;
import chess.domain.piece.strategy.pawn.BlackPawnStartingPointMovingStrategy;
import chess.domain.piece.strategy.pawn.PawnMovingStrategy;
import chess.domain.piece.strategy.pawn.WhitePawnCaptureMovingStrategy;
import chess.domain.piece.strategy.pawn.WhitePawnDefaultMovingStrategy;
import chess.domain.piece.strategy.pawn.WhitePawnStartingPointMovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public class Pawn extends Piece {

    private static final List<PawnMovingStrategy> BLACK_STRATEGIES = List.of(
            new BlackPawnStartingPointMovingStrategy(),
            new BlackPawnDefaultMovingStrategy(),
            new BlackPawnCaptureMovingStrategy()
    );

    private static final List<PawnMovingStrategy> WHITE_STRATEGIES = List.of(
            new WhitePawnStartingPointMovingStrategy(),
            new WhitePawnDefaultMovingStrategy(),
            new WhitePawnCaptureMovingStrategy()
    );

    private final List<PawnMovingStrategy> movingStrategies;

    public Pawn(Color color) {
        super(PieceType.PAWN, color, null);
        if (color.isBlack()) {
            this.movingStrategies = BLACK_STRATEGIES;
            return;
        }

        this.movingStrategies = WHITE_STRATEGIES;
    }

    @Override
    public void validateMove(List<List<Piece>> board, Position source, Position target) {
        boolean canMove = movingStrategies.stream()
                .anyMatch(pawnMovingStrategy -> pawnMovingStrategy.canMove(board, source, target));

        if (!canMove) {
            throw new IllegalArgumentException();
        }
    }
}
