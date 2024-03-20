package chess.controller;

import chess.domain.board.ChessBoard;

public class ChessGame {
    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialBoard();
    }
}
