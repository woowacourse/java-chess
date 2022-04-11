package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @DisplayName("나이트가 성공적으로 이동할 수 있다.")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("knightMoveTestSet")
    void moveNNETrue(Position source, Position destination) {
        Piece piece = new Knight(Color.BLACK);

        assertThat(piece.canMove(source, destination)).isTrue();
    }

    static Stream<Arguments> knightMoveTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e7")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e3")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c7")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c3")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("b6")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("b4")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("f6")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("f4")),
                Arguments.of(Position.valueOf("e8"), Position.valueOf("f6"))
        );
    }

    @DisplayName("나이트는 정면으로 한칸 움직일 수 없다")
    @Test
    void moveNNWTrue() {
        Piece piece = new Knight(Color.BLACK);
        Position source = Position.valueOf("d5");
        Position destination = Position.valueOf("d6");

        assertThat(piece.canMove(source, destination)).isFalse();
    }
}
