package chess.domain.piece.type;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.type.Knight;
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
                Arguments.arguments(new Position("d4"), new Position("c2")),
                Arguments.arguments(new Position("d4"), new Position("e2")),
                Arguments.arguments(new Position("d4"), new Position("c6")),
                Arguments.arguments(new Position("d4"), new Position("e6")),
                Arguments.arguments(new Position("d4"), new Position("f3")),
                Arguments.arguments(new Position("d4"), new Position("f5")),
                Arguments.arguments(new Position("d4"), new Position("b5")),
                Arguments.arguments(new Position("d4"), new Position("b3"))
        );
    }

    static Stream<Arguments> cannotKnightMoveExceptL_ShapeDirectionArguments() {
        return Stream.of(
                Arguments.arguments(new Position("d4"), new Position("g5")),
                Arguments.arguments(new Position("d4"), new Position("g7")),
                Arguments.arguments(new Position("d4"), new Position("c5")),
                Arguments.arguments(new Position("d4"), new Position("c7")),
                Arguments.arguments(new Position("d4"), new Position("a3")),
                Arguments.arguments(new Position("d4"), new Position("a1")),
                Arguments.arguments(new Position("d4"), new Position("e3")),
                Arguments.arguments(new Position("d4"), new Position("e1"))
        );
    }

    @DisplayName("나이트는 모든 방향으로 L모양으로만 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canKnightMoveL_ShapeDirectionArguments")
    void canKnightMoveL_ShapeDirection(Position source, Position target) {
        // given
        Piece knight = new Knight(PieceColor.BLACK);

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
        Piece knight = new Knight(PieceColor.BLACK);

        // when
        boolean result = knight.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }

}