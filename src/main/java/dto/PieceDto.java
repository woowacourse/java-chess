package dto;

public class PieceDto {
    private final String name;
    private final String pieceColor;
    private final int row;
    private final int column;
    private final String colorOfTurn;

    public PieceDto(String name, String pieceColor, int row, int column, String colorOfTurn) {
        this.name = name;
        this.pieceColor = pieceColor;
        this.row = row;
        this.column = column;
        this.colorOfTurn = colorOfTurn;
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

    public String getColorOfTurn() {
        return colorOfTurn;
    }
}
