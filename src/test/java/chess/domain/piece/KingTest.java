package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @DisplayName("킹은 성공적으로 이동할 수 있다.")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("kingMoveTestSet")
    void kingMoveTest(Position src, Position dest) {
        Piece piece = new King(Color.BLACK);

        assertThat(piece.canMove(src, dest)).isTrue();
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
                Arguments.of(Position.of("d3"), Position.of("c2"))
        );
    }

    @DisplayName("킹은 북으로 두칸 이동할 수 없다.")
    @Test
    void kingTest() {
        Piece piece = new King(Color.BLACK);
        Position src = Position.of("d3");
        Position dest = Position.of("d5");

        assertThat(piece.canMove(src, dest)).isFalse();
    }
}
