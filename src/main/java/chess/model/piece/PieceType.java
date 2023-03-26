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
import chess.model.position.Distance;

public enum PieceType {

    KING(PieceScore.ZERO, KingMovementStrategy.MOVEMENT),
    QUEEN(PieceScore.QUEEN, QueenMovementStrategy.MOVEMENT),
    BISHOP(PieceScore.BISHOP, BishopMovementStrategy.MOVEMENT),
    ROOK(PieceScore.ROOK, RookMovementStrategy.MOVEMENT),
    KNIGHT(PieceScore.KNIGHT, KnightMovementStrategy.MOVEMENT),
    INITIAL_PAWN(PieceScore.PAWN, InitialPawnMovementStrategy.MOVEMENT),
    PAWN(PieceScore.PAWN, PawnMovementStrategy.MOVEMENT),
    EMPTY(PieceScore.ZERO, EmptyMovementStrategy.MOVEMENT);

    private final PieceScore pieceScore;
    private final MovementStrategy movementStrategy;

    PieceType(final PieceScore pieceScore, final MovementStrategy movementStrategy) {
        this.pieceScore = pieceScore;
        this.movementStrategy = movementStrategy;
    }

    public boolean isSamePieceType(final PieceType pieceType) {
        return this == pieceType;
    }

    public boolean movable(final Distance distance, final AttackEvaluator attackEvaluator) {
        return movementStrategy.movable(distance, attackEvaluator);
    }

    public PieceScore pieceScore() {
        return pieceScore;
    }
}
