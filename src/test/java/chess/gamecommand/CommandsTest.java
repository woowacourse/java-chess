package chess.gamecommand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.command.Commands;
import chess.domain.command.GameCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CommandsTest {

    @Test
    @DisplayName("move 명령어가 잘못된 포맷으로 들어오면 예외를 던진다.")
    void validateMoveCommand() {
        // given
        List<String> moveCommands = Arrays.asList("move", "a");

        // when, then
        assertThatThrownBy(() -> new Commands(moveCommands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] move 명령어는 소스 위치와 타겟 위치를 모두 입력해야 합니다.");
    }

    @ParameterizedTest
    @MethodSource("getCommand_TestCases")
    @DisplayName("커맨드를 만들어서 반환한다.")
    void getCommand(Commands commands, GameCommand expectedGameCommand) {
        // when, then
        assertThat(commands.getCommand()).isEqualTo(expectedGameCommand);
    }

    static Stream<Arguments> getCommand_TestCases() {
        return Stream.of(
                Arguments.arguments(new Commands(Arrays.asList("start")), GameCommand.START),
                Arguments.arguments(new Commands(Arrays.asList("move", "a2", "a4")), GameCommand.MOVE),
                Arguments.arguments(new Commands(Arrays.asList("end")), GameCommand.END)
        );
    }

    @Test
    @DisplayName("move 커맨드일 떄 소스 위치와 타겟 위치를 생성한다.")
    void generatePosition() {
        // given
        Commands commands = new Commands(Arrays.asList("move", "a2", "a4"));

        // when
        Position sourcePosition = commands.generateSourcePosition();
        Position targetPosition = commands.generateTargetPosition();

        // then
        assertThat(sourcePosition).isEqualTo(new Position(File.A, Rank.TWO));
        assertThat(targetPosition).isEqualTo(new Position(File.A, Rank.FOUR));
    }
}
