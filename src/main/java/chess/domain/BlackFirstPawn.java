package chess.domain;

import java.util.Set;

public class BlackFirstPawn extends Piece {
    private static Set<Direction> directions = Direction.getBlackFirstPawnDirection();

    public BlackFirstPawn(Position position) {
        super(position, Color.BLACK);
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
        return direction == Direction.LEFT_DOWN || direction == Direction.RIGHT_DOWN;
    }

    @Override
    public BlackPawn update(Position destination) {
        return new BlackPawn(destination);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BLACK_PAWN;
    }
}
