package chess.domain.piece;

import java.util.Map;

public class Pawn extends Piece {

    private static final Map<Color, Pawn> CACHE = Map.ofEntries(
            Map.entry(Color.BLACK, new Pawn(Color.BLACK)),
            Map.entry(Color.WHITE, new Pawn(Color.WHITE))
    );

    private Pawn(final Color color) {
        super(color);
        initDirections();
    }

    public static Pawn of(final Color team) {
        return CACHE.get(team);
    }

    private void initDirections() {
        if (color == Color.BLACK) {
            this.directions.add(Direction.DOWN);
        }
        if (color == Color.WHITE) {
            this.directions.add(Direction.UP);
        }
    }

    @Override
    public PieceType getOwnPieceType() {
        return PieceType.PAWN;
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
