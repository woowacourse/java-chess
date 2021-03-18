package chess.view.dto;

import chess.domain.piece.Piece;

public class PieceDto {

    private final String notation;
    private final int row;
    private final int column;

    public PieceDto(Piece piece) {
        this.notation = piece.getNotation();
        this.row = piece.getRow();
        this.column = piece.getColumn();
    }

    public String getNotation() {
        return notation;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
