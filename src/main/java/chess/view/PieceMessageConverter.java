package chess.view;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.DefaultType;
import chess.model.piece.PieceColor;
import chess.model.piece.PieceType;
import java.util.Arrays;

public enum PieceMessageConverter {

    EMPTY(DefaultType.EMPTY, "."),
    PAWN(PieceType.PAWN, "p"),
    BISHOP(PieceType.BISHOP, "b"),
    KNIGHT(PieceType.KNIGHT, "n"),
    ROOK(PieceType.ROOK, "r"),
    QUEEN(PieceType.QUEEN, "q"),
    KING(PieceType.KING, "k");

    private final Type type;
    private final String message;

    PieceMessageConverter(final Type type, final String message) {
        this.type = type;
        this.message = message;
    }

    public static String convert(final Type type, final Color pieceColor) {
        final String message = convertPieceMessage(type);

        if (PieceColor.WHITE.equals(pieceColor)) {
            return message.toLowerCase();
        }
        return message.toUpperCase();
    }

    private static String convertPieceMessage(final Type type) {
        return Arrays.stream(values())
                .filter(converter -> converter.type.equals(type))
                .map(converter -> converter.message)
                .findFirst()
                .orElse(PieceMessageConverter.EMPTY.message);
    }
}
