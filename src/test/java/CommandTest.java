import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.gamestate.Finished;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.Running;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class CommandTest {

    @Test
    @DisplayName("시작 커맨드")
    void startCommand() {
        assertThat(Command.getByInput("start")).isEqualTo(Command.START);
    }

    @Test
    @DisplayName("종료 커맨드")
    void endCommand() {
        assertThat(Command.getByInput("end")).isEqualTo(Command.END);
    }

    @Test
    @DisplayName("시작 커맨드에 인자를 입력할 경우 예외 처리")
    void startCommandWithArgument() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                Command.getByInput("start a1")
        ).withMessage("유효하지 않은 명령입니다.");
    }

    @Test
    @DisplayName("종료 커맨드에 인자를 입력할 경우 예외 처리")
    void endCommandWithArgument() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                Command.getByInput("end a1")
        ).withMessage("유효하지 않은 명령입니다.");
    }

    @Test
    @DisplayName("무브 커맨드")
    void moveCommand() {
        assertThat(Command.getByInput("move")).isEqualTo(Command.MOVE);
    }

    @Test
    @DisplayName("무브 커맨드의 인자가 부족하거나 넘는 경우 예외처리")
    void moveCommandWithInvalidArgumentCount() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                Command.getByInput("move a1")
        ).withMessage("유효하지 않은 명령입니다.");

        assertThatIllegalArgumentException().isThrownBy(() ->
                Command.getByInput("move a1 a2")
        ).withMessage("유효하지 않은 명령입니다.");
    }

    @Test
    @DisplayName("인자 리스트의 개수를 확인하는 테스트")
    void testCountArguments() {
        String input = "move b2 b3";
        List<String> arguments = Arrays.asList(input.split(" "));
        assertThat(arguments).hasSize(3);
    }

    @Test
    @DisplayName("move 명령어의 인자개수가 잘 못 들어온 경우 예외처리 테스트")
    void testInvalidArgumentsSize() {
        ChessGame chessGame = new ChessGame(new Board());
        chessGame.start();

        String input1 = "move b1";
        List<String> arguments1 = Arrays.asList(input1.split(" "));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Command.MOVE.execute(chessGame, arguments1));

        String input2 = "move b1 b2 b3";
        List<String> arguments2 = Arrays.asList(input2.split(" "));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Command.MOVE.execute(chessGame, arguments2));
    }

    @Test
    @DisplayName("명령어에 따라 올바른 실행을 하는지 테스트")
    void testExecute() {
        ChessGame chessGame = new ChessGame(new Board());
        List<String> empty = new ArrayList<>();

        assertThat(chessGame.state() instanceof Ready).isTrue();

        Command.START.execute(chessGame, empty);
        assertThat(chessGame.state() instanceof Running).isTrue();

        Command.STATUS.execute(chessGame, empty);
        assertThat(chessGame.state() instanceof Running).isTrue();

        Command.MOVE.execute(chessGame, Arrays.asList("move", "b2", "b4"));
        assertThat(chessGame.state() instanceof Running).isTrue();

        Command.END.execute(chessGame, empty);
        assertThat(chessGame.state() instanceof Finished).isTrue();
    }
}
