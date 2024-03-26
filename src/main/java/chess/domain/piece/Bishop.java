package chess.domain.piece;

import chess.domain.Position;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bishop extends Piece {

    private static final Map<Color, Bishop> CACHE = Map.ofEntries(
            Map.entry(Color.BLACK, new Bishop(Color.BLACK)),
            Map.entry(Color.WHITE, new Bishop(Color.WHITE))
    );

    private Bishop(final Color color) {
        super(color);
        initDirections();
    }

    public static Bishop of(final Color team) {
        return CACHE.get(team);
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
    public PieceType getOwnPieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public boolean canMoveMoreThenOnce() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
