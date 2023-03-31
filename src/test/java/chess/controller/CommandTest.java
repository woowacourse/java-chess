package chess.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CommandTest {

    @ParameterizedTest(name = "입력한 명령어에 따라서 Command를 조회해온다.")
    @CsvSource(value = {"start:START", "move a2 a4:MOVE", "end:END", "status:STATUS"}, delimiter = ':')
    void findCommand_success(final String command, final CommandType commandType) {
        // given
        final List<String> commands = Arrays.asList(command.split(" "));
        final Command expected = new Command(commandType, commands);

        // when
        final Command actual = Command.findCommand(commands);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "start, move, end, status 외의 명령어를 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"star", "@#$F@#", "203948029f"})
    void findCommand_fail(final String command) {
        // given
        final List<String> commands = Arrays.asList(command.split(" "));

        // when, then
        assertThatThrownBy(() -> Command.findCommand(commands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 명령어 입력입니다.");
    }

    @Test
    @DisplayName("현재 명령어가 start인지 판단한다.")
    void isStart() {
        // given
        final Command command = new Command(CommandType.START, List.of("start"));

        // when, then
        assertThat(command.isStart())
                .isTrue();
    }

    @Test
    @DisplayName("현재 명령어가 move인지 판단한다.")
    void isMove() {
        // given
        final Command command = new Command(CommandType.MOVE, List.of("move", "a2", "a4"));

        // when, then
        assertThat(command.isMove())
                .isTrue();
    }

    @Test
    @DisplayName("현재 명령어가 end인지 판단한다.")
    void isEnd() {
        // given
        final Command command = new Command(CommandType.END, List.of("end"));

        // when, then
        assertThat(command.isEnd())
                .isTrue();
    }

    @Test
    @DisplayName("현재 명령어가 status인지 판단한다.")
    void isStatus() {
        // given
        final Command command = new Command(CommandType.STATUS, List.of("status"));

        // when, then
        assertThat(command.isStatus())
                .isTrue();
    }

    @ParameterizedTest(name = "사용자가 입력한 명령어가 move일 때 명령어의 길이가 3인지 판단한다.")
    @CsvSource(value = {"move:false", "move : false", "move a3:false", "move a2 a4:true", "move a3 a4 a5:false"}, delimiter = ':')
    void isCorrectWhenMove(final String userCommand, final boolean expected) {
        // given
        List<String> commands = Arrays.asList(userCommand.split(" "));
        final Command command = new Command(CommandType.MOVE, commands);

        // when
        boolean actual = command.isCorrectWhenMove();

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
