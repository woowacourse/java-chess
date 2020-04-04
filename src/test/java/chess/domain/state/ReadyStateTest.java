package chess.domain.state;

import chess.domain.MoveParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyStateTest {

    ReadyState readyState;

    @BeforeEach
    void setUp() {
        readyState = new ReadyState();
    }

    @Test
    @DisplayName("ReadyState는 Board를 초기화하고 RunningState로 진입")
    void start() {
        assertThat(readyState.start(Arrays.asList("new")))
                .isInstanceOf(RunningState.class);
    }

    @Test
    @DisplayName("ReadyState는 move 메서드를 지원하지 않음")
    void move() {
        assertThatThrownBy(() -> readyState.move(MoveParameter.of(Arrays.asList("B1", "A3"))))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("ReadyState는 end 메서드를 지원하지 않음")
    void end() {
        assertThatThrownBy(() -> readyState.end(new ArrayList<>()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("ReadyState는 getBoard 메서드를 지원하지 않음")
    void getBoard() {
        assertThatThrownBy(() -> readyState.getChessGame())
                .isInstanceOf(UnsupportedOperationException.class);
    }
}