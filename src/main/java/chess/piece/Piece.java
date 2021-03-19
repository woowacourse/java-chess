package chess.piece;

import chess.board.Point;
import chess.piece.movingstrategy.BishopMovingStrategy;
import chess.piece.movingstrategy.EmptyMovingStrategy;
import chess.piece.movingstrategy.KingMovingStrategy;
import chess.piece.movingstrategy.KnightMovingStrategy;
import chess.piece.movingstrategy.MovingStrategy;
import chess.piece.movingstrategy.PawnMovingStrategy;
import chess.piece.movingstrategy.QueenMovingStrategy;
import chess.piece.movingstrategy.RookMovingStrategy;

public enum Piece {
    KING(new KingMovingStrategy(), 0),
    QUEEN(new QueenMovingStrategy(), 9),
    ROOK(new RookMovingStrategy(), 5),
    BISHOP(new BishopMovingStrategy(), 3),
    KNIGHT(new KnightMovingStrategy(), 2.5),
    PAWN(new PawnMovingStrategy(), 1),
    EMPTY(new EmptyMovingStrategy(), 0);

    private final MovingStrategy movingStrategy;
    private final double score;

    Piece(MovingStrategy movingStrategy, double score) {
        this.movingStrategy = movingStrategy;
        this.score = score;
    }

    public Vector findMovableVector(Point source, Point destination) {
        return movingStrategy.findMovableVector(source, destination);
    }

    public int getMoveLength() {
        return movingStrategy.getMoveLength();
    }

    public double score() {
        return score;
    }
}
