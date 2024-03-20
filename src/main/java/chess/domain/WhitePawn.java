package chess.domain;

import java.util.Set;

public class WhitePawn extends Pawn {
    private static Set<Direction> DIRECTIONS = Set.of(Direction.UP, Direction.LEFT_UP, Direction.RIGHT_UP);

    public WhitePawn(Position position) {
        super(position, Color.WHITE, DIRECTIONS);
    }

    public boolean isCaptureMove(Position destination) {
        Direction direction = position.findDirectionTo(destination);
        return direction == Direction.LEFT_UP || direction == Direction.RIGHT_UP;
    }

    @Override
    public WhitePawn update(Position destination) {
        return new WhitePawn(destination);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.WHITE_PAWN;
    }
}
