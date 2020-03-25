package chess.domain.board;

import static chess.domain.board.File.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FileTest {

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

    static Stream<Arguments> createNextFile() {
        return Stream.of(
                Arguments.of(B, C),
                Arguments.of(H, null)
        );
    }

    static Stream<Arguments> createPreviousFile() {
        return Stream.of(
                Arguments.of(B, A),
                Arguments.of(A, null)
        );
    }

    @ParameterizedTest
    @DisplayName("세로축에 대해 대칭")
    @MethodSource("createOppositeCase")
    void opposite(File actual, File expected) {
        assertThat(actual.opposite()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("다음 File")
    @MethodSource("createNextFile")
    void next(File actual, File expected) {
        assertThat(actual.next().orElse(null)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("이전 File")
    @MethodSource("createPreviousFile")
    void previous(File actual, File expected) {
        assertThat(actual.previous().orElse(null)).isEqualTo(expected);
    }
}