package chess.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        final Position from = new Position(2, 2);
        final Position to = new Position(2, 4);
        assertThatThrownBy(() -> end.move(from, to))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("종료된 상태에서는 end 명령을 실행할 수 없습니다.")
    void end() {
        assertThatThrownBy(end::end)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
