package chess.model.piece;

import static chess.model.Fixtures.C3;
import static chess.model.Fixtures.D2;
import static chess.model.Fixtures.D3;
import static chess.model.Fixtures.D4;
import static chess.model.Fixtures.D6;
import static chess.model.Fixtures.E1;
import static chess.model.Fixtures.E2;
import static chess.model.Fixtures.E3;
import static chess.model.Fixtures.E4;
import static chess.model.Fixtures.F2;
import static chess.model.Fixtures.F3;
import static chess.model.Fixtures.F4;
import static chess.model.Fixtures.G5;
import static chess.model.material.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    /*
    ........
    ........
    ........
    ........
    ...***..
    ...*k*..
    ...***..
    ........
     */
    @DisplayName("King이 상하좌우 대각선 1칸 이동이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideValidSourceAndTarget")
    void kingCanMove(Position source, Position target) {
        Piece piece = King.of(WHITE);
        assertThatCode(() -> piece.findRoute(source, target))
            .doesNotThrowAnyException();
    }

    public static Stream<Arguments> provideValidSourceAndTarget() {
        return Stream.of(
            Arguments.of(E3, D4),
            Arguments.of(E3, D3),
            Arguments.of(E3, D2),
            Arguments.of(E3, E4),
            Arguments.of(E3, E2),
            Arguments.of(E3, F4),
            Arguments.of(E3, F3),
            Arguments.of(E3, F2)
        );
    }

    /*
    ........
    ........
    ...*....
    ......*.
    ........
    ..*.k...
    ........
    ....*...
     */
    @DisplayName("King이 상하좌우 대각선 1칸 이동이 아니면 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("provideInvalidSourceAndTarget")
    void kingCanNotMove(Position source, Position target) {
        Piece piece = King.of(WHITE);
        assertThatThrownBy(() -> piece.findRoute(source, target))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("King은 상하좌우 대각선 1칸 이동만 가능합니다.");
    }

    public static Stream<Arguments> provideInvalidSourceAndTarget() {
        return Stream.of(
            Arguments.of(E3, C3),
            Arguments.of(E3, D6),
            Arguments.of(E3, E1),
            Arguments.of(E3, G5)
        );
    }
}
