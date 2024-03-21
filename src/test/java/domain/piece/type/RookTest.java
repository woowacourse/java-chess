package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    static Stream<Arguments> canRookMoveCrossArguments() {
        return Stream.of(
                Arguments.arguments(new Position("d4"), new Position("d3")),
                Arguments.arguments(new Position("d4"), new Position("h4")),
                Arguments.arguments(new Position("d4"), new Position("d7")),
                Arguments.arguments(new Position("d4"), new Position("a4"))
        );
    }

    static Stream<Arguments> cannotRookMoveDiagonalArguments() {
        return Stream.of(
                Arguments.arguments(new Position("d4"), new Position("e5")),
                Arguments.arguments(new Position("d4"), new Position("c5")),
                Arguments.arguments(new Position("d4"), new Position("e3")),
                Arguments.arguments(new Position("d4"), new Position("c3"))
        );
    }

    @DisplayName("룩은 상하좌우로 원하는 만큼 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canRookMoveCrossArguments")
    void canRookMoveDirection(Position source, Position target) {
        // given
        Piece rook = new Rook(PieceColor.BLACK);

        // when
        boolean result = rook.isMovable(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("룩은 대각선으로 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("cannotRookMoveDiagonalArguments")
    void cannotRookMoveDiagonal(Position source, Position target) {
        // given
        Piece rook = new Rook(PieceColor.BLACK);

        // when
        boolean result = rook.isMovable(source, target);

        // then
        assertThat(result).isFalse();
    }

}