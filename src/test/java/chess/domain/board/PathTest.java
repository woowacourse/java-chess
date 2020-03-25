package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.piece.Piece;

public class PathTest {

    @DisplayName("생성자 예외 발생 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("constructorExceptionArguments")
    void constructorExceptionTest(String message, HashMap<Position, Optional<Piece>> path, Position start,
        Position end) {
        assertThatThrownBy(() -> {
            new Path(path, start, end);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> constructorExceptionArguments() {
        return Stream.of(Fixture.EMPTY_STARTING_POINT, Fixture.PATH_EXCLUDING_START_POINT);
    }

    @DisplayName("적이 끝에 있는지 테스트")
    @Test
    void isEnemyOnEnd() {
        // given
        // when
        // then
    }

    @DisplayName("끝이 비었는지 테스트")
    @Test
    void isEndEmpty() {
        // given
        // when
        // then
    }

    @DisplayName("중간에 막혀있는지 테스트")
    @Test
    void isBlocked() {
        // given
        // when
        // then
    }
}
