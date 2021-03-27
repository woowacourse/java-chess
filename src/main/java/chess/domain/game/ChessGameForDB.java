package chess.domain.game;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.dao.ChessGameDAO;
import chess.dao.entity.ChessGameEntity;
import chess.dao.entity.PlayerEntity;
import chess.domain.player.PlayerForDB;
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
