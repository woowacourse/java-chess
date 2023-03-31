package dto;

import domain.piece.Piece;
import domain.position.Position;

import java.util.Map;

public class PieceDto {
    private final String name;
    private final String pieceColor;
    private final int row;
    private final int column;

    public PieceDto(String name, String pieceColor, int row, int column) {
        this.name = name;
        this.pieceColor = pieceColor;
        this.row = row;
        this.column = column;
    }

    public PieceDto(Piece piece, Position position) {
        this.name = piece.getName();
        this.pieceColor = piece.getColor().name();
        this.row = position.getRow();
        this.column = position.getColumn();
    }

    public String getName() {
        return name;
    }

    public String getPieceColor() {
        return pieceColor;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
