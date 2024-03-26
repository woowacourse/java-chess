package chess.domain.piece;

import chess.domain.Position;
import java.util.List;
import java.util.Set;

public class Knight extends Piece {

    private static final Knight BLACK_KNIGHT = new Knight(Color.BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(Color.WHITE);

    private Knight(final Color color) {
        super(color);
        initDirections();
    }

    public static Knight colorOf(final Color color) {
        if (color == Color.BLACK) {
            return BLACK_KNIGHT;
        }
        return WHITE_KNIGHT;
    }

    private void initDirections() {
        this.directions.addAll(Set.of(
                Direction.KNIGHT_LEFT_UP,
                Direction.KNIGHT_LEFT_DOWN,
                Direction.KNIGHT_RIGHT_UP,
                Direction.KNIGHT_RIGHT_DOWN,
                Direction.KNIGHT_UP_LEFT,
                Direction.KNIGHT_UP_RIGHT,
                Direction.KNIGHT_DOWN_LEFT,
                Direction.KNIGHT_DOWN_RIGHT));
    }

    @Override
    public List<Position> findPath(final Position source, final Position target) {
        return findPathOfSingleMovePiece(source, target);
    }

    @Override
    public String getOwnPieceTypeName() {
        return PieceType.KNIGHT.name();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
