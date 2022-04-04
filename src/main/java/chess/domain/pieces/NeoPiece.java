package chess.domain.pieces;

public class NeoPiece {

    private final int id;
    private final Type type;
    private final Color color;
    private final int positionId;

    public NeoPiece(int id, Type type, Color color, int positionId) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.positionId = positionId;
    }

    public NeoPiece(Type type, Color color, int positionId) {
        this(0, type, color, positionId);
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public int getPositionId() {
        return positionId;
    }
}
