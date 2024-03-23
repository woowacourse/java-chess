package chess.domain.piece;

import chess.domain.piece.strategy.BishopMovementStrategy;
import chess.domain.piece.strategy.BlackPawnMovementStrategy;
import chess.domain.piece.strategy.KingMovementStrategy;
import chess.domain.piece.strategy.KnightMovementStrategy;
import chess.domain.piece.strategy.MovementStrategy;
import chess.domain.piece.strategy.QueenMovementStrategy;
import chess.domain.piece.strategy.RookMovementStrategy;
import chess.domain.piece.strategy.WhitePawnMovementStrategy;

public enum PieceType {
    WHITE_PAWN(PieceColor.WHITE, WhitePawnMovementStrategy.getInstance()),
    BLACK_PAWN(PieceColor.BLACK, BlackPawnMovementStrategy.getInstance()),
    WHITE_ROOK(PieceColor.WHITE, RookMovementStrategy.getInstance()),
    BLACK_ROOK(PieceColor.BLACK, RookMovementStrategy.getInstance()),
    WHITE_KNIGHT(PieceColor.WHITE, KnightMovementStrategy.getInstance()),
    BLACK_KNIGHT(PieceColor.BLACK, KnightMovementStrategy.getInstance()),
    WHITE_BISHOP(PieceColor.WHITE, BishopMovementStrategy.getInstance()),
    BLACK_BISHOP(PieceColor.BLACK, BishopMovementStrategy.getInstance()),
    WHITE_KING(PieceColor.WHITE, KingMovementStrategy.getInstance()),
    BLACK_KING(PieceColor.BLACK, KingMovementStrategy.getInstance()),
    WHITE_QUEEN(PieceColor.WHITE, QueenMovementStrategy.getInstance()),
    BLACK_QUEEN(PieceColor.BLACK, QueenMovementStrategy.getInstance()),
    ;

    private final PieceColor color;
    private final MovementStrategy movementStrategy;

    PieceType(PieceColor color, MovementStrategy movementStrategy) {
        this.color = color;
        this.movementStrategy = movementStrategy;
    }

    public static boolean isKnight(final Piece piece) {
        return piece.isType(BLACK_KNIGHT) || piece.isType(WHITE_KNIGHT);
    }

    public static boolean isPawn(final Piece piece) {
        return piece.isType(BLACK_PAWN) || piece.isType(WHITE_PAWN);
    }

    public PieceColor color() {
        return color;
    }

    public MovementStrategy movementStrategy() {
        return movementStrategy;
    }
}
