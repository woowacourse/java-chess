package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.commnad.Command;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @ParameterizedTest(name = "invalid command : {0}")
    @EmptySource
    @ValueSource(strings = {"1", "2", "3", "a2 b2", "moving"})
    @DisplayName("잘못된 게임 명령어 입력이 들어오면 예외를 발생시킨다.")
    void throws_exception_when_input_invalid_command(final String input) {
        // when & then
        assertThatThrownBy(() -> Command.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("start 인 경우 생성이 성공적으로 된다.")
    void create_success_when_command_is_start() {
        // given
        String input = "start";

        // when
        Command command = Command.from(input);

        // then
        assertAll(
                () -> assertThat(command.isCreateNewGame()).isTrue(),
                () -> assertThat(command.isGameStop()).isFalse(),
                () -> assertThat(command.isMove()).isFalse(),
                () -> assertThat(command.isStatus()).isFalse()
        );
    }

    @Test
    @DisplayName("end 인 경우 생성이 성공적으로 된다.")
    void create_success_when_command_is_end() {
        // given
        String input = "end";

        // when
        Command command = Command.from(input);

        // then
        assertAll(
                () -> assertThat(command.isCreateNewGame()).isFalse(),
                () -> assertThat(command.isGameStop()).isTrue(),
                () -> assertThat(command.isMove()).isFalse(),
                () -> assertThat(command.isStatus()).isFalse()
        );
    }

    @Test
    @DisplayName("status 인 경우 생성이 성공적으로 된다.")
    void create_success_when_command_is_status() {
        // given
        String input = "status";

        // when
        Command command = Command.from(input);

        // then
        assertAll(
                () -> assertThat(command.isCreateNewGame()).isFalse(),
                () -> assertThat(command.isGameStop()).isFalse(),
                () -> assertThat(command.isMove()).isFalse(),
                () -> assertThat(command.isStatus()).isTrue()
        );
    }

    @Test
    @DisplayName("start 인 경우 생성이 성공적으로 된다.")
    void create_success_when_command_is_move() {
        // given
        String input = "move a2 a3";
        List<String> expectedResult = List.of(input.split(" "));

        // when
        Command command = Command.from(input);

        // then
        assertAll(
                () -> assertThat(command.isCreateNewGame()).isFalse(),
                () -> assertThat(command.isGameStop()).isFalse(),
                () -> assertThat(command.isMove()).isTrue(),
                () -> assertThat(command.findSelectedPiece()).isEqualTo(expectedResult.get(1)),
                () -> assertThat(command.findDestination()).isEqualTo(expectedResult.get(2))
        );
    }
}
