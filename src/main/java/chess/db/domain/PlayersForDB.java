package chess.db.domain;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.db.dao.PlayerDAO;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import java.sql.SQLException;

public class PlayersForDB {
    private final PlayerDAO playerDAO;
    private final PlayerPiecesPositionsForDB playerPiecesPositionsForDB;

    public PlayersForDB() {
        playerDAO = new PlayerDAO();
        playerPiecesPositionsForDB = new PlayerPiecesPositionsForDB();
    }

    public void setInitialPlayers(ChessGameEntity chessGameEntity) throws SQLException {
        PlayerEntity whitePlayerEntity = new PlayerEntity(WHITE, chessGameEntity);
        PlayerEntity blackPlayerEntity = new PlayerEntity(BLACK, chessGameEntity);
        setInitialPieces(whitePlayerEntity, blackPlayerEntity);
        chessGameEntity.addPlayer(whitePlayerEntity);
        chessGameEntity.addPlayer(blackPlayerEntity);
        savePlayers(whitePlayerEntity, blackPlayerEntity);
    }

    private void setInitialPieces(PlayerEntity whitePlayerEntity, PlayerEntity blackPlayerEntity)
        throws SQLException {
        playerPiecesPositionsForDB.setInitialPiecesPositionsOfPlayer(whitePlayerEntity);
        playerPiecesPositionsForDB.setInitialPiecesPositionsOfPlayer(blackPlayerEntity);
    }

    private void savePlayers(PlayerEntity whitePlayerEntity, PlayerEntity blackPlayerEntity)
        throws SQLException {
        playerDAO.save(whitePlayerEntity);
        playerDAO.save(blackPlayerEntity);
    }
}
