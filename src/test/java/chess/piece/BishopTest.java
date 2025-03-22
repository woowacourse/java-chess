package chess.piece;

import chess.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

class BishopTest {

    @ParameterizedTest
    @MethodSource("provideValidPositions")
    void 비숍은_대각선으로_원하는만큼_움직일_수_있다(Position validPosition) {
        // given
        final Bishop bishop = new Bishop(D5);

        // expected
        assertThatCode(() -> bishop.move(validPosition))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPositions")
    void 비숍은_그_외의_위치로는_움직일_수_없다(Position invalidPosition) {
        // given
        final Bishop bishop = new Bishop(D5);

        // expected
        assertThatThrownBy(() -> bishop.move(invalidPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 위치입니다.");
    }

    public static Stream<Arguments> provideValidPositions() {
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

    public static Stream<Arguments> provideInvalidPositions() {
        return Stream.of(
                Arguments.of(D4),
                Arguments.of(D6),
                Arguments.of(D3),
                Arguments.of(D3)
        );
    }
}