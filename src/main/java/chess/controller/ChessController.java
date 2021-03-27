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
            executeFirstCommand(chessGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    private void executeFirstCommand(ChessGame chessGame) {
        if (chessGame.isStart()) {
            OutputView.printChessBoard(chessGame.getChessBoard());
            gamePlay(chessGame);
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
            if (chessGame.isEnd()) {
                return;
            }
            executeRunningCommand(chessGame);
        }
    }

    private void executeRunningCommand(ChessGame chessGame) {
        if (chessGame.isStatus()) {
            OutputView.printStatus(chessGame.getChessBoard());
        }
        if (chessGame.isMove()) {
            chessGame.move();
            OutputView.printChessBoard(chessGame.getChessBoard());
        }
    }
}
