package chess.console.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.console.domain.board.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @DisplayName("킹은 성공적으로 이동할 수 있다.")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("kingMoveTestSet")
    void kingMoveTest(Position source, Position destination) {
        Piece piece = new King(Color.BLACK);

        assertThat(piece.canMove(source, destination)).isTrue();
    }

    static Stream<Arguments> kingMoveTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d3"), Position.of("d4")),
                Arguments.of(Position.of("d3"), Position.of("d2")),
                Arguments.of(Position.of("d3"), Position.of("e3")),
                Arguments.of(Position.of("d3"), Position.of("c3")),
                Arguments.of(Position.of("d3"), Position.of("e4")),
                Arguments.of(Position.of("d3"), Position.of("e2")),
                Arguments.of(Position.of("d3"), Position.of("c4")),
                Arguments.of(Position.of("d3"), Position.of("c2")),
                Arguments.of(Position.of("e8"), Position.of("e7"))
        );
    }

    @DisplayName("킹은 북으로 두칸 이동할 수 없다.")
    @Test
    void kingTest() {
        Piece piece = new King(Color.BLACK);
        Position source = Position.of("d3");
        Position destination = Position.of("d5");

        assertThat(piece.canMove(source, destination)).isFalse();
    }
}
