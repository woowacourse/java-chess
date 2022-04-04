package chess.controller.dto;

import chess.domain.piece.Piece;
import java.util.Locale;

public class PieceResponse {

    private final String name;
    private final String color;

    private PieceResponse(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public static PieceResponse from(Piece piece) {
        return new PieceResponse(piece.name(), toLowerName(piece.color().name()));
    }

    private static String toLowerName(String name) {
        return name.toLowerCase(Locale.ROOT);
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
