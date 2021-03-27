package chess.domain.player;

import chess.dao.PlayerDAO;
import chess.dao.entity.PlayerEntity;
import chess.domain.PiecePositionEntity;
import chess.domain.PiecesPositionsForDB;
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
