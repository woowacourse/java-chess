package chess.domain.command;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CommandsTest {
    @Test
    void start() {
        assertThat(Commands.from("start")).isEqualTo(Commands.START);
        assertThat(Commands.from("Start")).isEqualTo(Commands.START);
        assertThat(Commands.from("START")).isEqualTo(Commands.START);
    }


    @Test
    void end() {
        assertThat(Commands.from("end")).isEqualTo(Commands.END);
        assertThat(Commands.from("End")).isEqualTo(Commands.END);
        assertThat(Commands.from("END")).isEqualTo(Commands.END);
    }

    @Test
    void move() {
        assertThat(Commands.from("move a2 a3")).isEqualTo(Commands.MOVE);
        assertThat(Commands.from("Move a3 a4")).isEqualTo(Commands.MOVE);
        assertThat(Commands.from("MOVE a2 a3")).isEqualTo(Commands.MOVE);
    }

    @Test
    void status() {
        assertThat(Commands.from("status")).isEqualTo(Commands.STATUS);
        assertThat(Commands.from("Status")).isEqualTo(Commands.STATUS);
        assertThat(Commands.from("STATUS")).isEqualTo(Commands.STATUS);
    }


    @Test
    void optionSizeException() {
        assertThatThrownBy(() -> Commands.from("move")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Commands.from("move a2")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Commands.from("move a2 a3 a4")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Commands.from("status a2 a3 a4")).isInstanceOf(IllegalArgumentException.class);
    }
}