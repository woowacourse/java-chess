package chess.controller.dto.response;

import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Locale;
import java.util.Map.Entry;

public class PieceResponse {

    private final String position;
    private final String name;
    private final String color;

    private PieceResponse(String position, String name, String color) {
        this.position = position;
        this.name = name;
        this.color = color;
    }

    public static PieceResponse from(Entry<Position, Piece> pieceEntry) {
        Position position = pieceEntry.getKey();
        Piece piece = pieceEntry.getValue();
        return new PieceResponse(positionName(position), piece.name(), toLowerName(piece.color().name()));
    }

    private static String positionName(Position position) {
        return String.format("%c%c", position.column(), position.row());
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
