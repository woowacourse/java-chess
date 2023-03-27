package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import chess.repository.BoardDao;
import chess.repository.InMemoryBoardDao;
import chess.repository.JdbcBoardDao;
import chess.repository.TestConnector;
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

import static chess.PositionFixture.B_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class EndCommandTest {


    private ChessGame chessGame;
    private BoardDao boardDao;

    @BeforeEach
    void init() {
        chessGame = new ChessGame(BoardFactory.createBoard(), Team.WHITE);
        boardDao = new InMemoryBoardDao(chessGame);
    }

    @Test
    void EndCommand의_타입을_확인할_수_있다() {

        Command endCommand = new EndCommand(boardDao, new OutputView());

        assertThat(endCommand.isSameType(CommandType.END)).isTrue();
    }

    @Test
    void EndCommand의_ChessGame판을_확인할_수_있다() {
        Command endCommand = new EndCommand(boardDao, new OutputView());

        assertThat(endCommand.getChessGameBoards().get(B_2)).isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "end", "move a2 a3", "move", "something"})
    void EndCommand는_execute를_하면_예외가_발생한다(String command) {
        Command endCommand = new EndCommand(boardDao, new OutputView());

        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> endCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 종료되었습니다.");
    }
}
