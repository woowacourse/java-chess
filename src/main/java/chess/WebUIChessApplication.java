package chess;

import chess.controller.WebChessController;
import chess.dao.PieceDao;
import chess.dao.PieceDaoFactory;
import chess.domain.board.service.BoardService;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");
        BoardService boardService = new BoardService();
        WebChessController webChessController = new WebChessController(boardService);

        PieceDaoFactory pieceDaoFactory = new PieceDaoFactory();
        PieceDao pieceDao = pieceDaoFactory.createPieceDao();
        webChessController.route(pieceDao);
    }
}
