package chess.db.domain.game;

import chess.db.dao.ChessGameDAO;
import chess.db.domain.player.PlayersForDB;
import chess.db.entity.ChessGameEntity;
import java.sql.SQLException;

public class ChessGameForDBEx {
    private final ChessGameDAO chessGameDAO;
    private final PlayersForDB playersForDB;

    public ChessGameForDBEx() {
        chessGameDAO = new ChessGameDAO();
        playersForDB = new PlayersForDB();
    }

    public void create(String title) throws SQLException {
        ChessGameEntity chessGameEntity = new ChessGameEntity(title);
    }
}
