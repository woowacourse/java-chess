package chess.domain.piece;

import chess.domain.game.score.Score;
import chess.domain.piece.movingstrategy.BishopMovingStrategy;
import chess.domain.piece.movingstrategy.KingMovingStrategy;
import chess.domain.piece.movingstrategy.KnightMovingStrategy;
import chess.domain.piece.movingstrategy.MovingStrategy;
import chess.domain.piece.movingstrategy.PawnMovingStrategy;
import chess.domain.piece.movingstrategy.QueenMovingStrategy;
import chess.domain.piece.movingstrategy.RookMovingStrategy;
import chess.domain.position.Position;

public enum PieceType {
    PAWN(Score.from(1), new PawnMovingStrategy()),
    ROOK(Score.from(5), new RookMovingStrategy()),
    KNIGHT(Score.from(2.5), new KnightMovingStrategy()),
    BISHOP(Score.from(3), new BishopMovingStrategy()),
    QUEEN(Score.from(9), new QueenMovingStrategy()),
    KING(Score.from(0), new KingMovingStrategy());

    private final Score score;
    private final MovingStrategy movingStrategy;

    PieceType(Score score, MovingStrategy movingStrategy) {
        this.score = score;
        this.movingStrategy = movingStrategy;
    }

    public boolean isAbleToMove(Position from, Position to, PieceColor pieceColor) {
        return movingStrategy.isAbleToMove(from, to, pieceColor);
    }

    public boolean isAbleToAttack(Position from, Position to, PieceColor pieceColor) {
        return movingStrategy.isAbleToAttack(from, to, pieceColor);
    }

    public Score getScore() {
        return score;
    }
}
