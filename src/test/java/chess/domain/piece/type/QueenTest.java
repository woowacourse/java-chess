package chess.domain.piece.type;

import chess.domain.position.Movement;
import chess.domain.piece.PieceRelation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    static Stream<Arguments> cannotQueenMoveAllDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.of("d4"), Position.of("d1")),
                Arguments.arguments(Position.of("d4"), Position.of("g4")),
                Arguments.arguments(Position.of("d4"), Position.of("d7")),
                Arguments.arguments(Position.of("d4"), Position.of("a4")),
                Arguments.arguments(Position.of("d4"), Position.of("g7")),
                Arguments.arguments(Position.of("d4"), Position.of("a7")),
                Arguments.arguments(Position.of("d4"), Position.of("g1")),
                Arguments.arguments(Position.of("d4"), Position.of("a1"))
        );
    }

    @DisplayName("퀸은 모든 방향으로 원하는 만큼 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("cannotQueenMoveAllDirectionArguments")
    void canQueenMoveAllDirection(Position source, Position target) {
        // given
        Piece queen = new Queen(PieceColor.BLACK);
        Movement movement = new Movement(source, target);
        PieceRelation targetStatus = PieceRelation.EMPTY;

        // when
        boolean result = queen.isMovable(movement, targetStatus);

        // then
        assertThat(result).isTrue();
    }
}
