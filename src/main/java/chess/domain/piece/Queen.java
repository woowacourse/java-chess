package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Piece;
import chess.domain.Team;
import chess.domain.exceptions.InvalidDirectionException;

import java.util.Arrays;
import java.util.List;

import static chess.domain.Direction.*;
import static chess.domain.Direction.NW;

public class Queen extends Piece {
    private static final String NAME = "q";
    private static final double SCORE = 9;
    private static final List<Direction> MOVABLE_DIRECTION = Arrays.asList(N, NE, E, SE, S, SW, W, NW);

    public Queen(Team team) {
        super(team, (source, target) -> {
            Direction direction = source.direction(target);

            if (MOVABLE_DIRECTION.stream().noneMatch(movable -> movable == direction)) {
                throw new InvalidDirectionException("움직일 수 있는 방향이 아닙니다.");
            }

            return true;
        });
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
