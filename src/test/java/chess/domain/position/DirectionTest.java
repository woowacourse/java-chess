package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @ParameterizedTest
    @DisplayName("#isDiagonal() : should return boolean as to Position from, to")
    @MethodSource({"getCasesForIsDiagonal"})
    void isDiagonal(Position from, Position to, boolean expected) {
        boolean isDiagonal = Direction.isDiagonal(from, to);
        assertThat(isDiagonal).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForIsDiagonal() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Position.of(1,2), false),
                Arguments.of(Position.of(1,1), Position.of(2,1), false),
                Arguments.of(Position.of(1,1), Position.of(2,2), true)
        );
    }
}