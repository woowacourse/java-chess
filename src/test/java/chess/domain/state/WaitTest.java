package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.state.exception.UnsupportedCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WaitTest {

    private State wait;

    @BeforeEach
    void setUp() {
        Start start = new Start();
        start.receive("start");
        wait = start.next();
    }

    @DisplayName("입력대기상태 - move, status, end 커맨드가 올 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"move a2 a3", "status", "end"})
    void receive(String command) {
        assertThatCode(() -> wait.receive(command))
            .doesNotThrowAnyException();
    }

    @DisplayName("입력대기상태 - move, status, end 이외의 명령은 불가능하다.")
    @ParameterizedTest
    @ValueSource(strings = {"start"})
    void receive_unsupported_command(String command) {
        assertThatThrownBy(() -> wait.receive(command))
            .isInstanceOf(UnsupportedCommandException.class);
    }

    @DisplayName("입력대기상태 - status 커맨드가 입력되면 다음은 Status로 넘어간다.")
    @Test
    void next_statusCommand() {
        wait.receive("status");

        assertThat(wait.next())
            .isInstanceOf(Status.class);
    }

    @DisplayName("입력대기상태 - move 커맨드가 입력되면 다음은 Move로 넘어간다.")
    @Test
    void next_moveCommand() {
        wait.receive("move a2 a3");

        assertThat(wait.next())
            .isInstanceOf(Move.class);
    }

    @DisplayName("입력대기상태 - end 커맨드가 입력되면 다음은 end로 넘어간다.")
    @Test
    void next_endCommand() {
        wait.receive("end");

        assertThat(wait.next())
            .isInstanceOf(End.class);
    }

    @DisplayName("입력대기상태 - 결과를 지원하지 않는다.")
    @Test
    void bringResult() {
        assertThatThrownBy(() -> wait.bringResult())
            .isInstanceOf(UnsupportedCommandException.class);
    }

    @DisplayName("입력대기상태 - 결과 타입은 없다.")
    @Test
    void bringResultType() {
        assertThat(wait.bringResultType())
            .isEqualTo(ResultType.NONE);
    }

    @DisplayName("입력대기상태 - 게임 실행중이다.")
    @Test
    void isRunning() {
        assertThat(wait.isRunning())
            .isTrue();
    }

    @DisplayName("입력대기상태 - 커맨드 입력이 필요하다.")
    @Test
    void needsParam() {
        assertThat(wait.needsParam())
            .isTrue();
    }

    @DisplayName("입력대기상태 - 실패시 상태를 유지한다.")
    @Test
    void before() {
        assertThat(wait.before())
            .isInstanceOf(Wait.class);
    }
}
