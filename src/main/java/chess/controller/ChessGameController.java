package chess.controller;

import chess.domain.game.ChessGameProgress;
import chess.domain.game.Score;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final String END_COMMAND_MESSAGE = "end";
    private static final String MOVE_COMMAND_MESSAGE = "move";
    private static final String STATUS_COMMAND_MESSAGE = "status";

    public void run() {
        OutputView.printStartGame();
        if (InputView.inputInitialCommand()) {
            ChessGameProgress chessGameProgress = new ChessGameProgress();
            printCurrentBoard(chessGameProgress);
            progressChessGame(chessGameProgress);
        }
        OutputView.printEndMessage();
    }

    private void progressChessGame(final ChessGameProgress chessGameProgress) {
        while (chessGameProgress.isOn()) {
            List<String> inputs = InputView.inputProgressCommand();
            String commandMessage = inputs.get(COMMAND_INDEX);
            if (commandMessage.equals(END_COMMAND_MESSAGE)) {
                chessGameProgress.turnOff();
            }
            if (commandMessage.equals(STATUS_COMMAND_MESSAGE)) {
                Score score = chessGameProgress.calculateScore();
                // 아웃풋 만들기
            }
            if (commandMessage.equals(MOVE_COMMAND_MESSAGE)) {
                chessGameProgress.move(inputs.get(SOURCE_INDEX), inputs.get(TARGET_INDEX));
                printCurrentBoard(chessGameProgress);
            }
        }
    }

    private void printCurrentBoard(final ChessGameProgress chessGameProgress) {
        if (chessGameProgress.isOn()) {
            OutputView.printBoard(chessGameProgress.getCurrentBoard());
        }
    }
}
