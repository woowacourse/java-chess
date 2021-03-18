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
    KING(new KingMovingStrategy()),
    QUEEN(new QueenMovingStrategy()),
    ROOK(new RookMovingStrategy()),
    BISHOP(new BishopMovingStrategy()),
    KNIGHT(new KnightMovingStrategy()),
    PAWN(new PawnMovingStrategy()),
    EMPTY(new EmptyMovingStrategy());

    private MovingStrategy movingStrategy;

    Piece(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public Vector findMovableVector(Point source, Point destination) {
        return movingStrategy.findMovableVector(source, destination);
    }

    public int getMoveLength() {
        return movingStrategy.getMoveLength();
    }
}
