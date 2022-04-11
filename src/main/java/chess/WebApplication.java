package chess;

import static spark.Spark.staticFileLocation;

import chess.controller.ChessController;
import chess.dao.BoardDao;
import chess.dao.BoardDaoImpl;
import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.service.ChessService;
import chess.utils.DatabaseUtil;
import java.sql.Connection;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");

        Connection connection = DatabaseUtil.getConnection();
        PieceDao pieceDao = new PieceDaoImpl(connection);
        BoardDao boardDao = new BoardDaoImpl(connection);

        ChessController chessController = new ChessController();
        ChessService chessService = new ChessService(pieceDao, boardDao);
        chessController.run(chessService);
    }
}
