package chess.service;

import chess.dao.BoardDAO;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.move.Coordinate;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.generator.JSONGenerator;
import spark.QueryParamsMap;

import java.sql.SQLException;

public class GameService {
    private static BoardDAO boardDAO;

    public GameService(){
        boardDAO = new BoardDAO();
    }

    public static String newGame() throws SQLException {
        Board board = BoardFactory.createBoard();

        boardDAO.initialize();
        boardDAO.updateDB(board);
        return JSONGenerator.generateJSON(board);
    }

    public static String move(MovingInfo movingInfo) throws SQLException {
        Board board = boardDAO.loadBoard();

        try {
            board.move(movingInfo);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException error) {
            return error.getMessage();
        }
        boardDAO.updateDB(board);
        return JSONGenerator.generateJSON(board);
    }

    public static String continueGame() throws SQLException {
        Board board = boardDAO.loadBoard();

        return JSONGenerator.generateJSON(board);
    }
}
