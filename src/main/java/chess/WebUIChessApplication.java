package chess;

import chess.controller.WebChessController;
import chess.dao.PieceDao;
import chess.dao.PieceDaoFactory;
import chess.domain.piece.service.PieceService;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        PieceDaoFactory pieceDaoFactory = new PieceDaoFactory();
        PieceDao pieceDao = pieceDaoFactory.createPieceDao();
        PieceService pieceService = new PieceService(pieceDao);
        WebChessController webChessController = new WebChessController(pieceService);
        webChessController.route();
    }
}
