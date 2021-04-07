package chess.domain.piece;

import chess.domain.board.Point;
import chess.domain.piece.movementStrategy.BishopMovementStrategy;
import chess.domain.piece.movementStrategy.EmptyMovementStrategy;
import chess.domain.piece.movementStrategy.KingMovementStrategy;
import chess.domain.piece.movementStrategy.KnightMovementStrategy;
import chess.domain.piece.movementStrategy.MovementStrategy;
import chess.domain.piece.movementStrategy.PawnMovementStrategy;
import chess.domain.piece.movementStrategy.QueenMovementStrategy;
import chess.domain.piece.movementStrategy.RookMovementStrategy;
import java.util.Arrays;

public enum Piece {
    KING(new KingMovementStrategy(), 0, "k"),
    QUEEN(new QueenMovementStrategy(), 9, "q"),
    ROOK(new RookMovementStrategy(), 5, "r"),
    BISHOP(new BishopMovementStrategy(), 3, "b"),
    KNIGHT(new KnightMovementStrategy(), 2.5, "n"),
    PAWN(new PawnMovementStrategy(), 1, "p"),
    EMPTY(new EmptyMovementStrategy(), 0, ".");

    private final MovementStrategy movementStrategy;
    private final double score;
    private final String pieceName;

    Piece(MovementStrategy movementStrategy, double score, String pieceName) {
        this.movementStrategy = movementStrategy;
        this.score = score;
        this.pieceName = pieceName;
    }

    public static Piece pieceByName(String name) {
        return Arrays.stream(values())
            .filter(piece -> piece.pieceName.equals(name))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public MoveVector movableVector(Point source, Point destination) {
        return movementStrategy.movableVector(source, destination);
    }

    public boolean hasMovableVector(Point source, Point destination) {
        return movementStrategy.hasMovableVector(source, destination);
    }

    public int movementRange() {
        return movementStrategy.movementRange();
    }

    public double score() {
        return score;
    }

    public String pieceName() {
        return pieceName;
    }
}
