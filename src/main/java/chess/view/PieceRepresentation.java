package chess.view;

import java.util.Arrays;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

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
        if (piece.isSameColor(Color.WHITE)) {
            return representation.whiteColor;
        }
        return representation.blackColor;
    }

    private static PieceRepresentation findMatchingType(Piece piece) {
        return Arrays.stream(values())
            .filter(value -> piece.isSameType(value.pieceType))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 타입이 없습니다."));
    }
}
