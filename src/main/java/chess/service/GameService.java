package chess.service;

import chess.dao.BoardDAO;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.move.MovingInfo;
import chess.generator.JSONGenerator;

import java.sql.SQLException;

public class GameService {
    private static BoardDAO boardDAO;

    public GameService() {
        boardDAO = new BoardDAO();
    }

    public String newGame() throws SQLException {
        Board board = BoardFactory.createBoard();

        boardDAO.initialize();
        boardDAO.updateDB(board);
        return JSONGenerator.generateJSON(board);
    }

    public String move(MovingInfo movingInfo) throws SQLException {
        Board board = boardDAO.loadBoard();

        try {
            board.move(movingInfo);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException error) {
            return error.getMessage();
        }
        boardDAO.updateDB(board);
        return JSONGenerator.generateJSON(board);
    }

    public String continueGame() throws SQLException {
        Board board = boardDAO.loadBoard();

        return JSONGenerator.generateJSON(board);
    }
}
