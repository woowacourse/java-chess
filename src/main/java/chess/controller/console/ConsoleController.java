package chess.controller.console;

import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.view.console.InputView;
import chess.view.console.OutputView;
import java.util.List;

public class ConsoleController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final String START_COMMAND_STRING = "start";
    private static final String END_COMMAND_STRING = "end";
    private static final String MOVE_COMMAND_STRING = "move";
    private static final String STATUS_COMMAND_STRING = "status";

    public void run() {
        OutputView.printStartGame();
        if (InputView.inputFirstCommand().equals(START_COMMAND_STRING)) {
            ChessGame chessGame = ChessGame.createBasic("ConsoleChessGame");
            printCurrentBoard(chessGame);
            progressChessGame(chessGame);
        }
        OutputView.printEndMessage();
    }

    private void printCurrentBoard(final ChessGame chessGame) {
        if (chessGame.isOn()) {
            OutputView.printBoard(chessGame.getCurrentBoard());
        }
    }

    private void progressChessGame(final ChessGame chessGame) {
        while (chessGame.isOn()) {
            List<String> inputs = InputView.inputCommandForProgressGame();
            String commandMessage = inputs.get(COMMAND_INDEX);
            move(commandMessage, chessGame, inputs);
            showStatus(commandMessage, chessGame);
            endGame(commandMessage, chessGame);
        }
    }

    private void move(final String commandMessage, final ChessGame chessGame, final List<String> inputs) {
        if (commandMessage.equals(MOVE_COMMAND_STRING)) {
            String rawSource = inputs.get(SOURCE_INDEX);
            String rawTarget = inputs.get(TARGET_INDEX);
            chessGame.move(
                    rawSource.charAt(COLUMN_INDEX), Character.getNumericValue(rawSource.charAt(ROW_INDEX)),
                    rawTarget.charAt(COLUMN_INDEX), Character.getNumericValue(rawTarget.charAt(ROW_INDEX))
            );
            printCurrentBoard(chessGame);
        }
    }

    private void showStatus(final String commandMessage, final ChessGame chessGame) {
        if (commandMessage.equals(STATUS_COMMAND_STRING)) {
            printStatus(chessGame);
        }
    }

    private void endGame(final String commandMessage, final ChessGame chessGame) {
        if (commandMessage.equals(END_COMMAND_STRING)) {
            chessGame.turnOff();
            printStatus(chessGame);
        }
    }

    private void printStatus(final ChessGame chessGame) {
        Result result = chessGame.generateResult();
        OutputView.printScore(result.getWhiteScore(), result.getBlackScore(), result.getWinningTeam().getValue());
    }
}
