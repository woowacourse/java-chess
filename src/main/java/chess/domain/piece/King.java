package chess.domain.piece;

import chess.domain.Position;
import java.util.List;
import java.util.Set;

public class King extends Piece {

    private static final King BLACK_KING = new King(Color.BLACK);
    private static final King WHITE_KING = new King(Color.WHITE);

    private King(final Color color) {
        super(color);
        initDirections();
    }

    public static King colorOf(final Color color) {
        if (color == Color.BLACK) {
            return BLACK_KING;
        }
        return WHITE_KING;
    }

    private void initDirections() {
        this.directions.addAll(Set.of(
                Direction.LEFT,
                Direction.RIGHT,
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT_UP,
                Direction.LEFT_DOWN,
                Direction.RIGHT_UP,
                Direction.RIGHT_DOWN
        ));
    }

    @Override
    public List<Position> findPath(final Position source, final Position target) {
        return findPathOfSingleMovePiece(source, target);
    }

    @Override
    public String getOwnPieceTypeName() {
        return PieceType.KING.name();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
