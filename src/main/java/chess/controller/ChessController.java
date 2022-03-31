package chess.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final int MOVE_INPUT_FROM_INDEX = 1;
    private static final int MOVE_INPUT_TO_INDEX = 2;
    private static final String DELIMITER = " ";

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printGameInitMessage();

        while (!chessGame.isFinish()) {
            String input = InputView.inputOption();
            Command.run(chessGame, input);
        }
    }

    static void start(ChessGame chessGame, String optionInput) {
        chessGame.start();
        OutputView.printInitialChessBoard(chessGame.getBoard().getValue());
    }

    static void move(ChessGame chessGame, String optionInput) {
        List<String> inputs = Arrays.asList(optionInput.split(DELIMITER));
        chessGame.movePiece(inputs.get(MOVE_INPUT_FROM_INDEX), inputs.get(MOVE_INPUT_TO_INDEX));
        OutputView.printInitialChessBoard(chessGame.getBoard().getValue());
    }

    static void end(ChessGame chessGame, String optionInput) {
        if (!chessGame.isWaiting()) {
            printScore(chessGame);
            OutputView.printWinner(chessGame.judgeWinner());
        }
        chessGame.end();
    }

    static void status(ChessGame chessGame, String optionInput) {
        printScore(chessGame);
    }

    private static void printScore(ChessGame chessGame) {
        Map<Color, Double> scores = chessGame.calculateScore();
        OutputView.printScore(scores);
    }
}

