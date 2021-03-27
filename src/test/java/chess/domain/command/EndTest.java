package chess.domain.command;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EndTest {
    @Test
    void End_커맨드_생성_확인() {
        Command end = CommandFactory.selectFirstCommand("end");

        assertThat(end).isInstanceOf(End.class);
    }

    @Test
    void End_커맨드_생성_에러() {
        assertThatThrownBy(() -> {
            CommandFactory.selectFirstCommand("oz");
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void Waiting_커맨드에서_변경_확인() {
        Command waiting = new Waiting();

        assertThat(waiting.changeRunningCommand("end")).isInstanceOf(End.class);
    }
}
