package chess.controller;

import chess.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private final ChessGame chessGame;
    private boolean gameFlag = true;
    private String command;

    public ChessGameController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }


    public void run() {
        OutputView.printStartMessage();

        while (gameFlag) {
            command = InputView.getCommand();
            if ("end".equals(command)) {
                gameFlag = false;
            }

            if ("start".equals(command)) {
                initSetting();
            }
        }
    }

    private void initSetting() {
        chessGame.initSetting();
        OutputView.printBoard(chessGame.getBoard());
    }
}
