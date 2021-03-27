package chess.db.domain;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.db.dao.ChessGameDAO;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import java.sql.SQLException;

public class ChessGameForDB {
    private final ChessGameDAO chessGameDAO;
    private final PlayerForDB playerForDB;

    public ChessGameForDB() {
        chessGameDAO = new ChessGameDAO();
        playerForDB = new PlayerForDB();
    }

    public void create(String title) throws SQLException {
        ChessGameEntity chessGameEntity = new ChessGameEntity(title);
        setPlayers(chessGameEntity);
    }

    private void setPlayers(ChessGameEntity chessGameEntity) throws SQLException {
        PlayerEntity whitePlayerEntity = playerForDB.createPlayer(WHITE);
        PlayerEntity blackPlayerEntity = playerForDB.createPlayer(BLACK);

        chessGameEntity.addPlayer(whitePlayerEntity);
        chessGameEntity.addPlayer(blackPlayerEntity);

        chessGameDAO.save(chessGameEntity);
    }
}
