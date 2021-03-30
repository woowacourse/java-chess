package chess.view.dto;

import chess.domain.piece.Piece;

public class PieceDto {
    private static final String[] COLUMN_VALUE = {"a", "b", "c", "d", "e", "f", "g", "h"};
    private static final int[] ROW_VALUE = {8, 7, 6, 5, 4, 3, 2, 1};

    private final String notation;
    private final int row;
    private final int column;
    private final String position;

    public PieceDto(Piece piece) {
        this.notation = piece.getNotation();
        this.row = piece.getRow();
        this.column = piece.getColumn();
        this.position = COLUMN_VALUE[column] + ROW_VALUE[row];
    }

    public String getPosition() {
        return position;
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
