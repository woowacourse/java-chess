package chess;

import chess.controller.ChessController;
import chess.service.BoardService;
import chess.service.ChessGameService;

public class Application {

    public static void main(String[] args) {
        ChessGameService chessGameService = new ChessGameService();
        BoardService boardService = new BoardService();
        ChessController chessController = new ChessController(chessGameService, boardService);
        chessController.start();
    }
}
