package chess.domain.piece.type;

import chess.domain.board.Movement;
import chess.domain.board.PieceRelation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    static Stream<Arguments> canRookMoveCrossArguments() {
        return Stream.of(
                Arguments.arguments(Position.of("d4"), Position.of("d3")),
                Arguments.arguments(Position.of("d4"), Position.of("h4")),
                Arguments.arguments(Position.of("d4"), Position.of("d7")),
                Arguments.arguments(Position.of("d4"), Position.of("a4"))
        );
    }

    static Stream<Arguments> cannotRookMoveDiagonalArguments() {
        return Stream.of(
                Arguments.arguments(Position.of("d4"), Position.of("e5")),
                Arguments.arguments(Position.of("d4"), Position.of("c5")),
                Arguments.arguments(Position.of("d4"), Position.of("e3")),
                Arguments.arguments(Position.of("d4"), Position.of("c3"))
        );
    }

    @DisplayName("룩은 상하좌우로 원하는 만큼 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canRookMoveCrossArguments")
    void canRookMoveDirection(Position source, Position target) {
        // given
        Piece rook = new Rook(PieceColor.BLACK);
        Movement movement = new Movement(source, target);
        PieceRelation targetStatus = PieceRelation.EMPTY;

        // when
        boolean result = rook.isMovable(movement, targetStatus);
        // then
        assertThat(result).isTrue();
    }

    @DisplayName("룩은 대각선으로 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("cannotRookMoveDiagonalArguments")
    void cannotRookMoveDiagonal(Position source, Position target) {
        // given
        Piece rook = new Rook(PieceColor.BLACK);
        Movement movement = new Movement(source, target);
        PieceRelation targetStatus = PieceRelation.EMPTY;

        // when
        boolean result = rook.isMovable(movement, targetStatus);

        // then
        assertThat(result).isFalse();
    }

}
