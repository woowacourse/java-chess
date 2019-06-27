package chess.service;

import chess.model.ChessGame;
import chess.model.boardcreatestrategy.ContinueGameCreateStrategy;
import chess.model.boardcreatestrategy.NewGameCreateStrategy;
import chess.model.dao.ChessDAO;
import chess.model.dto.BoardDTO;

import java.sql.SQLException;
import java.util.Collections;

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

    public BoardDTO findByTurn(int turn) throws SQLException {
        return chessDAO.selectByTurn(turn);
    }

    public ChessGame initGame(int turn) {
        return new ChessGame(new NewGameCreateStrategy(), turn);
    }

    public int getLatestTurn() throws SQLException {
        return chessDAO.getLatestTurn();

    }

    public ChessGame continueGame(BoardDTO boardDTO, int turn) {
        Collections.reverse(boardDTO.getPieces());
        return new ChessGame(new ContinueGameCreateStrategy(boardDTO), turn);
    }
}
