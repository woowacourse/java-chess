package chess.domain.piece;

import chess.domain.Position;
import java.util.List;
import java.util.Set;

public class Rook extends Piece {

    private static final Rook BLACK_ROOK = new Rook(Color.BLACK);
    private static final Rook WHITE_ROOK = new Rook(Color.WHITE);

    private Rook(final Color color) {
        super(color);
        initDirections();
    }

    public static Rook colorOf(final Color color) {
        if (color == Color.BLACK) {
            return BLACK_ROOK;
        }
        return WHITE_ROOK;
    }

    private void initDirections() {
        this.directions.addAll(Set.of(
                Direction.LEFT,
                Direction.RIGHT,
                Direction.UP,
                Direction.DOWN));
    }

    @Override
    public List<Position> findPath(final Position source, final Position target) {
        return findPathOfMultipleMovePiece(source, target);
    }

    @Override
    public String getOwnPieceTypeName() {
        return PieceType.ROOK.name();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
