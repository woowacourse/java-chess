package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.feature.Color;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.gamestate.Ready;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Controller {
    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard, Color.WHITE, new Ready());
        OutputView.printMainScreen();

        startGame(chessGame);
        playGame(chessGame);
        gameResult(chessGame);
    }

    private void startGame(ChessGame chessGame) {
        try {
            List<String> input = InputView.takeInput();
            chessGame.start(input);
            if (chessGame.isOngoing()) {
                OutputView.printChessBoard(chessGame.getChessBoard());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            startGame(chessGame);
        }
    }

    private void playGame(ChessGame chessGame) {
        while (chessGame.isOngoing()) {
            play(chessGame);
        }
    }

    private void play(ChessGame chessGame) {
        try {
            List<String> input = InputView.takeInput();
            chessGame.play(input);
            if (chessGame.isOngoing()) {
                OutputView.printChessBoard(chessGame.getChessBoard());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            play(chessGame);
        }
    }

    private void gameResult(ChessGame chessGame) {
        try {
            Result result = chessGame.calculateResult();
            OutputView.printResult(result);
            run();
        } catch (UnsupportedOperationException ignored) {
        }
    }
}
