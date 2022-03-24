package chess.domain.position;

import chess.domain.position.movestrategy.East;
import chess.domain.position.movestrategy.MoveStrategy;
import chess.domain.position.movestrategy.None;
import chess.domain.position.movestrategy.North;
import chess.domain.position.movestrategy.NorthEast;
import chess.domain.position.movestrategy.NorthWest;
import chess.domain.position.movestrategy.South;
import chess.domain.position.movestrategy.SouthEast;
import chess.domain.position.movestrategy.SouthWest;
import chess.domain.position.movestrategy.West;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {

    EAST(((file, rank) -> file > 0 && rank == 0), new East()),
    WEST(((file, rank) -> file < 0 && rank == 0), new West()),
    NORTH(((file, rank) -> file == 0 && rank > 0), new North()),
    SOUTH(((file, rank) -> file == 0 && rank < 0), new South()),
    NORTH_EAST(((file, rank) -> file > 0 && rank > 0 && file == rank), new NorthEast()),
    NORTH_WEST(((file, rank) -> file < 0 && rank > 0 && -file == rank), new NorthWest()),
    SOUTH_EAST(((file, rank) -> file > 0 && rank < 0 && -file == rank), new SouthEast()),
    SOUTH_WEST(((file, rank) -> file < 0 && rank < 0 && file == rank), new SouthWest()),
    KNIGHT_EAST_LEFT(((file, rank) -> file == 2 && rank == 1), new None()),
    KNIGHT_EAST_RIGHT(((file, rank) -> file == 2 && rank == -1), new None()),
    KNIGHT_WEST_LEFT(((file, rank) -> file == -2 && rank == -1), new None()),
    KNIGHT_WEST_RIGHT(((file, rank) -> file == -2 && rank == 1), new None()),
    KNIGHT_NORTH_LEFT(((file, rank) -> file == -1 && rank == 2), new None()),
    KNIGHT_NORTH_RIGHT(((file, rank) -> file == 1 && rank == 2), new None()),
    KNIGHT_SOUTH_LEFT(((file, rank) -> file == 1 && rank == -2), new None()),
    KNIGHT_SOUTH_RIGHT(((file, rank) -> file == -1 && rank == -2), new None()),
    NONE(((file, rank) -> false), new None()),
    ;

    private final BiPredicate<Integer, Integer> condition;
    private final MoveStrategy moveStrategy;

    Direction(final BiPredicate<Integer, Integer> condition,
        final MoveStrategy moveStrategy) {
        this.condition = condition;
        this.moveStrategy = moveStrategy;
    }

    public static Direction of(final int fileDistance, final int rankDistance) {
        return Arrays.stream(values())
            .filter(value -> value.condition.test(fileDistance, rankDistance))
            .findFirst()
            .orElse(NONE);
    }

    public Position move(final Position from) {
        return from.move(moveStrategy);
    }
}
