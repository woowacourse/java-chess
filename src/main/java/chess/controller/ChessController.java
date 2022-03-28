package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printGameInitMessage();
        String input = InputView.inputOption();

        while (true) {
            OptionController.run(chessGame, input);
            input = InputView.inputOption();
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
            double whiteScore = chessGame.calculateScore(Color.WHITE);
            double blackScore = chessGame.calculateScore(Color.BLACK);
            OutputView.printScore(whiteScore, blackScore);
            OutputView.printWinner(chessGame.judgeWinner());
        }
        System.exit(0);
    }

    static void status(ChessGame chessGame, String optionInput) {
        double whiteScore = chessGame.calculateScore(Color.WHITE);
        double blackScore = chessGame.calculateScore(Color.BLACK);
        OutputView.printScore(whiteScore, blackScore);
    }
}

