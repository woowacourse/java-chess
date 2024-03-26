package chess.domain.piece;

import chess.domain.Position;
import java.util.List;
import java.util.Map;

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
        return findPathOfSingleMovePiece(source, target);
    }

    @Override
    public PieceType getOwnPieceType() {
        return PieceType.KING;
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
