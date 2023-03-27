package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Queen;
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

import static chess.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class StatusCommandTest {

    private ChessGame chessGame;
    private BoardDao boardDao;

    @BeforeEach
    void init() {
        chessGame = new ChessGame(BoardFactory.createBoard(), Team.WHITE);
        chessGame.movePiece(E_2, E_4);
        chessGame.movePiece(F_7, F_5);
        chessGame.movePiece(E_4, F_5);
        chessGame.movePiece(G_7, G_5);
        chessGame.movePiece(D_1, H_5);
        chessGame.movePiece(C_7, C_6);
        chessGame.movePiece(H_5, E_8); // 화이트 팀이 킹을 잡는 상황

        boardDao = new InMemoryBoardDao(chessGame);

    }

    @Test
    void StatusCommand의_타입을_확인할_수_있다() {
        Command statusCommand = new StatusCommand(boardDao, new OutputView());

        assertThat(statusCommand.isSameType(CommandType.STATUS)).isTrue();
    }

    @Test
    void statusCommand의_ChessGame판을_확인할_수_있다() {
        Command statusCommand = new StatusCommand(boardDao, new OutputView());

        assertThat(statusCommand.getChessGameBoards().get(E_8)).isInstanceOf(Queen.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"status", "status ", " status", "STATUS", " status  "})
    void status를_입력받으면_StatusCommand_객체가_반환된다(String command) {
        Command statusCommand = new StatusCommand(boardDao, new OutputView());
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

        Command result = statusCommand.execute(input);

        assertThat(result.isSameType(CommandType.STATUS)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"end", "end ", " end", "END", " end  "})
    void end를_입력받으면_EndCommand_객체가_반환된다(String command) {
        Command statusCommand = new StatusCommand(boardDao, new OutputView());
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

        Command result = statusCommand.execute(input);

        assertThat(result.isSameType(CommandType.END)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "move a2 a3"})
    void status나_end를_입력받지_않으면_예외가_발생한다(String command) {
        Command statusCommand = new StatusCommand(boardDao, new OutputView());
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> statusCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 종료되어 상태를 확인하시려면 status를, 게임을 종료하려면 end를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"status1", "st", "sta tus"})
    void 올바른_명령어가_아닌_값이_입력되면_예외가_발생한다(String command) {
        Command statusCommand = new StatusCommand(boardDao, new OutputView());
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> statusCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 명령어를 입력했습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"status  2"})
    void status명령어는_명령어만_입력_가능하다(String command) {
        Command statusCommand = new StatusCommand(boardDao, new OutputView());
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> statusCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("status 명령어는 값을 하나만 입력해야합니다.");
    }
}
