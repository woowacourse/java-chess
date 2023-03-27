package chess.model.piece;

import chess.model.piece.movement.AttackEvaluator;
import chess.model.piece.movement.BishopMovementStrategy;
import chess.model.piece.movement.EmptyMovementStrategy;
import chess.model.piece.movement.InitialPawnMovementStrategy;
import chess.model.piece.movement.KingMovementStrategy;
import chess.model.piece.movement.KnightMovementStrategy;
import chess.model.piece.movement.MovementStrategy;
import chess.model.piece.movement.PawnMovementStrategy;
import chess.model.piece.movement.QueenMovementStrategy;
import chess.model.piece.movement.RookMovementStrategy;
import chess.model.piece.score.PieceRuleScore;
import chess.model.piece.score.PieceScore;
import chess.model.position.Distance;

public enum PieceType {

    KING(PieceRuleScore.ZERO, KingMovementStrategy.MOVEMENT),
    QUEEN(PieceRuleScore.QUEEN, QueenMovementStrategy.MOVEMENT),
    BISHOP(PieceRuleScore.BISHOP, BishopMovementStrategy.MOVEMENT),
    ROOK(PieceRuleScore.ROOK, RookMovementStrategy.MOVEMENT),
    KNIGHT(PieceRuleScore.KNIGHT, KnightMovementStrategy.MOVEMENT),
    INITIAL_PAWN(PieceRuleScore.PAWN, InitialPawnMovementStrategy.MOVEMENT),
    PAWN(PieceRuleScore.PAWN, PawnMovementStrategy.MOVEMENT),
    EMPTY(PieceRuleScore.ZERO, EmptyMovementStrategy.MOVEMENT);

    private final PieceRuleScore pieceRuleScore;
    private final MovementStrategy movementStrategy;

    PieceType(final PieceRuleScore pieceRuleScore, final MovementStrategy movementStrategy) {
        this.pieceRuleScore = pieceRuleScore;
        this.movementStrategy = movementStrategy;
    }

    public boolean isSamePieceType(final PieceType pieceType) {
        return this == pieceType;
    }

    public boolean movable(final Distance distance, final AttackEvaluator attackEvaluator) {
        return movementStrategy.movable(distance, attackEvaluator);
    }

    public PieceScore pieceScore() {
        return pieceRuleScore.score();
    }
}
