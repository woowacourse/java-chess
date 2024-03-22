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
    WHITE_PAWN("p", PieceColor.WHITE, WhitePawnMovementStrategy.getInstance()),
    BLACK_PAWN("P", PieceColor.BLACK, BlackPawnMovementStrategy.getInstance()),
    WHITE_ROOK("r", PieceColor.WHITE, RookMovementStrategy.getInstance()),
    BLACK_ROOK("R", PieceColor.BLACK, RookMovementStrategy.getInstance()),
    WHITE_KNIGHT("n", PieceColor.WHITE, KnightMovementStrategy.getInstance()),
    BLACK_KNIGHT("N", PieceColor.BLACK, KnightMovementStrategy.getInstance()),
    WHITE_BISHOP("b", PieceColor.WHITE, BishopMovementStrategy.getInstance()),
    BLACK_BISHOP("B", PieceColor.BLACK, BishopMovementStrategy.getInstance()),
    WHITE_KING("k", PieceColor.WHITE, KingMovementStrategy.getInstance()),
    BLACK_KING("K", PieceColor.BLACK, KingMovementStrategy.getInstance()),
    WHITE_QUEEN("q", PieceColor.WHITE, QueenMovementStrategy.getInstance()),
    BLACK_QUEEN("Q", PieceColor.BLACK, QueenMovementStrategy.getInstance()),
    ;

    private final String name;
    private final PieceColor color;
    private final MovementStrategy movementStrategy;

    PieceType(String name, PieceColor color, MovementStrategy movementStrategy) {
        this.name = name;
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

    public String pieceName() {
        return name;
    }

    public MovementStrategy movementStrategy() {
        return movementStrategy;
    }
}
