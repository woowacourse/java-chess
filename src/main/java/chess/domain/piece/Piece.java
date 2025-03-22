package chess.domain.piece;

public abstract class Piece {

    protected PieceType pieceType;
    protected Position position;
    protected Color color;

    public Piece(PieceType pieceType, Position position, Color color) {
        this.pieceType = pieceType;
        this.position = position;
        this.color = color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public boolean isExist(Row row, Column column) {
        return position.hasSameRowAndColumn(row, column);
    }
}
