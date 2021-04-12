package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chess.Status;

class StatusTest {

    @Test
    @DisplayName("실행 중인지 테스트")
    void isRunningTest() {

        // given
        Status status = Status.RUNNING;

        // when
        boolean isRunning = status.isRunning();

        // then
        assertThat(isRunning).isTrue();
    }

    @Test
    @DisplayName("실행 중이 아닌지 테스트")
    void isRunningTest_False() {

        // given
        Status status = Status.STOP;

        // when
        boolean isRunning = status.isRunning();

        // then
        assertThat(isRunning).isFalse();
    }

    @Test
    @DisplayName("게임 종료인지 테스트")
    void isTerminatedTest() {

        // given
        Status status = Status.TERMINATED;

        // when
        boolean isRunning = status.isTerminated();

        // then
        assertThat(isRunning).isTrue();
    }

    @Test
    @DisplayName("게임 종료가 아닌지 테스트")
    void isTerminatedTest_False() {

        // given
        Status status = Status.RUNNING;

        // when
        boolean isRunning = status.isTerminated();

        // then
        assertThat(isRunning).isFalse();
    }
}
