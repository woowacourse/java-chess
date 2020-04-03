package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.board.Column.*;
import static org.assertj.core.api.Assertions.assertThat;

class ColumnTest {

    @ParameterizedTest
    @DisplayName("세로축에 대해 대칭")
    @MethodSource("createOppositeCase")
    void opposite(Column actual, Column expected) {
        assertThat(actual.opposite()).isEqualTo(expected);
    }

    static Stream<Arguments> createOppositeCase() {
        return Stream.of(
                Arguments.of(A, H),
                Arguments.of(B, G),
                Arguments.of(C, F),
                Arguments.of(D, E),
                Arguments.of(E, D),
                Arguments.of(F, C),
                Arguments.of(G, B),
                Arguments.of(H, A)
        );
    }

    @ParameterizedTest
    @DisplayName("jump File")
    @MethodSource("createJumpFile")
    void jump(int index, Column actual, Column expected) {
        assertThat(actual.jump(index)).isEqualTo(expected);
    }

    static Stream<Arguments> createJumpFile() {
        return Stream.of(
                Arguments.of(3, A, D),
                Arguments.of(-2, H, F)
        );
    }

    @ParameterizedTest
    @DisplayName("다음 File")
    @MethodSource("createNextFile")
    void next(Column actual, Column expected) {
        assertThat(actual.next()).isEqualTo(expected);
    }

    static Stream<Arguments> createNextFile() {
        return Stream.of(
                Arguments.of(B, C),
                Arguments.of(G, H)
        );
    }

    @ParameterizedTest
    @DisplayName("이전 File")
    @MethodSource("createPreviousFile")
    void previous(Column actual, Column expected) {
        assertThat(actual.previous()).isEqualTo(expected);
    }

    static Stream<Arguments> createPreviousFile() {
        return Stream.of(
                Arguments.of(B, A),
                Arguments.of(H, G)
        );
    }
}