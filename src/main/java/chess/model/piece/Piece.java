package chess.model.piece;

import chess.model.position.Movement;
import chess.model.position.Position;
import java.util.List;

public abstract class Piece {
    private static final String PIECE_NAME_DELIMITER = "_";

    private final Color color;
    private final Type type;

    Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean isValid(Movement movement, Piece destination);

    public abstract List<Position> getIntermediatePositions(Movement movement);

    protected void validateDestinationColor(Piece destination) {
        if (destination.hasSameColorAs(this.color)) {
            throw new IllegalArgumentException("도착지에 같은 색깔의 기물이 있으면 움직일 수 없습니다.");
        }
    }

    private boolean hasSameColorAs(Color color) {
        return this.color == color;
    }

    public boolean hasOppositeColorTo(Color color) {
        return this.color == color.getOpposite();
    }

    protected boolean hasOppositeColorWith(Piece piece) {
        return this.color == piece.color.getOpposite();
    }

    public boolean isType(Type type) {
        return this.type == type;
    }

    public boolean isEmpty() {
        return isType(Type.NONE);
    }

    public String getName() {
        return color.name() + PIECE_NAME_DELIMITER + type.name();
    }
}
