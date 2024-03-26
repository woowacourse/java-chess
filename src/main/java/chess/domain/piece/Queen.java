package chess.domain.piece;

import chess.domain.Position;
import java.util.List;
import java.util.Map;

public class Queen extends Piece {

    private static final Map<Color, Queen> CACHE = Map.ofEntries(
            Map.entry(Color.BLACK, new Queen(Color.BLACK)),
            Map.entry(Color.WHITE, new Queen(Color.WHITE))
    );

    private Queen(final Color color) {
        super(color);
        initDirections();
    }

    public static Queen of(final Color color) {
        return CACHE.get(color);
    }

    private void initDirections() {
        this.directions.add(Direction.LEFT);
        this.directions.add(Direction.RIGHT);
        this.directions.add(Direction.UP);
        this.directions.add(Direction.DOWN);
        this.directions.add(Direction.LEFT_UP);
        this.directions.add(Direction.LEFT_DOWN);
        this.directions.add(Direction.RIGHT_UP);
        this.directions.add(Direction.RIGHT_DOWN);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target) {
        return findPathOfMultipleMovePiece(source, target);
    }

    @Override
    public PieceType getOwnPieceType() {
        return PieceType.QUEEN;
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
