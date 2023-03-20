package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class DirectionTest {

    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"NORTH_EAST", "SOUTH_EAST", "SOUTH_WEST", "NORTH_WEST"})
    void isDiagonal(final Direction direction) {
        assertThat(direction.isDiagonal()).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"NORTH_EAST", "SOUTH_EAST", "SOUTH_WEST", "NORTH_WEST"}, mode = Mode.EXCLUDE)
    void notDiagonal(final Direction direction) {
        assertThat(direction.isDiagonal()).isFalse();
    }
}
