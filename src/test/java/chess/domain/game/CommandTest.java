package chess.domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {
    @Test
    void start() {
        assertThat(Command.from("start")).isEqualTo(Command.START);
        assertThat(Command.from("Start")).isEqualTo(Command.START);
        assertThat(Command.from("START")).isEqualTo(Command.START);
    }


    @Test
    void end() {
        assertThat(Command.from("end")).isEqualTo(Command.END);
        assertThat(Command.from("End")).isEqualTo(Command.END);
        assertThat(Command.from("END")).isEqualTo(Command.END);
    }

    @Test
    void move() {
        assertThat(Command.from("move a2 a3")).isEqualTo(Command.MOVE);
        assertThat(Command.from("Move a3 a4")).isEqualTo(Command.MOVE);
        assertThat(Command.from("MOVE a2 a3")).isEqualTo(Command.MOVE);
    }

    @Test
    void status() {
        assertThat(Command.from("status")).isEqualTo(Command.STATUS);
        assertThat(Command.from("Status")).isEqualTo(Command.STATUS);
        assertThat(Command.from("STATUS")).isEqualTo(Command.STATUS);
    }


    @Test
    void optionSizeException() {
        assertThatThrownBy(() -> Command.from("move")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Command.from("move a2")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Command.from("move a2 a3 a4")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Command.from("status a2 a3 a4")).isInstanceOf(IllegalArgumentException.class);
    }
}