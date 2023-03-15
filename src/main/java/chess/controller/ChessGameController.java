package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardFactory;
import chess.view.OutputView;

public class ChessGameController {
    public void run() {
        printChessBoard();
    }

    private void printChessBoard() {
        OutputView outputView = new OutputView();

        ChessBoard chessBoard = new ChessBoardFactory().generate();
        outputView.printChessBoard(ChessBoardDto.of(chessBoard.getPieces()));
    }
}
