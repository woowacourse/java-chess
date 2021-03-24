package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public static final BoardFactory boardFactory = new BoardFactory();

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printManual();
        playGame(chessGame);
    }

    public void playGame(ChessGame chessGame) {
        while (true) {
            commandAndExecute(chessGame);
        }
    }

    private void commandAndExecute(ChessGame chessGame) {
        try {
            Command.execute(chessGame, InputView.inputCommand());
            OutputView.printBoard(chessGame.getBoard());
        } catch (Exception e) {
            OutputView.printMessage(e.getMessage());
            playGame(chessGame);
        }
    }
}