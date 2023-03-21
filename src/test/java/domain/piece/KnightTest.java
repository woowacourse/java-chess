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

@DisplayName("Knight는 ")
class KnightTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        Knight knight = new Knight(Color.BLACK);

        // when
        String name = knight.getName();

        // then
        assertThat(name).isEqualTo("N");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        Knight knight = new Knight(Color.WHITE);

        // when
        String name = knight.getName();

        // then
        assertThat(name).isEqualTo("n");
    }

    // 이동가능한 8방향 성공 테스트
    // 이동 불가능한 좌표가 주어졌을 때 실패 테스트

    @ParameterizedTest
    @MethodSource("isMovablePathTest_SuccessCase")
    @DisplayName("상하 중 한 방향으로 2칸 이동 후 좌우로 1칸 또는 좌우 중 한 방향으로 2칸 이동 후 상하로 1칸 이동할 수 있다.")
    void isMovablePathTest_Success(Position end) {
        // given
        Knight knight = new Knight(Color.WHITE);
        Position start = Position.of(4, 4);
        List<Position> routeFromStartToEnd = Position.getRouteOf(start, end);

        // when
        boolean result = knight.isMovableRoute(routeFromStartToEnd);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("상하 중 한 방향으로 2칸 이동 후 좌우로 1칸 또는 좌우 중 한 방향으로 2칸 이동 후 상하로 1칸이 아닌 좌표로는 이동할 수 없다.")
    void isMovablePathTest_Fail() {
        // given
        Knight knight = new Knight(Color.WHITE);
        Position start = Position.of(4, 4);
        Position end = Position.of(5, 5);
        List<Position> routeFromStartToEnd = Position.getRouteOf(start, end);

        // when
        boolean result = knight.isMovableRoute(routeFromStartToEnd);

        // then
        assertThat(result).isFalse();
    }

    static Stream<Arguments> isMovablePathTest_SuccessCase() {
        return Stream.of(
                Arguments.of(Position.of(6, 3)),
                Arguments.of(Position.of(6, 5)),
                Arguments.of(Position.of(5, 2)),
                Arguments.of(Position.of(5, 6)),
                Arguments.of(Position.of(3, 2)),
                Arguments.of(Position.of(3, 6)),
                Arguments.of(Position.of(2, 3)),
                Arguments.of(Position.of(2, 5))
        );
    }
}
