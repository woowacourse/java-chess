import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CommandTest {

    @DisplayName("START 커맨드")
    @Test
    void start() {
        //given
        final List<String> starts = List.of("Start", "START", "start", "staRt");

        //when

        //then
        Assertions.assertThat(starts).map(Command::from).allMatch(Command.START::equals);
    }

    @DisplayName("END 커맨드")
    @Test
    void end() {
        //given
        final List<String> starts = List.of("End", "end", "END");

        //when

        //then
        Assertions.assertThat(starts).map(Command::from).allMatch(Command.END::equals);
    }

    @DisplayName("MOVE 커맨드")
    @Test
    void move() {
        //given
        final List<String> starts = List.of("MOVE", "move", "Move");

        //when

        //then
        Assertions.assertThat(starts).map(Command::from).allMatch(Command.MOVE::equals);
    }
}
