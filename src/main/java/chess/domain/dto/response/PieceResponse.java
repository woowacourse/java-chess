package chess.domain.dto.response;

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

    private char namingByColor(Piece piece, Color color) {
        Shape shape = piece.getShape();
        if (color == Color.WHITE) {
            return shape.name().toLowerCase().charAt(0);
        }
        return shape.name().charAt(0);
    }

    public boolean samePosition(final int rank, final int file) {
        return this.rank == rank && this.file == file;
    }

    public String getName() {
        return String.valueOf(this.name);
    }

}
