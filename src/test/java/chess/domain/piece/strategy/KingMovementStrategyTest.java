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

class KingMovementStrategyTest {

    static Stream<Arguments> canKingMoveAllDirectionOneStepArguments() {
        return Stream.of(
                Arguments.arguments(Position.D4, Position.D3),
                Arguments.arguments(Position.D4, Position.E4),
                Arguments.arguments(Position.D4, Position.D5),
                Arguments.arguments(Position.D4, Position.C4),
                Arguments.arguments(Position.D4, Position.E5),
                Arguments.arguments(Position.D4, Position.C5),
                Arguments.arguments(Position.D4, Position.E3),
                Arguments.arguments(Position.D4, Position.C3)
        );
    }

    static Stream<Arguments> cannotKingMoveAllDirectionMoreThanTwoStepArguments() {
        return Stream.of(
                Arguments.arguments(Position.D4, Position.D2),
                Arguments.arguments(Position.D4, Position.F4),
                Arguments.arguments(Position.D4, Position.D6),
                Arguments.arguments(Position.D4, Position.B4),
                Arguments.arguments(Position.D4, Position.F6),
                Arguments.arguments(Position.D4, Position.B6),
                Arguments.arguments(Position.D4, Position.F2),
                Arguments.arguments(Position.D4, Position.B2)
        );
    }

    @DisplayName("킹은 모든 방향으로 한 칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canKingMoveAllDirectionOneStepArguments")
    void canKingMoveAllDirectionOneStep(Position source, Position target) {
        // given
        Piece king = new Piece(PieceType.BLACK_KING);

        // when
        boolean result = king.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("킹은 모든 방향으로 두 칸 이상 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("cannotKingMoveAllDirectionMoreThanTwoStepArguments")
    void cannotKingMoveAllDirectionMoreThanTwoStep(Position source, Position target) {
        // given
        Piece king = new Piece(PieceType.BLACK_KING);

        // when
        boolean result = king.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }
}
