package chess;

import static spark.Spark.staticFileLocation;

import chess.controller.ChessController;
import chess.dao.BoardDaoImpl;
import chess.dao.DataSourceImpl;
import chess.dao.TurnDaoImpl;
import chess.service.ChessService;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
        DataSourceImpl dataSource = new DataSourceImpl();
        ChessService chessService = new ChessService(new BoardDaoImpl(dataSource), new TurnDaoImpl(dataSource));
        ChessController chessController = new ChessController(chessService);
        chessController.run();
    }
}
