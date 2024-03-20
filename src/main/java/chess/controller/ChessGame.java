package chess.controller;

import chess.domain.board.ChessBoard;
import chess.dto.ChessBoardDto;
import chess.view.OutputView;

public class ChessGame {
    private final OutputView outputView = new OutputView();

    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialBoard();
        ChessBoardDto chessBoardDto = chessBoard.convertToDto();
        outputView.printChessBoard(chessBoardDto);
    }
}
