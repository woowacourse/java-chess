package chess;

import chess.controller.ChessController;
import chess.domain.PlayChessGameService;
import chess.domain.LoadChessGameService;
import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;

public class Application {

    public static void main(String[] args) {
        BoardDao boardDao = new BoardDao();
        PieceDao pieceDao = new PieceDao();

        LoadChessGameService loadChessGameService = new LoadChessGameService(boardDao, pieceDao);
        PlayChessGameService playChessGameService = new PlayChessGameService(boardDao, pieceDao);
        ChessController controller = new ChessController(playChessGameService, loadChessGameService);
        controller.run();
    }
}
