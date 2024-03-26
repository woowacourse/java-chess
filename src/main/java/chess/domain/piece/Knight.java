package chess.domain.piece;

import chess.domain.Position;
import java.util.List;
import java.util.Map;

public class Knight extends Piece {

    private static final Map<Color, Knight> CACHE = Map.ofEntries(
            Map.entry(Color.BLACK, new Knight(Color.BLACK)),
            Map.entry(Color.WHITE, new Knight(Color.WHITE))
    );

    private Knight(final Color color) {
        super(color);
        initDirections();
    }

    public static Knight of(final Color color) {
        return CACHE.get(color);
    }

    private void initDirections() {
        this.directions.add(Direction.KNIGHT_LEFT_UP);
        this.directions.add(Direction.KNIGHT_LEFT_DOWN);
        this.directions.add(Direction.KNIGHT_RIGHT_UP);
        this.directions.add(Direction.KNIGHT_RIGHT_DOWN);
        this.directions.add(Direction.KNIGHT_UP_LEFT);
        this.directions.add(Direction.KNIGHT_UP_RIGHT);
        this.directions.add(Direction.KNIGHT_DOWN_LEFT);
        this.directions.add(Direction.KNIGHT_DOWN_RIGHT);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target) {
        return findPathOfSingleMovePiece(source, target);
    }

    @Override
    public PieceType getOwnPieceType() {
        return PieceType.KNIGHT;
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
