package chess.domain.board;

import static chess.domain.board.File.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FileTest {

    @ParameterizedTest
    @DisplayName("세로축에 대해 대칭")
    @MethodSource("createOppositeCase")
    void opposite(File actual, File expected) {
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
}