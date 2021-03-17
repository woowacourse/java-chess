package chess.controller;

import chess.domain.board.Board;
import chess.manager.ChessManager;

public class ChessController {

    public static void main(String[] args) {
        ChessManager chessManager = new ChessManager();

        Board board = chessManager.getBoard();

        for(int i=0; i<8; i++){
            System.out.println(board.getLine(i));
        }
    }
}
