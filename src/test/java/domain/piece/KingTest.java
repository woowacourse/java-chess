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
    void isMovablePathTest_Success(Path path) {
        // given
        King king = new King(Color.WHITE);

        // when
        boolean result = king.isMovablePath(Position.of(3, 3), path);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("isMovablePathTest_FailCase")
    @DisplayName("2칸 이상 이동할 수 없다.")
    void isMovablePathTest_Fail(Path path) {
        // given
        King king = new King(Color.WHITE);

        // when
        boolean result = king.isMovablePath(Position.of(3, 3), path);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("기본점수가 0점이다.")
    void getScoreTest() {
        // given
        King king = new King(Color.WHITE);

        // when
        double score = king.getScore();

        // then
        assertThat(score).isEqualTo(0);
    }

    static Stream<Arguments> isMovablePathTest_SuccessCase() {
        return Stream.of(
                Arguments.of(new Path(Position.of(3, 3), Position.of(4, 3))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(3, 4))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(2, 3))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(3, 2))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(4, 4))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(2, 4))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(2, 2))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(4, 2)))
        );
    }

    static Stream<Arguments> isMovablePathTest_FailCase() {
        return Stream.of(
                Arguments.of(new Path(Position.of(3, 3), Position.of(5, 3))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(3, 5))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(1, 3))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(3, 1))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(5, 5))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(1, 5))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(1, 1))),
                Arguments.of(new Path(Position.of(3, 3), Position.of(5, 1)))
        );
    }
}
