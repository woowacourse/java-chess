package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertSame;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotStartedTest {

    private static final NotStarted notStarted = NotStarted.getInstance();

    @Test
    @DisplayName("start 메서드는 시작 상태를 반환해준다.")
    void test_start() {
        final State start = notStarted.start();

        assertThat(start)
                .isInstanceOf(Started.class);
    }

    @Test
    @DisplayName("시작되지 않은 상태에서는 말을 움직일 수 없습니다.")
    void test_move() {
        final Position from = new Position(2, 2);
        final Position to = new Position(2, 4);
        assertThatThrownBy(() -> notStarted.move(from, to))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("end 메서드는 종료 상태를 반환해준다.")
    void test_end() {
        final State end = notStarted.end();

        assertSame(end, End.getInstance());
    }
}
