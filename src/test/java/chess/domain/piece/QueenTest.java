package chess.domain.piece;

import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {

    @ParameterizedTest
    @MethodSource("provideInvalidMoveQueen")
    @DisplayName("퀸은 동일선상외에는 이동 시 예외 발생")
    void moveInvalidMoveQueen(Position from, Position to) {
        Queen queen = new Queen(Color.BLACK, from);

        assertThatThrownBy(() -> queen.transfer(to, List.of(queen)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> provideInvalidMoveQueen() {
        return Stream.of(
            Arguments.of(new Position(D, EIGHT), new Position(F, FOUR)),
            Arguments.of(new Position(D, EIGHT), new Position(A, SIX)),
            Arguments.of(new Position(D, ONE), new Position(E, FIVE)),
            Arguments.of(new Position(D, ONE), new Position(B, TWO))
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidMoveQueen")
    @DisplayName("퀸은 동일선상으로 이동")
    void moveCrossOrSameRowOrColMoveQueen(Position from, Position to) {
        Queen queen = new Queen(Color.BLACK, from);

        assertThat(queen.transfer(to, List.of(queen)))
            .isEqualTo(new Queen(Color.BLACK, to));
    }

    private static Stream<Arguments> provideValidMoveQueen() {
        return Stream.of(
            Arguments.of(new Position(D, EIGHT), new Position(D, FOUR)),
            Arguments.of(new Position(D, EIGHT), new Position(H, EIGHT)),
            Arguments.of(new Position(D, EIGHT), new Position(A, EIGHT)),
            Arguments.of(new Position(D, EIGHT), new Position(F, SIX)),
            Arguments.of(new Position(D, EIGHT), new Position(A, FIVE)),
            Arguments.of(new Position(D, ONE), new Position(D, FOUR)),
            Arguments.of(new Position(D, ONE), new Position(F, THREE)),
            Arguments.of(new Position(D, ONE), new Position(A, FOUR))
        );
    }
}
