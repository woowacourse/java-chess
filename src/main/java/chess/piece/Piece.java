package chess.piece;

import chess.Color;
import chess.Position;
import java.util.List;

public abstract class Piece {

    private final Position position;
    private final Color color;

    protected Piece(final Position position, final Color color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public abstract List<Position>  calculateAvailablePositions(final Board board);

    public boolean isAvailablePosition(final Board board, final Position position) {
        return position.isAvailableMovePosition() && (board.isEmptyPosition(position) || !board.isSameTeam(position, this));
    }

    public Color getColor() {
        return color;
    }

    public abstract Piece copyOf(final Position position);

    public abstract PieceType getType();
}
