package chess.dto;

import chess.domain.piece.ChessPiece;

public class ChessPieceDto {
    private final String name;
    private final char column;
    private final int row;

    private ChessPieceDto(String name, char column, int row) {
        this.name = name;
        this.column = column;
        this.row = row;
    }

    public static ChessPieceDto of(ChessPiece chessPiece) {
        return new ChessPieceDto(
                chessPiece.getName(), chessPiece.getPosition().getColumn(), chessPiece.getPosition().getRow());
    }

    public String getName() {
        return name;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
