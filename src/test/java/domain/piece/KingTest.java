package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

@DisplayName("King은 ")
class KingTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        King king = new King(Color.BLACK);

        // when
        String name = king.getName();

        // then
        assertThat(name).isEqualTo("K");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        King king = new King(Color.WHITE);

        // when
        String name = king.getName();

        // then
        assertThat(name).isEqualTo("k");
    }

    @ParameterizedTest
    @MethodSource("isMovablePathTest_SuccessCase")
    @DisplayName("모든 방향으로 1칸 이동할 수 있다.")
    void isMovablePathTest_Success(Position end) {
        // given
        King king = new King(Color.WHITE);
        Position start = Position.of(3, 3);
        List<Position> routeFromStartToEnd = Position.getRouteOf(start, end);

        // when
        boolean result = king.isMovableRoute(routeFromStartToEnd);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("isMovablePathTest_FailCase")
    @DisplayName("2칸 이상 이동할 수 없다.")
    void isMovablePathTest_Fail(Position end) {
        // given
        King king = new King(Color.WHITE);
        Position start = Position.of(3, 3);
        List<Position> routeFromStartToEnd = Position.getRouteOf(start, end);

        // when
        boolean result = king.isMovableRoute(routeFromStartToEnd);

        // then
        assertThat(result).isFalse();
    }

    static Stream<Arguments> isMovablePathTest_SuccessCase() {
        return Stream.of(
                Arguments.of(Position.of(4, 3)),
                Arguments.of(Position.of(3, 4)),
                Arguments.of(Position.of(2, 3)),
                Arguments.of(Position.of(3, 2)),
                Arguments.of(Position.of(4, 4)),
                Arguments.of(Position.of(2, 4)),
                Arguments.of(Position.of(2, 2)),
                Arguments.of(Position.of(4, 2))
        );
    }

    static Stream<Arguments> isMovablePathTest_FailCase() {
        return Stream.of(
                Arguments.of(Position.of(5, 3)),
                Arguments.of(Position.of(3, 5)),
                Arguments.of(Position.of(1, 3)),
                Arguments.of(Position.of(3, 1)),
                Arguments.of(Position.of(5, 5)),
                Arguments.of(Position.of(1, 5)),
                Arguments.of(Position.of(1, 1)),
                Arguments.of(Position.of(5, 1))
        );
    }
}
