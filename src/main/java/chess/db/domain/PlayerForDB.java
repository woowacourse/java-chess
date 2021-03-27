package chess.db.domain;

import chess.db.dao.PlayerDAO;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PiecePositionEntity;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import java.util.List;

public class PlayerForDB {
    private final PlayerDAO playerDAO;
    private final PiecesPositionsForDB piecesPositionsForDB;

    public PlayerForDB() {
        playerDAO = new PlayerDAO();
        piecesPositionsForDB = new PiecesPositionsForDB();
    }

    public PlayerEntity createPlayer(TeamColor teamColor) throws SQLException {
        PlayerEntity playerEntity = new PlayerEntity(teamColor);
        setInitialPieces(playerEntity);
        return playerDAO.save(playerEntity);
    }

    private void setInitialPieces(PlayerEntity playerEntity) {
        List<PiecePositionEntity> initialPiecesPositionsByColor
            = piecesPositionsForDB.getInitialPiecesPositionsByColor(playerEntity.getTeamColor());
        playerEntity.setPieceEntities(initialPiecesPositionsByColor);
    }
}
