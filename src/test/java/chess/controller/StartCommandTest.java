package chess.controller;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class StartCommandTest {

    @Test
    void StartCommand의_타입을_확인할_수_있다() {
        StartCommand startCommand = new StartCommand(CommandType.START);

        assertThat(startCommand.isSameType(CommandType.START)).isTrue();
    }

    @Test
    void StartCommand의_ChessGame판을_확인하면_예외가_발생한다() {
        StartCommand startCommand = new StartCommand(CommandType.START);

        assertThatThrownBy(startCommand::getChessGame)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임을 시작해야 체스판을 확인할 수 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "start ", " start", "Start", " start  "})
    void start를_입력받으면_MoveCommand_객체가_반환된다(String command) {
        StartCommand startCommand = new StartCommand(CommandType.START);
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

        Command result = startCommand.execute(input);

        assertThat(result.isSameType(CommandType.MOVE)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"start1", "star"})
    void start를_입력받지_않으면_예외가_발생한다(String command) {
        StartCommand startCommand = new StartCommand(CommandType.START);
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> startCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 명령어를 입력했습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"start  2", "sta rt"})
    void start명령어는_명령어만_입력_가능하다(String command) {
        StartCommand startCommand = new StartCommand(CommandType.START);
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> startCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start 명령어는 값을 하나만 입력해야합니다.");
    }
}
