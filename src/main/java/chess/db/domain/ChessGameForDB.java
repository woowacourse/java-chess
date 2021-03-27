package chess.db.domain;

import chess.db.dao.ChessGameDAO;
import chess.db.entity.ChessGameEntity;
import java.sql.SQLException;

public class ChessGameForDB {
    private final ChessGameDAO chessGameDAO;
    private final PlayersForDB playersForDB;

    public ChessGameForDB() {
        chessGameDAO = new ChessGameDAO();
        playersForDB = new PlayersForDB();
    }

    public void create(String title) throws SQLException {
        ChessGameEntity chessGameEntity = new ChessGameEntity(title);
        playersForDB.setInitialPlayers(chessGameEntity);
    }
}
