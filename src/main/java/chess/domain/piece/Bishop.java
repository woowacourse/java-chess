package chess.domain.piece;

import chess.domain.Position;
import java.util.List;
import java.util.Set;

public class Bishop extends Piece {

    private static final Bishop BLACK_BISHOP = new Bishop(Color.BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(Color.WHITE);

    private Bishop(final Color color) {
        super(color);
        initDirections();
    }

    public static Bishop colorOf(final Color color) {
        if (color == Color.BLACK) {
            return BLACK_BISHOP;
        }
        return WHITE_BISHOP;
    }

    private void initDirections() {
        this.directions.addAll(Set.of(
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
        return PieceType.BISHOP.name();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
