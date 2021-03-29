package controller;

import domain.chessgame.ChessGame;
import domain.chessgame.ChessGameManager;
import domain.command.Commands;
import view.InputView;
import view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printGameInformation();
        ChessGameManager chessGameManager = new ChessGameManager(new ChessGame(), new Commands());
        try {
            chessGameManager.runChessGame(InputView.command());
            proceed(chessGameManager);
            OutputView.printResult(chessGameManager);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    private void proceed(ChessGameManager chessGameManager) {
        try {
            play(chessGameManager);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            proceed(chessGameManager);
        }
    }

    private void play(ChessGameManager chessGameManager) {
        OutputView.printBoard(chessGameManager);
        while (chessGameManager.isPlaying()) {
            chessGameManager.runChessGame(InputView.command());
            printCommandResult(chessGameManager);
        }
    }

    public void printCommandResult(ChessGameManager chessGameManager) {
        if (chessGameManager.isStatusCommandCalled()) {
            OutputView.printScore(chessGameManager);
            return;
        }
        OutputView.printBoard(chessGameManager);
    }

}
