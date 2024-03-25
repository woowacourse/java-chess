package chess.domain.piece;

import java.util.Map;

public class Rook extends Piece {

    private static final Map<Color, Rook> CACHE = Map.ofEntries(
            Map.entry(Color.BLACK, new Rook(Color.BLACK)),
            Map.entry(Color.WHITE, new Rook(Color.WHITE))
    );

    private Rook(final Color color) {
        super(color);
        initDirections();
    }

    public static Rook of(final Color color) {
        return CACHE.get(color);
    }


    private void initDirections() {
        this.directions.add(Direction.LEFT);
        this.directions.add(Direction.RIGHT);
        this.directions.add(Direction.UP);
        this.directions.add(Direction.DOWN);
    }

    @Override
    public PieceType getOwnPieceType() {
        return PieceType.ROOK;
    }

    @Override
    public boolean canMoveMoreThenOnce() {
        return true;
    }
}
