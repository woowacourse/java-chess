package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

class KnightTest {

    @ParameterizedTest
    @MethodSource("provideValidPositions")
    void 나이트는_십자방향과_대각선으로_움직일_수_있다(Position validPosition) {
        // given
        final Knight knight = new Knight(D5);

        // expected
        assertThatCode(() -> knight.move(validPosition))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPositions")
    void 나이트는_그_외의_위치로는_움직일_수_없다(Position invalidPosition) {
        // given
        final Knight knight = new Knight(D5);

        // expected
        assertThatThrownBy(() -> knight.move(invalidPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 위치입니다.");
    }

    public static Stream<Arguments> provideValidPositions() {
        return Stream.of(
                Arguments.of(F4),
                Arguments.of(F6),
                Arguments.of(E3),
                Arguments.of(E7),
                Arguments.of(B4),
                Arguments.of(B6),
                Arguments.of(C3),
                Arguments.of(C7)
        );
    }

    public static Stream<Arguments> provideInvalidPositions() {
        return Stream.of(
                Arguments.of(new Position(Row.FIVE, Column.C)),
                Arguments.of(new Position(Row.FIVE, Column.E)),
                Arguments.of(new Position(Row.FOUR, Column.D)),
                Arguments.of(new Position(Row.SIX, Column.D)),
                Arguments.of(new Position(Row.FOUR, Column.C)),
                Arguments.of(new Position(Row.FOUR, Column.E)),
                Arguments.of(new Position(Row.SIX, Column.C)),
                Arguments.of(new Position(Row.SIX, Column.E))
        );
    }

}