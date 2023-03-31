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
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ReadyCommandTest {

    private ChessGame chessGame;
    private BoardDao boardDao;

    @BeforeEach
    void init() {
        chessGame = new ChessGame(BoardFactory.createBoard(), Team.WHITE);
        boardDao = new InMemoryBoardDao(chessGame);
    }

    @Test
    void ReadyCommand의_타입을_확인할_수_있다() {

        Command readyCommand = new ReadyCommand(boardDao, new OutputView());

        assertThat(readyCommand.isSameType(CommandType.READY)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "start ", " start", "Start", " start  "})
    void start를_입력받으면_StartCommand_객체가_반환된다(String command) {
        Command startCommand = new ReadyCommand(boardDao, new OutputView());
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

        Command result = startCommand.execute(input);

        assertThat(result.isSameType(CommandType.START)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"start  2"})
    void start명령어는_명령어만_입력_가능하다(String command) {
        Command readyCommand = new ReadyCommand(boardDao, new OutputView());
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> readyCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start 명령어는 값을 하나만 입력해야합니다.");
    }
}
