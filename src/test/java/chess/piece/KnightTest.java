package chess.piece;

import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.G;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.FOUR;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.SIX;
import static chess.position.Rank.THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.position.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @Test
    @DisplayName("나이트가 이동할 수 없는 위치로 이동 시 예외 발생")
    void moveKnightToInvalidPosition() {
        Knight knight = new Knight(Color.BLACK, new Position(G, EIGHT));

        assertThatThrownBy(
                () -> knight.transfer(new Position(F, FIVE), new Pieces(List.of(knight))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideValidMoveKnight")
    @DisplayName("나이트는 직선으로 1칸 이동 후 대각선으로 1칸 움직인다.")
    void moveKnightToValidPosition(Position from, Position to) {
        Knight knight = new Knight(Color.BLACK, from);

        assertThat(knight.transfer(to, new Pieces(List.of(knight))))
                .isEqualTo(new Knight(Color.BLACK, to));
    }

    private static Stream<Arguments> provideValidMoveKnight() {
        return Stream.of(
                Arguments.of(new Position(E, FIVE), new Position(G, FOUR)),
                Arguments.of(new Position(E, FIVE), new Position(F, THREE)),
                Arguments.of(new Position(E, FIVE), new Position(D, THREE)),
                Arguments.of(new Position(E, FIVE), new Position(C, FOUR)),
                Arguments.of(new Position(E, FIVE), new Position(C, SIX)),
                Arguments.of(new Position(E, FIVE), new Position(D, SEVEN)),
                Arguments.of(new Position(E, FIVE), new Position(F, SEVEN)),
                Arguments.of(new Position(E, FIVE), new Position(G, SIX))
        );
    }
}