package chess.dto;

import chess.domain.ChessPiece;
import chess.domain.Type;

public class ChessPieceDto {
    private final Type type;
    private final char column;
    private final int row;

    private ChessPieceDto(Type type, char column, int row) {
        this.type = type;
        this.column = column;
        this.row = row;
    }

    public static ChessPieceDto of(ChessPiece chessPiece) {
        return new ChessPieceDto(
                chessPiece.getType(), chessPiece.getPosition().getColumn(), chessPiece.getPosition().getRow());
    }

    public Type getType() {
        return type;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
