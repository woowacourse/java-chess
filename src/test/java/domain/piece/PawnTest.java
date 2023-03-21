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

@DisplayName("Pawn은 ")
class PawnTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        // when
        String name = pawn.getName();

        // then
        assertThat(name).isEqualTo("P");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        // when
        String name = pawn.getName();

        // then
        assertThat(name).isEqualTo("p");
    }

    @ParameterizedTest
    @MethodSource("isMovableInitialRowBlackPawnTestCase")
    @DisplayName("검은색일 때 초기 위치일 경우 아래, 오른쪽 아래 대각선, 왼쪽 아래 대각선으로 한 칸, 아래로 두 칸 이동 할 수 있다.")
    void isMovableInitialRowBlackPawnTest(Position end) {
        // given
        Pawn pawn = new Pawn(Color.BLACK);
        Position start = Position.of(7, 5);
        List<Position> routeFromStartToEnd = Position.getRouteOf(start, end);

        // when
        boolean result = pawn.isMovableRoute(routeFromStartToEnd);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("isMovableInitialRowWhitePawnTestCase")
    @DisplayName("흰색일 때 초기 위치일 경우 위, 오른쪽 위 대각선, 왼쪽 위 대각선으로 한 칸, 위로 두 칸 이동 할 수 있다.")
    void isMovableInitialRowWhitePawnTest(Position end) {
        // given
        Pawn pawn = new Pawn(Color.WHITE);
        Position start = Position.of(2, 5);
        List<Position> routeFromStartToEnd = Position.getRouteOf(start, end);

        // when
        boolean result = pawn.isMovableRoute(routeFromStartToEnd);

        // then
        assertThat(result).isTrue();
    }

    static Stream<Arguments> isMovableInitialRowBlackPawnTestCase() {
        return Stream.of(
                Arguments.of(Position.of(6, 5)),
                Arguments.of(Position.of(6, 4)),
                Arguments.of(Position.of(6, 6)),
                Arguments.of(Position.of(6, 5))
        );
    }

    static Stream<Arguments> isMovableInitialRowWhitePawnTestCase() {
        return Stream.of(
                Arguments.of(Position.of(3, 5)),
                Arguments.of(Position.of(3, 4)),
                Arguments.of(Position.of(3, 6)),
                Arguments.of(Position.of(3, 5))
        );
    }
}
