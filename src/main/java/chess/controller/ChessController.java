package chess.controller;

import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame;
        OutputView.printGameInitMessage();
        String option = InputView.inputOption();
        if (option.equals("start")) {
            chessGame = new ChessGame();
            OutputView.printInitialChessBoard(chessGame.getBoard());
            turn(chessGame);
        }
    }

    private void turn(ChessGame chessGame) {
        String inputOption = InputView.inputOption();
        boolean isMoveOption = inputOption.contains("move");

        if (isMoveOption) {
            chessGame.movePiece(inputOption);
        }
        OutputView.printInitialChessBoard(chessGame.getBoard());
    }
}

