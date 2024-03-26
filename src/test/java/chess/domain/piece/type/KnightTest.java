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

class KnightTest {

    static Stream<Arguments> canKnightMoveL_ShapeDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.of("d4"), Position.of("c2")),
                Arguments.arguments(Position.of("d4"), Position.of("e2")),
                Arguments.arguments(Position.of("d4"), Position.of("c6")),
                Arguments.arguments(Position.of("d4"), Position.of("e6")),
                Arguments.arguments(Position.of("d4"), Position.of("f3")),
                Arguments.arguments(Position.of("d4"), Position.of("f5")),
                Arguments.arguments(Position.of("d4"), Position.of("b5")),
                Arguments.arguments(Position.of("d4"), Position.of("b3"))
        );
    }

    static Stream<Arguments> cannotKnightMoveExceptL_ShapeDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.of("d4"), Position.of("g5")),
                Arguments.arguments(Position.of("d4"), Position.of("g7")),
                Arguments.arguments(Position.of("d4"), Position.of("c5")),
                Arguments.arguments(Position.of("d4"), Position.of("c7")),
                Arguments.arguments(Position.of("d4"), Position.of("a3")),
                Arguments.arguments(Position.of("d4"), Position.of("a1")),
                Arguments.arguments(Position.of("d4"), Position.of("e3")),
                Arguments.arguments(Position.of("d4"), Position.of("e1"))
        );
    }

    @DisplayName("나이트는 모든 방향으로 L모양으로만 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canKnightMoveL_ShapeDirectionArguments")
    void canKnightMoveL_ShapeDirection(Position source, Position target) {
        // given
        Piece knight = new Knight(PieceColor.BLACK);
        Movement movement = new Movement(source, target);
        PieceRelation targetStatus = PieceRelation.EMPTY;

        // when
        boolean result = knight.isMovable(movement, targetStatus);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("나이트는 모든 방향으로 L모양 외에는 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("cannotKnightMoveExceptL_ShapeDirectionArguments")
    void cannotKnightMoveExceptL_ShapeDirection(Position source, Position target) {
        // given
        Piece knight = new Knight(PieceColor.BLACK);
        Movement movement = new Movement(source, target);
        PieceRelation targetStatus = PieceRelation.EMPTY;

        // when
        boolean result = knight.isMovable(movement, targetStatus);

        // then
        assertThat(result).isFalse();
    }
}
