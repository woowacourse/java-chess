package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
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
            ChessGame chessGame = new ChessGame(BoardFactory.createChessBoard());
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
            List<String> inputs = InputView.inputProgressCommand();
            String commandMessage = inputs.get(COMMAND_INDEX);
            move(chessGame, inputs, commandMessage);
            showStatus(chessGame, commandMessage);
            endGame(chessGame, commandMessage);
        }
    }

    private void move(final ChessGame chessGame, final List<String> inputs,final String commandMessage) {
        if (commandMessage.equals(MOVE_COMMAND_MESSAGE)) {
            chessGame.move(inputs.get(SOURCE_INDEX), inputs.get(TARGET_INDEX));
            printCurrentBoard(chessGame);
        }
    }

    private void showStatus(final ChessGame chessGame, final String commandMessage) {
        if (commandMessage.equals(STATUS_COMMAND_MESSAGE)) {
            printStatus(chessGame);
        }
    }

    private void endGame(final ChessGame chessGame, final String commandMessage) {
        if (commandMessage.equals(END_COMMAND_MESSAGE)) {
            chessGame.turnOff();
            printStatus(chessGame);
        }
    }

    private void printStatus(final ChessGame chessGame) {
        Score score = chessGame.calculateScore();
        OutputView.printScore(score.getWhiteScore(), score.getBlackScore(), score.getWinColor().getValue());
    }
}
