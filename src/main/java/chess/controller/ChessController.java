package chess.controller;

import chess.domain.ChessGame;
import chess.dto.ChessMenDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printChessGameStart();
        while (InputView.requestPlay()) {
            playChessGame();
        }
    }

    private void playChessGame() {
        ChessGame chessGame = ChessGame.create();
        OutputView.printCurrentChessBoard(ChessMenDto.of(chessGame.getBlackChessMen()),
                ChessMenDto.of(chessGame.getWhiteChessMen()));

    }
}
