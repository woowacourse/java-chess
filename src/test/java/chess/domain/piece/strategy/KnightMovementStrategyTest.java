package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightMovementStrategyTest {

    static Stream<Arguments> canKnightMoveL_ShapeDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.D4, Position.C2),
                Arguments.arguments(Position.D4, Position.E2),
                Arguments.arguments(Position.D4, Position.C6),
                Arguments.arguments(Position.D4, Position.E6),
                Arguments.arguments(Position.D4, Position.F3),
                Arguments.arguments(Position.D4, Position.F5),
                Arguments.arguments(Position.D4, Position.B5),
                Arguments.arguments(Position.D4, Position.B3)
        );
    }

    static Stream<Arguments> cannotKnightMoveExceptL_ShapeDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.D4, Position.G5),
                Arguments.arguments(Position.D4, Position.G7),
                Arguments.arguments(Position.D4, Position.C5),
                Arguments.arguments(Position.D4, Position.C7),
                Arguments.arguments(Position.D4, Position.A3),
                Arguments.arguments(Position.D4, Position.A1),
                Arguments.arguments(Position.D4, Position.E3),
                Arguments.arguments(Position.D4, Position.E1)
        );
    }

    @DisplayName("나이트는 모든 방향으로 L모양으로만 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canKnightMoveL_ShapeDirectionArguments")
    void canKnightMoveL_ShapeDirection(Position source, Position target) {
        // given
        Piece knight = new Piece(PieceType.BLACK_KNIGHT);

        // when
        boolean result = knight.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("나이트는 모든 방향으로 L모양 외에는 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("cannotKnightMoveExceptL_ShapeDirectionArguments")
    void cannotKnightMoveExceptL_ShapeDirection(Position source, Position target) {
        // given
        Piece knight = new Piece(PieceType.BLACK_KNIGHT);

        // when
        boolean result = knight.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }
}
