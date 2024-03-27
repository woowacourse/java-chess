package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CommandTypeTest {

    @DisplayName("입력받은 명령어에 맞는 타입을 찾는다.")
    @ParameterizedTest
    @EnumSource(CommandType.class)
    public void findByCommand(CommandType expected) {
        //given
        List<String> command = new ArrayList<>();
        if (expected.isStart()) {
            command = List.of("start");
        }
        if (expected.isMove()) {
            command = List.of("move", "argument1", "argument2");
        }
        if (expected.isEnd()) {
            command = List.of("end");
        }

        //when
        CommandType commandType = CommandType.findByCommand(command);

        //then
        assertThat(commandType).isEqualTo(expected);
    }

    @DisplayName("존재하는 명령어이다.")
    @Test
    public void isCommandExists() {
        assertAll(
                () -> assertThat(CommandType.exists("start")).isTrue(),
                () -> assertThat(CommandType.exists("move")).isTrue(),
                () -> assertThat(CommandType.exists("end")).isTrue()
        );
    }

    @DisplayName("존재하는 명령어가 아니다.")
    @Test
    public void isCommandNotExists() {
        assertThat(CommandType.exists("invalid")).isFalse();
    }

    @DisplayName("입력받은 명령어의 인자 개수가 올바르다.")
    @ParameterizedTest
    @EnumSource(CommandType.class)
    public void isValidArgumentCount(CommandType commandType) {
        //given
        List<String> command = new ArrayList<>();
        if (commandType.isStart()) {
            command = List.of("start");
        }
        if (commandType.isMove()) {
            command = List.of("move", "argument1", "argument2");
        }
        if (commandType.isEnd()) {
            command = List.of("end");
        }

        //then
        assertThat(CommandType.isValidArguments(command)).isTrue();
    }

    @DisplayName("입력받은 명령어의 인자 개수가 올바르지 않다.")
    @Test
    public void isInvalidArgumentCount() {
        //given
        List<String> invalidMoveArguments = Arrays.asList("move", "argument1");
        List<String> invalidStartArguments = Arrays.asList("start", "argument1");
        List<String> invalidEndArguments = Arrays.asList("end", "argument1");

        //when & then
        assertAll(
                () -> assertThat(CommandType.isValidArguments(invalidMoveArguments)).isFalse(),
                () -> assertThat(CommandType.isValidArguments(invalidStartArguments)).isFalse(),
                () -> assertThat(CommandType.isValidArguments(invalidEndArguments)).isFalse()
        );
    }
}

