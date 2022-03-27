package chess.domain.piece.movepattern;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RookMovePatternTest {

    @DisplayName("룩은 상하좌우로 이동할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("rookMovePatternTestSet")
    void test(Position src, Position dest) {
        AbstractStraightMovePattern pattern = new RookMovePattern();

        assertThat(pattern.canMove(src, dest)).isTrue();
    }

    static Stream<Arguments> rookMovePatternTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d5"), Position.of("d1"), Color.WHITE),
                Arguments.of(Position.of("d5"), Position.of("d8"), Color.BLACK),
                Arguments.of(Position.of("d5"), Position.of("a5"), Color.WHITE),
                Arguments.of(Position.of("d5"), Position.of("h5"), Color.BLACK)
        );
    }
}
