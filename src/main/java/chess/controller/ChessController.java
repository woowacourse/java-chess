package chess.controller;

import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

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
        OutputView.printInitialChessBoard(chessGame.getBoard());
    }

    static void move(ChessGame chessGame, String optionInput) {
        chessGame.movePiece(optionInput);
        OutputView.printInitialChessBoard(chessGame.getBoard());
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

