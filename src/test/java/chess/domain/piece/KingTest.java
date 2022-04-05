package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
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
                Arguments.of(Position.valueOf("d3"), Position.valueOf("d4")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("d2")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("e3")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("c3")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("e4")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("e2")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("c4")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("c2")),
                Arguments.of(Position.valueOf("e8"), Position.valueOf("e7"))
        );
    }

    @DisplayName("킹은 북으로 두칸 이동할 수 없다.")
    @Test
    void kingTest() {
        Piece piece = new King(Color.BLACK);
        Position source = Position.valueOf("d3");
        Position destination = Position.valueOf("d5");

        assertThat(piece.canMove(source, destination)).isFalse();
    }
}
