package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    private static final List<Direction> POSSIBLE_DIRECTIONS = Arrays.asList(Direction.NNE, Direction.NNW, Direction.SSE,
            Direction.SSW, Direction.WWN, Direction.WWS, Direction.EEN, Direction.EES);
    private static final String INITIAL_NAME = "N";

    public Knight(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }

    @Override
    boolean canMove(final Position source, final Position target, final Piece piece) {
        if (!isPossibleDirection(source, target)) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
        return isOpponent(piece) || piece.equals(new Blank());
    }

    private boolean isPossibleDirection(final Position source, final  Position target) {
        return POSSIBLE_DIRECTIONS.stream()
                .anyMatch(possibleDirection -> possibleDirection.isSameDirection(target.subtract(source)));
    }
}
