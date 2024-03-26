package chess.domain.piece;

import chess.domain.Position;
import java.util.List;
import java.util.Set;

public class Queen extends Piece {

    private static final Queen BLACK_QUEEN = new Queen(Color.BLACK);
    private static final Queen WHITE_QUEEN = new Queen(Color.WHITE);

    private Queen(final Color color) {
        super(color);
        initDirections();
    }

    public static Queen colorOf(final Color color) {
        if (color == Color.BLACK) {
            return BLACK_QUEEN;
        }
        return WHITE_QUEEN;
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
                Direction.RIGHT_DOWN));
    }

    @Override
    public List<Position> findPath(final Position source, final Position target) {
        return findPathOfMultipleMovePiece(source, target);
    }

    @Override
    public String getOwnPieceTypeName() {
        return PieceType.QUEEN.name();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
