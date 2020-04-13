package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardDAO;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardService {
    public static Map<String, Object> receiveEmptyBoard() {
        Board board = BoardFactory.createBoard();
        return createBoardModel(board);
    }

    public static Map<String, Object> receiveInitializedBoard(final Board board, final BoardDAO boardDAO) throws SQLException {
        board.initialize();
        boardDAO.deletePieces();
        for (Position position : board.getBoard().keySet()) {
            boardDAO.placePiece(board, position);
        }
        return createBoardModel(board);
    }

    public static Map<String, Object> receiveLoadedBoard(final BoardDAO boardDAO) throws SQLException {
        Board board = new Board(boardDAO.findAllPieces());
        return createBoardModel(board);
    }

    private static Map<String, Object> createBoardModel(final Board board) {
        Map<String, Object> model = new HashMap<>();
        for (Position position : board.getBoard().keySet()) {
            model.put(position.toString(), board.getBoard().get(position));
        }
        return model;
    }

    public static Map<String, Object> receiveDeletedBoard(final BoardDAO boardDAO) throws SQLException {
        boardDAO.deletePieces();
        return receiveEmptyBoard();
    }
}
