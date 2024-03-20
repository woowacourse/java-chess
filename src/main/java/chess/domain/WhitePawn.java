package chess.domain;

import java.util.Set;

public class WhitePawn extends Piece {
    private static Set<Direction> directions = Set.of(Direction.UP, Direction.LEFT_UP, Direction.RIGHT_UP);

    public WhitePawn(Position position) {
        super(position, Color.WHITE);
    }

    @Override
    public Set<Position> findMovablePositions(Position destination) {
        Set<Position> movable = position.findMovablePositions(directions);

        if (movable.contains(destination)) {
            return Set.of(destination);
        }
        throw new IllegalArgumentException("이동할 수 없습니다.");
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
