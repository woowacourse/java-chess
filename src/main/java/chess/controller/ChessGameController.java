package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.dto.ChessBoardDto;
import chess.view.OutputView;

public class ChessGameController {

    public void run() {
        ChessBoard chessBoard = new ChessBoard();

        printChessBoard(chessBoard);
    }

    private void printChessBoard(ChessBoard chessBoard) {
        ChessBoardDto chessBoardDto = chessBoard.createDto();
        OutputView.printChessBoard(chessBoardDto);
    }
}
