package chess.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartedTest {

    private final Started started = Started.getInstance();

    @Test
    @DisplayName("시작된 상태면 start 명령을 실행할 수 없습니다.")
    void test_start() {
        assertThatThrownBy(started::start)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("move메서드는 board.move를 실행시켜준다.")
    void move() {
        final List<Position> positions =
                List.of(new Position(2, 2), new Position(2, 4));

        final Board board = new BoardFactory().createInitialBoard();
        final State state = started.move(board, positions);

        final Map<Position, Piece> pieceMap = board.getBoard();

        assertAll(
                () -> assertSame(state, Started.getInstance()),
                () -> assertFalse(pieceMap.containsKey(positions.get(0))),
                () -> assertTrue(pieceMap.containsKey(positions.get(1)))
        );
    }

    @Test
    @DisplayName("end는 종료 상태를 반환해준다.")
    void end() {
        final State end = started.end();
        assertSame(end, End.getInstance());
    }
}
