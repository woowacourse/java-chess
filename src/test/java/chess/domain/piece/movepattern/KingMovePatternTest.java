package chess.domain.piece.movepattern;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KingMovePatternTest {

    @DisplayName("왕은 상하좌우 대각선으로 한칸 이동할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("kingMovePatternTestSet")
    void test(Position src, Position dest) {
        AbstractOnceMovePattern pattern = new KingMovePattern();

        assertThat(pattern.canMove(src, dest)).isTrue();
    }

    static Stream<Arguments> kingMovePatternTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d5"), Position.of("d4"), Color.BLACK),
                Arguments.of(Position.of("d5"), Position.of("d6"), Color.WHITE),
                Arguments.of(Position.of("d5"), Position.of("c5"), Color.BLACK),
                Arguments.of(Position.of("d5"), Position.of("e5"), Color.WHITE),
                Arguments.of(Position.of("d5"), Position.of("c4"), Color.BLACK),
                Arguments.of(Position.of("d5"), Position.of("c6"), Color.WHITE),
                Arguments.of(Position.of("d5"), Position.of("e4"), Color.BLACK),
                Arguments.of(Position.of("d5"), Position.of("e6"), Color.WHITE)
        );
    }
}
