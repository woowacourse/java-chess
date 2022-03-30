package chess.controller;

import chess.domain.command.Command;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printCommandGuide();

        while (true) {
            Command command = InputView.inputCommand();
            if (command.isStart()) {
                chessGame.startGame();
                OutputView.printBoard(chessGame.getBoard());
            }

            if (command.isMove()) {
                chessGame.movePiece(command.getFirstPosition(), command.getSecondPosition());
                OutputView.printBoard(chessGame.getBoard());
            }

            if (command.isStatus()) {
                OutputView.printScore(chessGame.getBoard());
            }

            if (command.isEnd()) {
                chessGame.endGame();
            }
        }
    }
}
