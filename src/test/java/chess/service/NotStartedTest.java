package chess.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertSame;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotStartedTest {

    private static final NotStarted notStarted = NotStarted.getInstance();

    @Test
    @DisplayName("start 메서드는 시작 상태를 반환해준다.")
    void test_start() {
        final State start = notStarted.start();

        assertSame(start, Started.getInstance());
    }

    @Test
    @DisplayName("시작되지 않은 상태에서는 말을 움직일 수 없습니다.")
    void test_move() {
        final Board initialBoard = new BoardFactory().createInitialBoard();
        final Position from = new Position(2, 2);
        final Position to = new Position(2, 4);
        assertThatThrownBy(() -> notStarted.move(initialBoard, from, to))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("end 메서드는 종료 상태를 반환해준다.")
    void test_end() {
        final State end = notStarted.end();

        assertSame(end, End.getInstance());
    }
}
