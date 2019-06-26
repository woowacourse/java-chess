package chess.service;

import chess.model.AbstractChessPiece;
import chess.model.ChessBoard;
import chess.model.Point;
import chess.persistance.ChessPieceDAO;
import chess.persistance.GameDAO;

import java.util.Map;

public class BoardService {
    private static final BoardService INSTANCE = new BoardService();

    private BoardService() {
    }

    public static BoardService getInstance() {
        return INSTANCE;
    }

    public BoardDTO getBoardDTO(final String gameId) {
        ChessPieceDAO chessPieceDAO = ChessPieceDAO.getInstance();
        Map<Point, AbstractChessPiece> board = chessPieceDAO.getAll(Integer.valueOf(gameId));
        return new BoardDTO(board);
    }

    public void initializeBoard(final String gameName) {
        GameDAO gameDAO = GameDAO.getInstance();
        int gameId = gameDAO.getGameId(gameName);
        ChessPieceDAO chessPieceDAO = ChessPieceDAO.getInstance();
        chessPieceDAO.addPieces((new ChessBoard()).getBoard(), gameId);
    }

    public ChessBoard getBoard(final String gameId) {
        ChessPieceDAO chessPieceDAO = ChessPieceDAO.getInstance();
        Map<Point, AbstractChessPiece> board = chessPieceDAO.getAll(Integer.valueOf(gameId));
        return new ChessBoard(board);
    }
}
