package chess.dto;

public class BoardData {
    private String chessPieceType;
    private int column;
    private int row;

    private BoardData(String chessPieceType, int column, int row) {
        this.chessPieceType = chessPieceType;
        this.column = column;
        this.row = row;
    }

    public static BoardData of(String chessPieceType, int column, int row) {
        return new BoardData(chessPieceType, column, row);
    }

    public String getChessPieceType() {
        return chessPieceType;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
