package chess.service;

import chess.model.ChessGame;
import chess.model.boardcreatestrategy.NewGameCreateStrategy;
import chess.model.dao.ChessDAO;
import chess.model.dto.BoardDTO;

import java.sql.SQLException;
import java.util.List;

public class GameService {
    private ChessDAO chessDAO;

    public GameService(ChessDAO chessDAO) {
        this.chessDAO = chessDAO;
    }

    public void deleteAll() throws SQLException {
        chessDAO.deleteAll();
    }

    public void insertInit() throws SQLException {
        chessDAO.insertInit();
    }

    public List<String> findByTurn(int turn) throws SQLException {
        return chessDAO.selectByTurn(turn).getPieces();

    }

    public ChessGame initGame(int turn) {
        return new ChessGame(new NewGameCreateStrategy(), turn);
    }
}
