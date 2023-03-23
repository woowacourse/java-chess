package chess.dto;

import chess.domain.board.Position;
import chess.domain.pieces.Piece;

public class PieceDto {

    private final char col;
    private final char row;
    private final String piece;

    private PieceDto(final char row, final char col, final String piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
    }

    public static PieceDto toDto(final Position position, final Piece piece) {
        return new PieceDto(position.getCol(), position.getRow(), piece.getName());
    }

    public char getCol() {
        return col;
    }

    public char getRow() {
        return row;
    }

    public String getPiece() {
        return piece;
    }
}
