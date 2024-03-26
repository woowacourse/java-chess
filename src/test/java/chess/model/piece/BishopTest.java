package chess.model.piece;

import static chess.model.Fixtures.A4;
import static chess.model.Fixtures.B2;
import static chess.model.Fixtures.C5;
import static chess.model.Fixtures.D1;
import static chess.model.Fixtures.D4;
import static chess.model.Fixtures.E6;
import static chess.model.Fixtures.F6;
import static chess.model.Fixtures.G1;
import static chess.model.Fixtures.G3;
import static chess.model.material.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    /*
    ........
    ........
    .....*..
    ..*.....
    ...b....
    ........
    .*......
    ......*.
     */
    @DisplayName("Bishop이 대각선 이동이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideValidSourceAndTarget")
    void bishopCanMove(Position source, Position target) {
        Piece piece = new Bishop(WHITE);
        assertThatCode(() -> piece.findRoute(source, target))
            .doesNotThrowAnyException();
    }

    public static Stream<Arguments> provideValidSourceAndTarget() {
        return Stream.of(
            Arguments.of(D4, B2),
            Arguments.of(D4, C5),
            Arguments.of(D4, F6),
            Arguments.of(D4, G1)
        );
    }

    /*
    ........
    ........
    ....*...
    ........
    *..b....
    ......*.
    ........
    ...*....
     */
    @DisplayName("Bishop이 대각선 이동이 아니면 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("provideInvalidSourceAndTarget")
    void bishopCanNotMove(Position source, Position target) {
        Piece piece = new Bishop(WHITE);
        assertThatThrownBy(() -> piece.findRoute(source, target))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Bishop은 대각선 이동만 가능합니다.");
    }

    public static Stream<Arguments> provideInvalidSourceAndTarget() {
        return Stream.of(
            Arguments.of(D4, A4),
            Arguments.of(D4, D1),
            Arguments.of(D4, E6),
            Arguments.of(D4, G3)
        );
    }
}
