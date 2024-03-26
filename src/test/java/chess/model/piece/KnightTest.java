package chess.model.piece;

import static chess.model.Fixtures.B3;
import static chess.model.Fixtures.B5;
import static chess.model.Fixtures.C1;
import static chess.model.Fixtures.C2;
import static chess.model.Fixtures.C4;
import static chess.model.Fixtures.C6;
import static chess.model.Fixtures.D4;
import static chess.model.Fixtures.D6;
import static chess.model.Fixtures.E2;
import static chess.model.Fixtures.E6;
import static chess.model.Fixtures.F2;
import static chess.model.Fixtures.F3;
import static chess.model.Fixtures.F5;
import static chess.model.Fixtures.G6;
import static chess.model.material.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {


    /*
    ........
    ........
    ..*.*...
    .*...*..
    ...N....
    .*...*..
    ..*.*...
    ........
     */
    @DisplayName("Knight이 L자 이동이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideValidSourceAndTarget")
    void knightCanMove(Position source, Position target) {
        Piece piece = new Knight(BLACK);
        assertThatCode(() -> piece.findRoute(source, target))
            .doesNotThrowAnyException();
    }

    public static Stream<Arguments> provideValidSourceAndTarget() {
        return Stream.of(
            Arguments.of(D4, B5),
            Arguments.of(D4, B3),
            Arguments.of(D4, C6),
            Arguments.of(D4, C2),
            Arguments.of(D4, E6),
            Arguments.of(D4, E2),
            Arguments.of(D4, F5),
            Arguments.of(D4, F3)
        );
    }

    /*
    ........
    ........
    ...*..*.
    ........
    ..*N....
    ........
    .....*..
    ..*.....
     */
    @DisplayName("Knight이 L자 이동이 아니면 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("provideInvalidSourceAndTarget")
    void knightCanNotMove(Position source, Position target) {
        Piece piece = new Knight(BLACK);
        assertThatThrownBy(() -> piece.findRoute(source, target))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Knight는 L자 이동만 가능합니다.");
    }

    public static Stream<Arguments> provideInvalidSourceAndTarget() {
        return Stream.of(
            Arguments.of(D4, C1),
            Arguments.of(D4, C4),
            Arguments.of(D4, D6),
            Arguments.of(D4, F2),
            Arguments.of(D4, G6)
        );
    }
}
