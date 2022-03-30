package chess.domain.piece;

import chess.domain.piece.movingstrategy.BishopMovingStrategy;
import chess.domain.piece.movingstrategy.KingMovingStrategy;
import chess.domain.piece.movingstrategy.KnightMovingStrategy;
import chess.domain.piece.movingstrategy.MovingStrategy;
import chess.domain.piece.movingstrategy.PawnMovingStrategy;
import chess.domain.piece.movingstrategy.QueenMovingStrategy;
import chess.domain.piece.movingstrategy.RookMovingStrategy;
import chess.domain.position.Position;

public enum PieceType {
    PAWN(1, new PawnMovingStrategy()),
    ROOK(5, new RookMovingStrategy()),
    KNIGHT(2.5, new KnightMovingStrategy()),
    BISHOP(3, new BishopMovingStrategy()),
    QUEEN(9, new QueenMovingStrategy()),
    KING(0, new KingMovingStrategy());

    private final double score;
    private final MovingStrategy movingStrategy;

    PieceType(double score, MovingStrategy movingStrategy) {
        this.score = score;
        this.movingStrategy = movingStrategy;
    }

    public boolean isAbleToMove(Position from, Position to, PieceColor pieceColor) {
        return movingStrategy.isAbleToMove(from, to, pieceColor);
    }

    public boolean isAbleToAttack(Position from, Position to, PieceColor pieceColor) {
        return movingStrategy.isAbleToAttack(from, to, pieceColor);
    }

    public double getScore() {
        return score;
    }
}
