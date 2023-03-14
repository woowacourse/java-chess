package chess.domain;

public class Piece {
    private final PieceType type;
    private final Color color;

    public Piece(PieceType pieceType, Color color) {
        type = pieceType;
        this.color = color;
    }

    public Piece(String type, Color color) {
        this(PieceType.from(type), color);
    }

    public Piece() {
        this(PieceType.EMPTY, Color.NONE);
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
