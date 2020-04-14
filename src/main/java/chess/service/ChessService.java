package chess.service;

import java.sql.SQLException;

import chess.dao.BoardDao;
import chess.domain.Board;

public class ChessService {
    private BoardDao boardDao;

    public void initBoard() throws SQLException {
        boardDao.save(new Board());
    }

    public Board getSavedBoard() throws SQLException {
        return boardDao.find();
    }
}
