package chess.dto.response;

import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.Shape;

public class PieceResponse {

    private final int rank;
    private final char file;
    private final char name;

    public PieceResponse(final Piece piece, final Color color) {
        this.rank = piece.getRank();
        this.file = piece.getFile();
        this.name = namingByColor(piece, color);
    }

    private char namingByColor(final Piece piece, final Color color) {
        Shape shape = piece.getShape();
        if (color == Color.WHITE) {
            return getShapeSymbol(shape, shape.name().toLowerCase());
        }
        return getShapeSymbol(shape, shape.name());
    }

    private static char getShapeSymbol(final Shape shape, final String shapeName) {
        if (shape == Shape.KNIGHT) {
            return shapeName.charAt(1);
        }
        return shapeName.charAt(0);
    }

    public boolean samePosition(final int rank, final int file) {
        return this.rank == rank && this.file == file;
    }

    public String getName() {
        return String.valueOf(this.name);
    }

}
