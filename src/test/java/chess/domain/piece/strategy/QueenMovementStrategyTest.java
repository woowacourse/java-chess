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

class QueenMovementStrategyTest {

    static Stream<Arguments> canQueenMoveAllDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.D4, Position.D1),
                Arguments.arguments(Position.D4, Position.G4),
                Arguments.arguments(Position.D4, Position.D7),
                Arguments.arguments(Position.D4, Position.A4),
                Arguments.arguments(Position.D4, Position.G7),
                Arguments.arguments(Position.D4, Position.A7),
                Arguments.arguments(Position.D4, Position.G1),
                Arguments.arguments(Position.D4, Position.A1)
        );
    }

    @DisplayName("퀸은 모든 방향으로 원하는 만큼 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canQueenMoveAllDirectionArguments")
    void canQueenMoveAllDirection(Position source, Position target) {
        // given
        Piece queen = new Piece(PieceType.WHITE_QUEEN);

        // when
        boolean result = queen.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }
}
