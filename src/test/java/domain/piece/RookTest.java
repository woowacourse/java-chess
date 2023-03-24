package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Path;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Rook은 ")
class RookTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        Rook rook = new Rook(Color.BLACK);

        // when
        String name = rook.getName();

        // then
        assertThat(name).isEqualTo("R");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        Rook rook = new Rook(Color.WHITE);

        // when
        String name = rook.getName();

        // then
        assertThat(name).isEqualTo("r");
    }

    @ParameterizedTest
    @MethodSource("isMovablePathTest_SuccessCase")
    @DisplayName("상하좌우에 위치한 모든 칸으로 이동할 수 있다.")
    void isMovablePathTest_Success(Path path) {
        // given
        Rook rook = new Rook(Color.WHITE);

        // when
        boolean result = rook.isMovablePath(Position.of(3, 3), path);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("isMovablePathTest_FailCase")
    @DisplayName("대각선에 위치한 모든 칸으로 이동할 수 없다.")
    void isMovablePathTest_Fail(Path path) {
        // given
        Rook rook = new Rook(Color.WHITE);

        // when
        boolean result = rook.isMovablePath(Position.of(3, 3), path);

        // then
        assertThat(result).isFalse();
    }

    static Stream<Arguments> isMovablePathTest_SuccessCase() {
        return Stream.of(
                Arguments.of(new Path(Position.of(3, 3), Position.of(5, 3))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(3, 5))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(1, 3))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(3, 1)))
        );
    }

    static Stream<Arguments> isMovablePathTest_FailCase() {
        return Stream.of(
                Arguments.of(new Path(Position.of(3, 3), Position.of(5, 5))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(1, 5))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(1, 1))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(5, 1)))
        );
    }
}
