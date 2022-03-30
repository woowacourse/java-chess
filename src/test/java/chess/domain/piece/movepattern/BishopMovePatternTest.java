package chess.domain.piece.movepattern;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BishopMovePatternTest {

    @DisplayName("비숍은 대각선으로 이동할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("bishopMovePatternTestSet")
    void test(Position source, Position destination) {
        AbstractStraightMovePattern pattern = new BishopMovePattern();

        assertThat(pattern.canMove(source, destination)).isTrue();
    }

    static Stream<Arguments> bishopMovePatternTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d5"), Position.of("a8"), Color.WHITE),
                Arguments.of(Position.of("d5"), Position.of("a2"), Color.BLACK),
                Arguments.of(Position.of("d5"), Position.of("h1"), Color.WHITE),
                Arguments.of(Position.of("d5"), Position.of("g8"), Color.BLACK)
        );
    }
}
