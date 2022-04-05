package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {

    @DisplayName("퀸은 상하좌우 대각으로 직진할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("queenMoveTestSet")
    void queenMoveTest(Position source, Position destination) {
        Piece piece = new Queen(Color.BLACK);

        assertThat(piece.canMove(source, destination)).isTrue();
    }

    static Stream<Arguments> queenMoveTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e5")), // 동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("h5")), // 동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c5")), // 서
                Arguments.of(Position.valueOf("d5"), Position.valueOf("a5")), // 서
                Arguments.of(Position.valueOf("d5"), Position.valueOf("d4")), // 남
                Arguments.of(Position.valueOf("d5"), Position.valueOf("d1")), // 남
                Arguments.of(Position.valueOf("d5"), Position.valueOf("d6")), // 북
                Arguments.of(Position.valueOf("d5"), Position.valueOf("d8")), // 북
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e6")), // 북동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("g8")), // 북동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c6")), // 북서
                Arguments.of(Position.valueOf("d5"), Position.valueOf("a8")), // 북서
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e4")), // 남동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("h1")), // 남동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c4")), // 남서
                Arguments.of(Position.valueOf("d5"), Position.valueOf("a2"))  // 남서
        );
    }

    @DisplayName("퀸은 북동북으로 이동할 수 없다")
    @Test
    void queenCanNotMove() {
        Piece piece = new Queen(Color.BLACK);
        Position source = Position.valueOf("d5");
        Position destination = Position.valueOf("e7");

        assertThat(piece.canMove(source, destination)).isFalse();
    }
}
