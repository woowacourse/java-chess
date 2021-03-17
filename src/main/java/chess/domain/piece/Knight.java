package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.Vertical;

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
        final Direction direction = POSSIBLE_DIRECTIONS.stream()
                .filter(possibleDirection -> possibleDirection.isSameDirection(target.subtract(source)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("위치를 잘못 입력하셨습니다."));
        return isOpponent(piece) || piece.equals(new Blank());
    }
}
