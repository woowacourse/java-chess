package chess;

import chess.controller.ChessController;
import chess.domain.ChessGameService;
import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;

public class Application {

    public static void main(String[] args) {
        BoardDao boardDao = new BoardDao();
        PieceDao pieceDao = new PieceDao();

        ChessGameService chessGameService = new ChessGameService(boardDao, pieceDao);
        ChessController controller = new ChessController(chessGameService);
        controller.run();
    }
}
