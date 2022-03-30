package chess.domain.piece.movepattern;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KnightMovePatternTest {

    @DisplayName("나이트는 상하좌우 두칸 앞으로 이동 후 좌우의 위치로 이동한다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("knightMovePatternTestSet")
    void test(Position source, Position destination) {
        AbstractOnceMovePattern pattern = new KnightMovePattern();

        assertThat(pattern.canMove(source, destination)).isTrue();
    }

    static Stream<Arguments> knightMovePatternTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d5"), Position.of("b4"), Color.WHITE),
                Arguments.of(Position.of("d5"), Position.of("b6"), Color.BLACK),
                Arguments.of(Position.of("d5"), Position.of("c3"), Color.WHITE),
                Arguments.of(Position.of("d5"), Position.of("c7"), Color.BLACK),
                Arguments.of(Position.of("d5"), Position.of("e3"), Color.WHITE),
                Arguments.of(Position.of("d5"), Position.of("e7"), Color.BLACK),
                Arguments.of(Position.of("d5"), Position.of("f4"), Color.WHITE),
                Arguments.of(Position.of("d5"), Position.of("f6"), Color.BLACK)
        );
    }
}
