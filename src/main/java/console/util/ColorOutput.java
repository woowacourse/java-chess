package console.util;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.piece.Color;

public class ColorOutput {
    private static final String WHITE_COLOR_CODE = "\u001B[31m";
    private static final String BLACK_COLOR_CODE = "\u001B[34m";
    private static final String EXIT_COLOR_CODE = "\u001B[0m";

    public static String apply(Color color, char message) {
        StringBuilder stringBuilder = new StringBuilder();

        if (color == WHITE) {
            stringBuilder.append(WHITE_COLOR_CODE);
        }

        if (color == BLACK) {
            stringBuilder.append(BLACK_COLOR_CODE);
        }

        return stringBuilder.append(message).append(EXIT_COLOR_CODE).toString();
    }
}
