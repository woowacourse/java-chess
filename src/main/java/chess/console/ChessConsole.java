package chess.console;

import static chess.console.view.InputView.COMMAND_INDEX;

import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.domain.board.generator.BoardGenerator;
import chess.domain.game.ChessGame;
import chess.domain.game.Command;
import java.util.List;

public class ChessConsole {

    private final ChessGame chessGame = new ChessGame();

    public void play(BoardGenerator boardGenerator) {
        OutputView.printGuideMessage();

        if (!inputStart()) {
            return;
        }

        start(boardGenerator);

        boolean canPlay = true;
        while (canPlay) {
            canPlay = isExceedPlay(inputCommand());
        }
    }

    private boolean inputStart() {
        List<String> inputs = inputCommand();
        Command command = Command.of(inputs.get(COMMAND_INDEX));
        try {
            validStartType(command);
            return command.isStart();
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputStart();
        }
    }

    private List<String> inputCommand() {
        try {
            List<String> inputs = InputView.inputCommand();
            Command.of(inputs.get(COMMAND_INDEX));
            return inputs;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputCommand();
        }
    }

    private void validStartType(Command command) {
        if (!command.isStart() && !command.isEnd()) {
            throw new IllegalStateException("게임을 시작하지 않았습니다.");
        }
    }

    private boolean isExceedPlay(List<String> inputs) {
        Command command = Command.of(inputs.get(COMMAND_INDEX));

        if (chessGame.isEnd(command)) {
            return false;
        }

        move(command, inputs);
        status(command);

        return true;
    }

    private void start(BoardGenerator boardGenerator) {
        try {
            chessGame.start(boardGenerator);
            printBoard(chessGame.getBoard());
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void move(Command command, List<String> inputs) {
        if (!command.isMove()) {
            return;
        }

        try {
            chessGame.move(inputs);
            printBoard(chessGame.getBoard());
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void status(Command command) {
        if (!command.isStatus()) {
            return;
        }

        try {
            Score score = chessGame.status();
            OutputView.printStatus(score.getValue(), score.findWinTeam());
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void printBoard(Board board) {
        if (board != null) {
            OutputView.printBoard(board.getValue());
        }
    }
}
