package chess.domain.piece.type;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.type.Bishop;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    static Stream<Arguments> cannotBishopMoveCrossArguments() {
        return Stream.of(
                Arguments.arguments(new Position("d4"), new Position("d3")),
                Arguments.arguments(new Position("d4"), new Position("h4")),
                Arguments.arguments(new Position("d4"), new Position("d7")),
                Arguments.arguments(new Position("d4"), new Position("a4"))
        );
    }

    static Stream<Arguments> canBishopMoveDiagonalArguments() {
        return Stream.of(
                Arguments.arguments(new Position("d4"), new Position("e5")),
                Arguments.arguments(new Position("d4"), new Position("c5")),
                Arguments.arguments(new Position("d4"), new Position("e3")),
                Arguments.arguments(new Position("d4"), new Position("c3"))
        );
    }

    @DisplayName("비숍은 상하좌우로 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("cannotBishopMoveCrossArguments")
    void cannotBishopMoveCross(Position source, Position target) {
        // given
        Piece bishop = new Bishop(PieceColor.BLACK);

        // when
        boolean result = bishop.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("비숍은 대각선으로 원하는 만큼 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canBishopMoveDiagonalArguments")
    void canBishopMoveDiagonal(Position source, Position target) {
        // given
        Piece bishop = new Bishop(PieceColor.BLACK);

        // when
        boolean result = bishop.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }
}
