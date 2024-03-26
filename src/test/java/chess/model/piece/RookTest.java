package chess.model.piece;

import static chess.model.Fixtures.D1;
import static chess.model.Fixtures.D4;
import static chess.model.Fixtures.D5;
import static chess.model.Fixtures.E1;
import static chess.model.Fixtures.E4;
import static chess.model.Fixtures.E5;
import static chess.model.Fixtures.F6;
import static chess.model.Fixtures.G2;
import static chess.model.Fixtures.H4;
import static chess.model.material.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    /*
    ........
    ........
    ........
    ....*...
    ...*r..*
    ........
    ........
    ....*...
     */
    @DisplayName("Rook이 상하좌우 이동이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideValidSourceAndTarget")
    void rookCanMove(Position source, Position target) {
        Piece piece = Rook.of(WHITE);
        assertThatCode(() -> piece.findRoute(source, target))
            .doesNotThrowAnyException();
    }

    public static Stream<Arguments> provideValidSourceAndTarget() {
        return Stream.of(
            Arguments.of(E4, D4),
            Arguments.of(E4, E5),
            Arguments.of(E4, E1),
            Arguments.of(E4, H4)
        );
    }

    /*
    ........
    ........
    .....*..
    ...*....
    ....r...
    ........
    ......*.
    ...*....
    */
    @DisplayName("Rook이 상하좌우 이동이 아니면 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("provideInvalidSourceAndTarget")
    void rookCanNotMove(Position source, Position target) {
        Piece piece = Rook.of(WHITE);
        assertThatThrownBy(() -> piece.findRoute(source, target))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Rook은 상하좌우 이동만 가능합니다.");
    }

    public static Stream<Arguments> provideInvalidSourceAndTarget() {
        return Stream.of(
            Arguments.of(E4, D5),
            Arguments.of(E4, D1),
            Arguments.of(E4, F6),
            Arguments.of(E4, G2)
        );
    }
}
