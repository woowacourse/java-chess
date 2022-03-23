package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.Arrays;

public enum PieceRepresentation {

    BISHOP(PieceType.BISHOP, "♝", "♗"),
    KING(PieceType.KING, "♛", "♕"),
    KNIGHT(PieceType.KNIGHT, "♞", "♘"),
    QUEEN(PieceType.QUEEN, "♚", "♔"),
    ROOK(PieceType.ROOK, "♜", "♖"),
    PAWN(PieceType.PAWN, "♟", "♙"),
    EMPTY(PieceType.EMPTY, ".", ".");

    private final PieceType pieceType;
    private final String whiteColor;
    private final String blackColor;

    PieceRepresentation(PieceType pieceType, String whiteColor, String blackColor) {
        this.pieceType = pieceType;
        this.whiteColor = whiteColor;
        this.blackColor = blackColor;
    }

    public static String convertType(Piece piece) {
        PieceRepresentation representation = findMatchingType(piece);
        if (piece.getColor() == Color.WHITE) {
            return representation.whiteColor;
        }
        return representation.blackColor;
    }

    private static PieceRepresentation findMatchingType(Piece piece) {
        return Arrays.stream(values())
                .filter(value -> value.pieceType == piece.getType())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
