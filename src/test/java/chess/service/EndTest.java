package chess.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    private static final End end = End.getInstance();

    @Test
    @DisplayName("종료된 상태에서는 start 명령을 실행할 수 없습니다.")
    void start() {
        assertThatThrownBy(end::start)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("종료된 상태에서는 move 명령을 실행할 수 없습니다.")
    void move() {
        final Board initialBoard = new BoardFactory().createInitialBoard();
        final Position from = new Position(2, 2);
        final Position to = new Position(2, 4);
        assertThatThrownBy(() -> end.move(initialBoard, from, to))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("종료된 상태에서는 end 명령을 실행할 수 없습니다.")
    void end() {
        assertThatThrownBy(end::end)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
