package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.state.exception.UnsupportedCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    private State end;

    @BeforeEach
    void setUp() {
        State start = new Start();
        start.receive("end");
        end = start.next();
    }

    @DisplayName("종료상태 - 명령을 입력 받을 수 없다.")
    @Test
    void receive_throw_exception() {
        assertThatThrownBy(() -> end.receive("start"))
            .isInstanceOf(UnsupportedCommandException.class);
    }

    @DisplayName("종료상태 - End로 넘어간다.")
    @Test
    void next() {
        State state = end.next();
        assertThat(state).isInstanceOf(End.class);
    }

    @DisplayName("종료상태 - 결과는 없다.")
    @Test
    void result() {
        assertThatThrownBy(() -> end.result())
            .isInstanceOf(UnsupportedCommandException.class);
    }

    @DisplayName("종료상태 - 결과 타입은 없다.")
    @Test
    void resultType() {
        assertThat(end.resultType())
            .isEqualTo(ResultType.NONE);
    }

    @DisplayName("종료상태 - 게임 실행중이 아니다.")
    @Test
    void isRunning() {
        assertThat(end.isRunning())
            .isFalse();
    }

    @DisplayName("종료상태 - 커맨드 입력이 필요없다.")
    @Test
    void needsParam() {
        assertThat(end.needsParam())
            .isFalse();
    }
}
