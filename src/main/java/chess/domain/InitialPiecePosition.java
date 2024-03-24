package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.List;

public enum InitialPiecePosition {
    BLACK_KING(new Piece(PieceType.KING, Color.BLACK), List.of(new Position(5, 8))),
    WHITE_KING(new Piece(PieceType.KING, Color.WHITE), List.of(new Position(5, 1))),

    BLACK_QUEEN(new Piece(PieceType.QUEEN, Color.BLACK), List.of(new Position(4, 8))),
    WHITE_QUEEN(new Piece(PieceType.QUEEN, Color.WHITE), List.of(new Position(4, 1))),

    BLACK_BISHOP(new Piece(PieceType.BISHOP, Color.BLACK), List.of(
            new Position(3, 8),
            new Position(6, 8))),
    WHITE_BISHOP(new Piece(PieceType.BISHOP, Color.WHITE), List.of(
            new Position(3, 1),
            new Position(6, 1))),

    BLACK_ROOK(new Piece(PieceType.ROOK, Color.BLACK), List.of(
            new Position(1, 8),
            new Position(8, 8))),
    WHITE_ROOK(new Piece(PieceType.ROOK, Color.WHITE), List.of(
            new Position(1, 1),
            new Position(8, 1))),

    BLACK_KNIGHT(new Piece(PieceType.KNIGHT, Color.BLACK), List.of(
            new Position(2, 8),
            new Position(7, 8))),
    WHITE_KNIGHT(new Piece(PieceType.KNIGHT, Color.WHITE), List.of(
            new Position(2, 1),
            new Position(7, 1))),

    BLACK_PAWN(new Piece(PieceType.PAWN, Color.BLACK), List.of(
            new Position(1, 7),
            new Position(2, 7),
            new Position(3, 7),
            new Position(4, 7),
            new Position(5, 7),
            new Position(6, 7),
            new Position(7, 7),
            new Position(8, 7))),

    WHITE_PAWN(new Piece(PieceType.PAWN, Color.WHITE), List.of(
            new Position(1, 2),
            new Position(2, 2),
            new Position(3, 2),
            new Position(4, 2),
            new Position(5, 2),
            new Position(6, 2),
            new Position(7, 2),
            new Position(8, 2)));

    private final Piece piece;
    private final List<Position> initialPositions;

    InitialPiecePosition(final Piece piece, final List<Position> initialPositions) {
        this.piece = piece;
        this.initialPositions = initialPositions;
    }

    public static List<InitialPiecePosition> getInitialPositionByColor(Color color) {
        return Arrays.stream(values())
                .filter(initialPiecePosition -> initialPiecePosition.isSameColor(color))
                .toList();
    }

    public boolean isSameColor(final Color color) {
        return piece.color() == color;
    }

    public List<Position> getInitialPositions() {
        return initialPositions;
    }

    public Piece getPiece() {
        return piece;
    }
}
