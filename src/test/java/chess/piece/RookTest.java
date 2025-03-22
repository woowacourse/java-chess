package chess.piece;

import chess.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

class RookTest {

    @ParameterizedTest
    @MethodSource("provideValidPositions")
    void 룩은_십자방향으로_움직일_수_있다(Position validPosition) {
        // given
        final Rook rook = new Rook(D5);

        // expected
        assertThatCode(() -> rook.move(validPosition))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPositions")
    void 룩은_그_외의_위치로는_움직일_수_없다(Position invalidPosition) {
        // given
        final Rook rook = new Rook(D5);

        // expected
        assertThatThrownBy(() -> rook.move(invalidPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 위치입니다.");
    }

    public static Stream<Arguments> provideValidPositions() {
        return Stream.of(
                Arguments.of(D1),
                Arguments.of(D2),
                Arguments.of(D3),
                Arguments.of(D4),
                Arguments.of(D6),
                Arguments.of(D7),
                Arguments.of(D8),
                Arguments.of(A5),
                Arguments.of(B5),
                Arguments.of(C5),
                Arguments.of(E5),
                Arguments.of(F5),
                Arguments.of(G5),
                Arguments.of(H5)
        );
    }

    public static Stream<Arguments> provideInvalidPositions() {
        return Stream.of(
                Arguments.of(C4),
                Arguments.of(B3),
                Arguments.of(A2),
                Arguments.of(C6),
                Arguments.of(B7),
                Arguments.of(A8),
                Arguments.of(E4),
                Arguments.of(F3),
                Arguments.of(G2),
                Arguments.of(H1),
                Arguments.of(E6),
                Arguments.of(F7),
                Arguments.of(G8)
        );
    }

}