package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.piece.Piece;

public class PathTest {

    @DisplayName("생성자 예외 발생 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("constructorExceptionArguments")
    void constructorExceptionTest(String message, HashMap<Position, Piece> path, Position start,
        Position end) {
        assertThatThrownBy(() -> {
            new Path(path, start, end);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> constructorExceptionArguments() {
        return Stream.of(Fixture.EMPTY_STARTING_POINT, Fixture.PATH_EXCLUDING_START_POINT);
    }

    @DisplayName("적이 끝에 있는지 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("isEnemyOnEndParams")
    void isEnemyOnEnd(String message, HashMap<Position, Piece> path, Position start,
        Position end, boolean expected) {
        assertThat(new Path(path, start, end).isEnemyOnEnd()).isEqualTo(expected);
    }

    static Stream<Arguments> isEnemyOnEndParams() {
        return Stream.of(Fixture.ENEMY_ON_END_POINT, Fixture.NO_ENEMY_ON_END_POINT,
            Fixture.NO_ENEMY_OR_ALLY_ON_END_POINT);
    }

    @DisplayName("끝이 비었는지 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("isEndEmptyParams")
    void isEndEmpty(String message, HashMap<Position, Piece> path, Position start,
        Position end, boolean expected) {
        assertThat(new Path(path, start, end).isEndEmpty()).isEqualTo(expected);
    }

    static Stream<Arguments> isEndEmptyParams() {
        return Stream.of(Fixture.EMPTY_ON_END_POINT, Fixture.NOT_EMPTY_ON_END_POINT);
    }

    @DisplayName("중간에 막혀있는지 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("isBlockedParams")
    void isBlocked(String message, HashMap<Position, Piece> path, Position start,
        Position end, boolean expected) {
        assertThat(new Path(path, start, end).isBlocked()).isEqualTo(expected);
    }

    static Stream<Arguments> isBlockedParams() {
        return Stream.of(Fixture.BLOCKED_PATH, Fixture.NOT_BLOCKED_PATH);
    }
}
