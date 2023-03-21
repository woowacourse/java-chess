package chess.domain.commnad;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.exception.CommandException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LoadGameCommandTest {

    @Test
    @DisplayName("new_game 커맨드를 생성한다.")
    void create_success_of_new_game_command() {
        // given
        String input = "1";

        // when
        LoadGameCommand loadGameCommand = LoadGameCommand.from(input);

        // then
        assertThat(loadGameCommand.isNewGame()).isTrue();
    }

    @Test
    @DisplayName("saved_game 커맨드를 생성한다.")
    void create_success_of_saved_game_command() {
        // given
        String input = "2";

        // when
        LoadGameCommand loadGameCommand = LoadGameCommand.from(input);

        // then
        assertThat(loadGameCommand.isSavedGame()).isTrue();
    }

    @ParameterizedTest(name = "잘못된 input : {0}")
    @ValueSource(strings = {"3", "4", "newGame"})
    @DisplayName("커맨드 생성에 실패한다.")
    void create_fail(final String input) {
        // given

        // when & then
        assertThatThrownBy(() -> LoadGameCommand.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CommandException.STATUS_COMMAND_INVALID.getMessage());
    }
}
