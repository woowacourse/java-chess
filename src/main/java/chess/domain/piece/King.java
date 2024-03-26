package chess.domain.piece;

import chess.domain.Position;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class King extends Piece {

    private static final Map<Color, King> CACHE = Map.ofEntries(
            Map.entry(Color.BLACK, new King(Color.BLACK)),
            Map.entry(Color.WHITE, new King(Color.WHITE))
    );

    private King(final Color color) {
        super(color);
        initDirections();
    }

    public static King of(final Color team) {
        return CACHE.get(team);
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
    public boolean canMoveMoreThenOnce() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
