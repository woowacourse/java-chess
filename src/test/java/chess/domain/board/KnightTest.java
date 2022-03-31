package chess.domain.board;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @DisplayName("나이트가 성공적으로 이동할 수 있다.")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("knightMoveTestSet")
    void moveNNETrue(Position src, Position dest) {
        Piece piece = new Knight(Color.BLACK);

        assertThat(piece.canMove(src, dest)).isTrue();
    }

    static Stream<Arguments> knightMoveTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d5"), Position.of("e7")),
                Arguments.of(Position.of("d5"), Position.of("e3")),
                Arguments.of(Position.of("d5"), Position.of("c7")),
                Arguments.of(Position.of("d5"), Position.of("c3")),
                Arguments.of(Position.of("d5"), Position.of("b6")),
                Arguments.of(Position.of("d5"), Position.of("b4")),
                Arguments.of(Position.of("d5"), Position.of("f6")),
                Arguments.of(Position.of("d5"), Position.of("f4")),
                Arguments.of(Position.of("e8"), Position.of("f6"))
        );
    }

    @DisplayName("나이트는 정면으로 한칸 움직일 수 없다")
    @Test
    void moveNNWTrue() {
        Piece piece = new Knight(Color.BLACK);
        Position src = Position.of("d5");
        Position dest = Position.of("d6");

        assertThat(piece.canMove(src, dest)).isFalse();
    }
}
