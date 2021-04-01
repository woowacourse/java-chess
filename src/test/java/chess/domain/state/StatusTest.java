package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.result.ScoreResult;
import chess.domain.state.exception.UnsupportedCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusTest {

    private Status status;

    @BeforeEach
    void setUp() {
        State start = new Start();
        start.receive("start");
        State wait = start.next();
        wait.receive("status");
        status = (Status) wait.next();
    }

    @DisplayName("점수확인상태 - 명령을 입력 받을 수 없다.")
    @Test
    void receive_throw_exception() {
        assertThatThrownBy(() -> status.receive("end"))
            .isInstanceOf(UnsupportedCommandException.class);
    }

    @DisplayName("점수확인상태 - Wait로 넘어간다.")
    @Test
    void next() {
        State state = status.next();
        assertThat(state).isInstanceOf(Wait.class);
    }

    @DisplayName("점수확인상태 - 결과는 두 팀의 점수를 가지고 있다.")
    @Test
    void bringResult() {
        ScoreResult scoreResult = status.bringResult();

        assertThat(scoreResult.getWhiteScore()).isEqualTo(38.0);
        assertThat(scoreResult.getBlackScore()).isEqualTo(38.0);
    }

    @DisplayName("점수확인상태 - 결과 타입은 점수이다.")
    @Test
    void bringResultType() {
        assertThat(status.bringResultType())
            .isEqualTo(ResultType.SCORE);
    }

    @DisplayName("점수확인상태 - 게임 실행중이다.")
    @Test
    void isRunning() {
        assertThat(status.isRunning())
            .isTrue();
    }

    @DisplayName("점수확인상태 - 커맨드 입력이 필요없다.")
    @Test
    void needsParam() {
        assertThat(status.needsParam())
            .isFalse();
    }

    @DisplayName("이동상태 - 실패시 입력대기 상태로 돌아온다.")
    @Test
    void before() {
        assertThat(status.before())
            .isInstanceOf(Wait.class);
    }
}
