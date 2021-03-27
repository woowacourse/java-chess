package chess.db.service;

import chess.db.domain.ChessGameForDB;
import java.sql.SQLException;

public class ChessServiceForDB {
    private final ChessGameForDB chessGameForDB;

    public ChessServiceForDB() {
        chessGameForDB = new ChessGameForDB();
    }

    public void createChessGame(String title) throws SQLException {
        chessGameForDB.create(title);
    }
}
