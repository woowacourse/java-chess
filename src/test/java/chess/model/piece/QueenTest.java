package chess.model.piece;

import static chess.model.Fixtures.A1;
import static chess.model.Fixtures.A5;
import static chess.model.Fixtures.A7;
import static chess.model.Fixtures.C2;
import static chess.model.Fixtures.C4;
import static chess.model.Fixtures.D3;
import static chess.model.Fixtures.D4;
import static chess.model.Fixtures.D8;
import static chess.model.Fixtures.E5;
import static chess.model.Fixtures.E6;
import static chess.model.Fixtures.F2;
import static chess.model.Fixtures.F3;
import static chess.model.Fixtures.G4;
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

class QueenTest {

    /*
    ...*....
    *.......
    ........
    ....*...
    ..*q..*.
    ...*....
    .....*..
    *.......
     */
    @DisplayName("Queen이 상하좌우 대각선 이동이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideValidSourceAndTarget")
    void queenCanMove(Position source, Position target) {
        Piece piece = Queen.of(WHITE);
        assertThatCode(() -> piece.findRoute(source, target))
            .doesNotThrowAnyException();
    }

    public static Stream<Arguments> provideValidSourceAndTarget() {
        return Stream.of(
            Arguments.of(D4, A7),
            Arguments.of(D4, A1),
            Arguments.of(D4, C4),
            Arguments.of(D4, D3),
            Arguments.of(D4, D8),
            Arguments.of(D4, E5),
            Arguments.of(D4, F2),
            Arguments.of(D4, G4)
        );
    }

    /*
    ........
    ........
    ....*...
    *.....*.
    ...q....
    .....*..
    ..*.....
    ........
     */
    @DisplayName("Queen이 상하좌우 대각선 이동이 아니면 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("provideInvalidSourceAndTarget")
    void queenCanNotMove(Position source, Position target) {
        Piece piece = Queen.of(WHITE);
        assertThatThrownBy(() -> piece.findRoute(source, target))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Queen은 상하좌우 대각선 이동만 가능합니다.");
    }

    public static Stream<Arguments> provideInvalidSourceAndTarget() {
        return Stream.of(
            Arguments.of(D4, A5),
            Arguments.of(D4, C2),
            Arguments.of(D4, E6),
            Arguments.of(D4, F3),
            Arguments.of(D4, G5)
        );
    }
}
