package chess.controller;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        try {
            OutputView.printStartMessage();
            ChessGame chessGame = new ChessGame();
            chessGame.selectFirstCommand(InputView.inputCommand());
            if (chessGame.isStart()) {
                OutputView.printChessBoard(chessGame.getChessBoard());
                gamePlay(chessGame);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    public void gamePlay(ChessGame chessGame) {
        try {
            playByCommand(chessGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            gamePlay(chessGame);
        }
    }

    private void playByCommand(ChessGame chessGame) {
        while (chessGame.isRunnable()) {
            chessGame.selectRunningCommand(InputView.inputCommand());
            if (chessGame.isMove()) {
                chessGame.move();
                OutputView.printChessBoard(chessGame.getChessBoard());
            }
            if (chessGame.isEnd()) {
                return;
            }
            if (chessGame.isStatus()) {
                OutputView.printStatus(chessGame.getChessBoard());
            }
        }
    }
}
