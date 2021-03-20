package chess.domain.piece;

import chess.domain.board.Point;
import chess.domain.piece.movingstrategy.BishopMovingStrategy;
import chess.domain.piece.movingstrategy.EmptyMovingStrategy;
import chess.domain.piece.movingstrategy.KingMovingStrategy;
import chess.domain.piece.movingstrategy.KnightMovingStrategy;
import chess.domain.piece.movingstrategy.MovingStrategy;
import chess.domain.piece.movingstrategy.PawnMovingStrategy;
import chess.domain.piece.movingstrategy.QueenMovingStrategy;
import chess.domain.piece.movingstrategy.RookMovingStrategy;

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

    public MoveVector movableVector(Point source, Point destination) {
        return movingStrategy.movableVector(source, destination);
    }

    public boolean hasMovableVector(Point source, Point destination) {
        return movingStrategy.hasMovableVector(source, destination);
    }

    public int movingLength() {
        return movingStrategy.movingLength();
    }

    public double score() {
        return score;
    }

    public String pieceName() {
        return pieceName;
    }
}
