package chess.domain.piece;

import chess.domain.board.Point;
import chess.domain.piece.movingstrategy.*;

public enum Piece {
    KING(new KingMovingStrategy(), 0, "k"),
    QUEEN(new QueenMovingStrategy(), 9, "q"),
    ROOK(new RookMovingStrategy(), 5, "r"),
    BISHOP(new BishopMovingStrategy(), 3, "b"),
    KNIGHT(new KnightMovingStrategy(), 2.5, "n"),
    PAWN(new PawnMovingStrategy(), 1, "p"),
    EMPTY(new EmptyMovingStrategy(), 0, ".");

    private final MovingStrategy movingStrategy;
    private final double score;
    private final String pieceName;

    Piece(MovingStrategy movingStrategy, double score, String pieceName) {
        this.movingStrategy = movingStrategy;
        this.score = score;
        this.pieceName = pieceName;
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

    public String pieceName() {
        return pieceName;
    }
}
