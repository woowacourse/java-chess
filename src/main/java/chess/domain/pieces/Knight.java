package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.strategy.KnightDirection;
import java.util.List;

public class Knight extends Piece {

    private static final List<KnightDirection> KNIGHT_DIRECTIONS = List.of(
        KnightDirection.KNIGHT_UP_RIGHT,
        KnightDirection.KNIGHT_UP_LEFT,
        KnightDirection.KNIGHT_RIGHT_UP,
        KnightDirection.KNIGHT_RIGHT_DOWN,
        KnightDirection.KNIGHT_DOWN_RIGHT,
        KnightDirection.KNIGHT_DOWN_LEFT,
        KnightDirection.KNIGHT_LEFT_UP,
        KnightDirection.KNIGHT_LEFT_DOWN
    );

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final Position source, final Position destination) {
        validateMoveDirection(source, destination);
        validateRangeOfMove(source, destination);
    }

    private void validateMoveDirection(final Position source, final Position destination) {
        KNIGHT_DIRECTIONS.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Knight는 "));
    }

    private void validateRangeOfMove(final Position source, final Position destination) {
        int absSubOfCol = Math.abs(destination.calculateDistanceOfCol(source));
        int absSubOfRow = Math.abs(destination.calculateDistanceOfRow(source));
        if ((absSubOfRow == 2 && absSubOfCol != 1) || (absSubOfRow == 1 && absSubOfCol != 2)) {
            throw new IllegalArgumentException("Knight의 올바른 이동범위가 아닙니다.");
        }
    }
}
