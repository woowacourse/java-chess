package chess.db.service;

import chess.db.domain.game.ChessGameForDBEx;
import java.sql.SQLException;

public class ChessServiceForDB {
    private final ChessGameForDBEx chessGameForDB;

    public ChessServiceForDB() {
        chessGameForDB = new ChessGameForDBEx();
    }

    public void createChessGame(String title) throws SQLException {
        chessGameForDB.create(title);
    }
}
