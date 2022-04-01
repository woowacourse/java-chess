package chess.model.position;

import static chess.model.position.File.A;
import static chess.model.position.File.B;
import static chess.model.position.File.C;
import static chess.model.position.File.F;
import static chess.model.position.File.H;
import static chess.model.position.Rank.EIGHT;
import static chess.model.position.Rank.FIVE;
import static chess.model.position.Rank.ONE;
import static chess.model.position.Rank.SEVEN;
import static chess.model.position.Rank.SIX;
import static chess.model.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class PositionTest {

    @ParameterizedTest(name = "key값을 통해 Position을 반환하는지 확인한다.")
    @MethodSource({"generatePosition"})
    void from(final Position ofPosition, final Position fromPosition) {
        assertThat(ofPosition).isEqualTo(fromPosition);
    }

    static Stream<Arguments> generatePosition() {
        return Stream.of(
                Arguments.of(Position.of(FIVE, A), Position.from("a5")),
                Arguments.of(Position.of(SEVEN, B), Position.from("b7")),
                Arguments.of(Position.of(ONE, A), Position.from("a1")),
                Arguments.of(Position.of(EIGHT, H), Position.from("h8")),
                Arguments.of(Position.of(ONE, H), Position.from("h1")),
                Arguments.of(Position.of(EIGHT, A), Position.from("a8"))
        );
    }

    @DisplayName("폰의 게임 시작 위치가 맟으면 false를 반환한다.")
    @Test
    void isNotStartLocation_false() {
        Position position = Position.from("b2");
        assertThat(position.isNotStartLocation()).isFalse();
    }

    @DisplayName("폰의 게임 시작 위치가 아니면 true를 반환한다.")
    @Test
    void isNotStartLocation_true() {
        Position position = Position.from("h8");
        assertThat(position.isNotStartLocation()).isTrue();
    }

    @DisplayName("같은 File위치이면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource({"generatePosition_2"})
    void isSameFile_true(final Position actual, final Position expected) {
        assertThat(actual.isSameFile(expected)).isTrue();
    }

    static Stream<Arguments> generatePosition_2() {
        return Stream.of(
                Arguments.of(Position.of(FIVE, A), Position.from("a5")),
                Arguments.of(Position.of(SEVEN, B), Position.from("b7")),
                Arguments.of(Position.of(ONE, A), Position.from("a1")),
                Arguments.of(Position.of(EIGHT, H), Position.from("h8")),
                Arguments.of(Position.of(ONE, H), Position.from("h1")),
                Arguments.of(Position.of(EIGHT, A), Position.from("a8")),
                Arguments.of(Position.of(FIVE, A), Position.from("a8")),
                Arguments.of(Position.of(TWO, A), Position.from("a8")),
                Arguments.of(Position.of(SEVEN, C), Position.from("c3")),
                Arguments.of(Position.of(SIX, F), Position.from("f8"))
        );
    }

    @DisplayName("다른 File위치이면 false를 반환한다.")
    @Test
    void isSameFile_false() {
        Position actual = Position.of(FIVE, A);
        Position expected = Position.from("b5");
        assertThat(actual.isSameFile(expected)).isFalse();
    }
}
