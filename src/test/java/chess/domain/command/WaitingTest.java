package chess.domain.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WaitingTest {
    private Command waiting;
    @BeforeEach
    void setUp() {
        waiting = new Waiting();
    }
    @Test
    void Waiting_커맨드_생성_확인() {
        assertThat(waiting).isInstanceOf(Waiting.class);
    }

    @Test
    void Waiting_에서_Move_커맨드_생성() {
        assertThat(waiting.changeRunningCommand("move a2 a4")).isInstanceOf(Move.class);
    }

    @Test
    void Waiting_에서_End_커맨드_생성() {
        assertThat(waiting.changeRunningCommand("end")).isInstanceOf(End.class);
    }

    @Test
    void Waiting_에서_Status_커맨드_생성() {
        assertThat(waiting.changeRunningCommand("status")).isInstanceOf(Status.class);
    }

    @Test
    void Waiting_에서_커맨드_생성_에러() {
        assertThatThrownBy(() -> {
            waiting.changeRunningCommand("oz");
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
