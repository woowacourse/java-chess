package chess.domain.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StatusTest {
    private Command waiting;
    @BeforeEach
    public void setUp() {
        waiting = new Waiting();
    }

    @Test
    void Status_커맨드_생성_확인() {
        assertThat(waiting.changeRunningCommand("status")).isInstanceOf(Status.class);
    }

    @Test
    void Status_커맨드_생성_에러() {
        assertThatThrownBy(() -> {
            waiting.changeRunningCommand("oz");
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void Waiting_커맨드_변경_확인() {
        Command status = waiting.changeRunningCommand("status");

        assertThat(status.changeWaiting()).isInstanceOf(Waiting.class);
    }
}
