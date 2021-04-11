package chess;

import chess.controller.WebChessController;
import chess.dao.GameDAOImpl;
import chess.dao.RoomDAOImpl;
import chess.repository.ChessRepositoryImpl;
import chess.service.ChessGameService;
import spark.Spark;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFiles.location("/static");
        ChessGameService chessGameService = new ChessGameService(new ChessRepositoryImpl(new GameDAOImpl(), new RoomDAOImpl()));
        WebChessController webChessController = new WebChessController(chessGameService);
        webChessController.run();
    }
}
