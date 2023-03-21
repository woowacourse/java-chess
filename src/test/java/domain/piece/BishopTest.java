package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

@DisplayName("Bishop은 ")
class BishopTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        Bishop bishop = new Bishop(Color.BLACK);

        // when
        String name = bishop.getName();

        // then
        assertThat(name).isEqualTo("B");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);

        // when
        String name = bishop.getName();

        // then
        assertThat(name).isEqualTo("b");
    }

    @ParameterizedTest
    @MethodSource("isMovablePathTest_SuccessCase")
    @DisplayName("대각선에 위치한 모든 칸으로 이동할 수 있다.")
    void isMovablePathTest_Success(Position end) {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        Position start = Position.of(3, 3);
        List<Position> routeFromStartToEnd = Position.getRouteOf(start, end);

        // when
        boolean result = bishop.isMovableRoute(routeFromStartToEnd);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("isMovablePathTest_FailCase")
    @DisplayName("상하좌우에 위치한 모든 칸으로 이동할 수 없다.")
    void isMovablePathTest_Fail(Position end) {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        Position start = Position.of(3, 3);
        List<Position> routeFromStartToEnd = Position.getRouteOf(start, end);

        // when
        boolean result = bishop.isMovableRoute(routeFromStartToEnd);

        // then
        assertThat(result).isFalse();
    }

    static Stream<Arguments> isMovablePathTest_SuccessCase() {
        return Stream.of(
                Arguments.of(Position.of(5, 5)),
                Arguments.of(Position.of(1, 5)),
                Arguments.of(Position.of(1, 1)),
                Arguments.of(Position.of(5, 1))
        );
    }

    static Stream<Arguments> isMovablePathTest_FailCase() {
        return Stream.of(
                Arguments.of(Position.of(5, 3)),
                Arguments.of(Position.of(3, 5)),
                Arguments.of(Position.of(1, 3)),
                Arguments.of(Position.of(3, 1))
        );
    }
}
