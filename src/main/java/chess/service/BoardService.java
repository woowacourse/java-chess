package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardDAO;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardService {
    public static Map<String, Object> parseEmptyBoard() {
        Board board = BoardFactory.createBoard();
        return createBoardModel(board);
    }

    public static Map<String, Object> parseInitializedBoard(final Board board, final BoardDAO boardDAO) throws SQLException {
        boardDAO.deletePieces();
        for (Position position : board.getBoard().keySet()) {
            boardDAO.placePiece(board, position);
        }
        Map<String, Object> model = createBoardModel(board);
        model.put("turn", board.getCurrentTurn() + "가 먼저 시작합니다.");
        return model;
    }

    public static Board receiveLoadedBoard(final BoardDAO boardDAO) throws SQLException {
        return new Board(boardDAO.findAllPieces());
    }

    public static Map<String, Object> parseLoadedBoard(final BoardDAO boardDAO) throws SQLException {
        return createBoardModel(receiveLoadedBoard(boardDAO));
    }

    public static Map<String, Object> receiveMovedBoard(final Board board, final BoardDAO boardDAO) throws SQLException {
        Map<String, Object> model = createBoardModel(board);
        model.put("turn", board.getCurrentTurn() + "의 차례입니다.");

        boardDAO.deletePieces();
        for (Position position : board.getBoard().keySet()) {
            boardDAO.placePiece(board, position);
        }
        return model;
    }

    public static Map<String, Object> createBoardModel(final Board board) {
        Map<String, Object> model = new HashMap<>();
        Map<Position, Piece> rawBoard = board.getBoard();
        for (Position position : rawBoard.keySet()) {
            model.put(position.toString(), rawBoard.get(position).getName());
        }
        return model;
    }
}
