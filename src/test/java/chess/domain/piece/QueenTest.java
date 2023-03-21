package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Queen은 ")
class QueenTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        Queen queen = new Queen(Color.BLACK);

        // when
        String name = queen.getName();

        // then
        assertThat(name).isEqualTo("Q");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        Queen queen = new Queen(Color.WHITE);

        // when
        String name = queen.getName();

        // then
        assertThat(name).isEqualTo("q");
    }

    @ParameterizedTest
    @MethodSource("isMovablePathTest_SuccessCase")
    @DisplayName("모든 방향에 위치한 모든 칸으로 이동할 수 있다.")
    void isMovablePathTest_Success(Position end) {
        // given
        Queen queen = new Queen(Color.WHITE);
        Position start = Position.of(3, 3);
        List<Position> routeFromStartToEnd = Position.getRouteOf(start, end);

        // when
        boolean result = queen.isMovableRoute(routeFromStartToEnd);

        // then
        assertThat(result).isTrue();
    }

    static Stream<Arguments> isMovablePathTest_SuccessCase() {
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
