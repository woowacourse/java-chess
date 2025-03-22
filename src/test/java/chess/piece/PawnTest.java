package chess.piece;

import chess.position.Column;
import chess.position.Position;
import chess.position.Row;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.Fixtures.D5;
import static org.assertj.core.api.Assertions.*;

class PawnTest {

    @ParameterizedTest
    @MethodSource("provideValidPositions")
    void 폰은_위로_움직일_수_있다(Position validPosition) {
        // given
        final Pawn pawn = new Pawn(D5);

        // expected
        assertThatCode(() -> pawn.move(validPosition))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPositions")
    void 폰은_그_외의_위치로는_움직일_수_없다(Position invalidPosition) {
        // given
        final Pawn pawn = new Pawn(D5);

        // expected
        assertThatThrownBy(() -> pawn.move(invalidPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 위치입니다.");
    }

    public static Stream<Arguments> provideValidPositions() {
        return Stream.of(
                Arguments.of(new Position(Row.SIX, Column.D))
        );
    }

    public static Stream<Arguments> provideInvalidPositions() {
        return Stream.of(
                Arguments.of(new Position(Row.FIVE, Column.C)),
                Arguments.of(new Position(Row.FIVE, Column.E)),
                Arguments.of(new Position(Row.FOUR, Column.D)),
                Arguments.of(new Position(Row.FOUR, Column.C)),
                Arguments.of(new Position(Row.FOUR, Column.E)),
                Arguments.of(new Position(Row.SIX, Column.C)),
                Arguments.of(new Position(Row.SIX, Column.E))
        );
    }
}