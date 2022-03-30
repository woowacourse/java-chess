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
                Arguments.of(Position.of("d5"), Position.of("e5")), // 동
                Arguments.of(Position.of("d5"), Position.of("h5")), // 동
                Arguments.of(Position.of("d5"), Position.of("c5")), // 서
                Arguments.of(Position.of("d5"), Position.of("a5")), // 서
                Arguments.of(Position.of("d5"), Position.of("d4")), // 남
                Arguments.of(Position.of("d5"), Position.of("d1")), // 남
                Arguments.of(Position.of("d5"), Position.of("d6")), // 북
                Arguments.of(Position.of("d5"), Position.of("d8")), // 북
                Arguments.of(Position.of("d5"), Position.of("e6")), // 북동
                Arguments.of(Position.of("d5"), Position.of("g8")), // 북동
                Arguments.of(Position.of("d5"), Position.of("c6")), // 북서
                Arguments.of(Position.of("d5"), Position.of("a8")), // 북서
                Arguments.of(Position.of("d5"), Position.of("e4")), // 남동
                Arguments.of(Position.of("d5"), Position.of("h1")), // 남동
                Arguments.of(Position.of("d5"), Position.of("c4")), // 남서
                Arguments.of(Position.of("d5"), Position.of("a2"))  // 남서
        );
    }

    @DisplayName("퀸은 북동북으로 이동할 수 없다")
    @Test
    void queenCanNotMove() {
        Piece piece = new Queen(Color.BLACK);
        Position source = Position.of("d5");
        Position destination = Position.of("e7");

        assertThat(piece.canMove(source, destination)).isFalse();
    }
}
