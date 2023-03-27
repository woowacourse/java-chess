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

    KING(PieceRuleScore.ZERO, KingMovementStrategy.getInstance()),
    QUEEN(PieceRuleScore.QUEEN, QueenMovementStrategy.getInstance()),
    BISHOP(PieceRuleScore.BISHOP, BishopMovementStrategy.getInstance()),
    ROOK(PieceRuleScore.ROOK, RookMovementStrategy.getInstance()),
    KNIGHT(PieceRuleScore.KNIGHT, KnightMovementStrategy.getInstance()),
    INITIAL_PAWN(PieceRuleScore.PAWN, InitialPawnMovementStrategy.getInstance()),
    PAWN(PieceRuleScore.PAWN, PawnMovementStrategy.getInstance()),
    EMPTY(PieceRuleScore.ZERO, EmptyMovementStrategy.getInstance());

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
