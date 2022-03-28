package chess;

import chess.piece.Color;
import chess.view.OutputView;

import static chess.Command.*;
import static chess.view.InputView.inputCommand;
import static chess.view.OutputView.printStartMessage;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        printStartMessage();
        while (!chessGame.isFinished()) {
            playTurn(chessGame);
        }
        OutputView.printFinalScore(chessGame.computeScore(Color.BLACK),chessGame.computeScore(Color.WHITE));
    }

    private void playTurn(ChessGame chessGame) {
        String command = inputCommand();
        if (START.isValue(command)) {
            chessGame.start();
            OutputView.printBoard(chessGame.getChessBoard());
        }
        if (MOVE.isValue(command)) {
            chessGame.move(command);
            OutputView.printBoard(chessGame.getChessBoard());
        }
        if (STATUS.isValue(command)) {
            OutputView.printProgressScore(chessGame.computeScore(Color.BLACK), chessGame.computeScore(Color.WHITE));
        }
        if (END.isValue(command)) {
            chessGame.end();
            OutputView.printBoard(chessGame.getChessBoard());
        }
    }
}
