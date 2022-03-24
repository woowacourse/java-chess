package chess.piece;

import static chess.position.File.*;
import static chess.position.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @ParameterizedTest
    @MethodSource("provideInvalidMoveBishop")
    @DisplayName("비숍은 대각선외에는 움직일 수 없다.")
    void throwExceptionInvalidMoveBishop(Position from, Position to) {
        Bishop bishop = new Bishop(Color.BLACK, from);

        assertAll(() -> {
            assertThatThrownBy(() -> bishop.move(to)).isInstanceOf(IllegalArgumentException.class);
            assertThat(bishop.getPosition()).isEqualTo(from);
        });
    }

    private static Stream<Arguments> provideInvalidMoveBishop() {
        return Stream.of(
            Arguments.of(new Position(C, EIGHT), new Position(D, THREE)),
            Arguments.of(new Position(C, EIGHT), new Position(G, FIVE)),
            Arguments.of(new Position(C, ONE), new Position(B, FIVE)),
            Arguments.of(new Position(C, ONE), new Position(F, SIX))
        );
    }

    @ParameterizedTest
    @MethodSource("provideCrossMoveBishop")
    @DisplayName("비숍은 대각선으로 이동할 수 있다.")
    void moveCrossBishop(Position from, Position to) {
        Bishop bishop = new Bishop(Color.BLACK, from);

        bishop.move(to);

        assertThat(bishop.getPosition()).isEqualTo(to);
    }

    private static Stream<Arguments> provideCrossMoveBishop() {
        return Stream.of(
            Arguments.of(new Position(C, EIGHT), new Position(F, FIVE)),
            Arguments.of(new Position(C, EIGHT), new Position(A, SIX)),
            Arguments.of(new Position(C, ONE), new Position(B, TWO)),
            Arguments.of(new Position(C, ONE), new Position(E, THREE))
        );
    }
}