package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.repository.BoardDao;
import chess.repository.InMemoryBoardDao;
import chess.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
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

    private ChessGame chessGame;
    private BoardDao boardDao;

    @BeforeEach
    void init() {
        chessGame = new ChessGame(BoardFactory.createBoard(), Team.WHITE);
        boardDao = new InMemoryBoardDao(chessGame);
    }

    @Test
    void StartCommand의_타입을_확인할_수_있다() {
        Command startCommand = new StartCommand(boardDao);

        assertThat(startCommand.isSameType(CommandType.START)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "emd", "sta rt"})
    void move나_status를_입력받지_않으면_예외가_발생한다(String command) {
        Command startCommand = new StartCommand(boardDao);
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> startCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 명령어를 입력했습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"status  a2"})
    void status명령어는_명령어만_입력_가능하다(String command) {
        Command startCommand = new StartCommand(boardDao);
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> startCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("status 명령어는 값을 하나만 입력해야합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"move a2"})
    void move명령어는_도착지와_출발지의_정보를_입력해야한다(String command) {
        Command startCommand = new StartCommand(boardDao);
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> startCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start 명령어는 값을 하나만 입력해야합니다.");
    }
}
