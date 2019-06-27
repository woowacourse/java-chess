package chess.application.chessround.dto;

import java.util.Objects;

public class ChessPieceDTO {
    private int row;
    private int column;
    private String name;

    public ChessPieceDTO(int row, int column, String name) {
        this.row = row;
        this.column = column;
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ChessPieceDTO that = (ChessPieceDTO) o;
        return row == that.row &&
                column == that.column &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, name);
    }
}
