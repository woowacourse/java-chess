package chess.console.view.boardview;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Color;
import java.util.Arrays;

public enum PieceSymbol {

    BLACK_PAWN("♗", BLACK, "Pawn"),
    WHITE_PAWN("♝", WHITE, "Pawn"),

    BLACK_KNIGHT("♘", BLACK, "Knight"),
    WHITE_KNIGHT("♞", WHITE, "Knight"),

    BLACK_BISHOP("♙", BLACK, "Bishop"),
    WHITE_BISHOP("♟", WHITE, "Bishop"),

    BLACK_ROOK("♖", BLACK, "Rook"),
    WHITE_ROOK("♜", WHITE, "Rook"),

    BLACK_QUEEN("♕", BLACK, "Queen"),
    WHITE_QUEEN("♛", WHITE, "Queen"),

    BLACK_KING("♔", BLACK, "King"),
    WHITE_KING("♚", WHITE, "King");

    private static final String EMPTY_SQUARE_DISPLAY = ".";

    private final String symbol;
    private final Color color;
    private final String name;

    PieceSymbol(String symbol, Color color, String name) {
        this.symbol = symbol;
        this.color = color;
        this.name = name;
    }

    public static String findSymbol(Color color, String name) {
        return Arrays.stream(values())
            .filter(pieceSymbol -> pieceSymbol.color.isSameColor(color))
            .filter(pieceSymbol -> pieceSymbol.name.equals(name))
            .map(pieceSymbol -> pieceSymbol.symbol)
            .findFirst()
            .orElse(EMPTY_SQUARE_DISPLAY);
    }

}
