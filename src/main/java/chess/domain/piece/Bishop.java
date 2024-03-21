package chess.domain.piece;

import java.util.Map;

public class Bishop extends Piece {

    private static final Map<Color, Bishop> CACHE = Map.ofEntries(
            Map.entry(Color.BLACK, new Bishop(Color.BLACK)),
            Map.entry(Color.WHITE, new Bishop(Color.WHITE))
    );

    private final boolean canMoveMoreThenOnce = true;

    private Bishop(final Color color) {
        super(color);
        initDirections();
    }

    public static Bishop of(final Color team) {
        return CACHE.get(team);
    }

    private void initDirections() {
        this.directions.add(Direction.LEFT_UP);
        this.directions.add(Direction.LEFT_DOWN);
        this.directions.add(Direction.RIGHT_UP);
        this.directions.add(Direction.RIGHT_DOWN);
    }

    @Override
    public boolean canMoveMoreThenOnce() {
        return canMoveMoreThenOnce;
    }
}
