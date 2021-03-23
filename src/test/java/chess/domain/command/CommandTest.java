package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @DisplayName("start 커맨드가 들어오면 Start상태를 반환한다.")
    @Test
    void start() {
        Command command = CommandFactory.initialCommand("start");

        assertThat(command.isStart()).isEqualTo(true);
        assertThat(command.isEnd()).isEqualTo(false);
        assertThat(command.isMove()).isEqualTo(false);
        assertThat(command.isStatus()).isEqualTo(false);
        assertThat(command.isReady()).isEqualTo(false);
    }

    @DisplayName("start가 들어오고, move 커맨드가 들어오면 move상태로 변경한다.")
    @Test
    void move() {
        Command command = CommandFactory.initialCommand("start");
        Command move = command.execute("move");

        assertThat(move.isStart()).isEqualTo(false);
        assertThat(move.isEnd()).isEqualTo(false);
        assertThat(move.isMove()).isEqualTo(true);
        assertThat(move.isStatus()).isEqualTo(false);
        assertThat(command.isReady()).isEqualTo(false);
    }

    @DisplayName("start가 들어오고, status 커맨드가 들어오면 move상태로 변경한다.")
    @Test
    void status() {
        Command command = CommandFactory.initialCommand("start");
        Command status = command.execute("status");

        assertThat(status.isStart()).isEqualTo(false);
        assertThat(status.isEnd()).isEqualTo(true);
        assertThat(status.isMove()).isEqualTo(false);
        assertThat(status.isStatus()).isEqualTo(true);
        assertThat(status.isReady()).isEqualTo(false);
    }

    @DisplayName("move를 한 뒤, Ready상태로 변경한다.")
    @Test
    void ready() {
        Command command = CommandFactory.initialCommand("start");
        Command move = command.execute("move");
        Command ready = move.ready();

        assertThat(ready.isStart()).isEqualTo(false);
        assertThat(ready.isEnd()).isEqualTo(false);
        assertThat(ready.isMove()).isEqualTo(false);
        assertThat(ready.isStatus()).isEqualTo(false);
        assertThat(ready.isReady()).isEqualTo(true);
    }

    @DisplayName("move를 한 뒤, Status상태로 변경한다.")
    @Test
    void statusAfterMove() {
        Command command = CommandFactory.initialCommand("start");
        Command move = command.execute("move");
        Command ready = move.ready();
        Command status = ready.execute("status");

        assertThat(status.isStart()).isEqualTo(false);
        assertThat(status.isEnd()).isEqualTo(true);
        assertThat(status.isMove()).isEqualTo(false);
        assertThat(status.isStatus()).isEqualTo(true);
        assertThat(status.isReady()).isEqualTo(false);
    }

    @DisplayName("start를 한 뒤, end 상태로 변경한다.")
    @Test
    void end() {
        Command command = CommandFactory.initialCommand("start");
        Command end = command.execute("end");

        assertThat(end.isStart()).isEqualTo(false);
        assertThat(end.isEnd()).isEqualTo(true);
        assertThat(end.isMove()).isEqualTo(false);
        assertThat(end.isStatus()).isEqualTo(false);
        assertThat(end.isReady()).isEqualTo(false);
    }

    @DisplayName("start 상태에서 옳지않은 커맨드가 들어오면 예가 발생한다.")
    @Test
    void startException() {
        Command command = CommandFactory.initialCommand("start");

        assertThatThrownBy(() -> command.execute("mive"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("ready 상태에서 옳지않은 커맨드가 들어오면 예외가 발생한다.")
    @Test
    void readyException() {
        Command command = CommandFactory.initialCommand("start");
        Command move = command.execute("move");
        Command ready = move.ready();

        assertThatThrownBy(() -> ready.execute("mive"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("move 상태에서 커맨드를 실행하려하면 예외가 발생한다.")
    @Test
    void moveException() {
        Command command = CommandFactory.initialCommand("start");
        Command move = command.execute("move");

        assertThatThrownBy(() -> move.execute("end"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("end 상태에서 커맨드를 실행하려하면 예외가 발생한다.")
    @Test
    void endException() {
        Command command = CommandFactory.initialCommand("start");
        Command end = command.execute("end");

        assertThatThrownBy(() -> end.execute("end"))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}