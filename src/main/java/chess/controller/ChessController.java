package chess.controller;

import java.util.Arrays;
import java.util.List;

import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final int MOVE_INPUT_FROM_INDEX = 1;
    private static final int MOVE_INPUT_TO_INDEX = 2;
    private static final String DELIMITER = " ";

    public void run() {
        final ChessGame chessGame = new ChessGame();
        OutputView.printGameInitMessage();

        while (!chessGame.isFinish()) {
            Command.run(chessGame, InputView.inputOption());
        }
    }

    static void start(final ChessGame chessGame, final String optionInput) {
        chessGame.start();
        OutputView.printInitialChessBoard(chessGame.getBoard().getValue());
    }

    static void move(final ChessGame chessGame, final String optionInput) {
        final List<String> inputs = Arrays.asList(optionInput.split(DELIMITER));
        chessGame.movePiece(inputs.get(MOVE_INPUT_FROM_INDEX), inputs.get(MOVE_INPUT_TO_INDEX));
        OutputView.printInitialChessBoard(chessGame.getBoard().getValue());
    }

    static void end(final ChessGame chessGame, final String optionInput) {
        if (!chessGame.isWaiting()) {
            printScore(chessGame);
            OutputView.printWinner(chessGame.judgeWinner());
        }
        chessGame.end();
    }

    static void status(final ChessGame chessGame, final String optionInput) {
        printScore(chessGame);
    }

    private static void printScore(final ChessGame chessGame) {
        OutputView.printScore(chessGame.calculateScore());
    }
}

