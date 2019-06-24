package chess.application.chessround.dto;

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
}
