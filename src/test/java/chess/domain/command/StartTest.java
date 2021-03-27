package chess.domain.command;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StartTest {

    @Test
    void Start_커맨드_생성_확인() {
        Command start = CommandFactory.selectFirstCommand("start");

        assertThat(start).isInstanceOf(Start.class);
    }

    @Test
    void Start_커맨드_생성_에러() {
        assertThatThrownBy(() -> {
            CommandFactory.selectFirstCommand("oz");
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void Waiting_커맨드_변경_확인() {
        Command start = CommandFactory.selectFirstCommand("start");

        assertThat(start.changeWaiting()).isInstanceOf(Waiting.class);
    }
}
