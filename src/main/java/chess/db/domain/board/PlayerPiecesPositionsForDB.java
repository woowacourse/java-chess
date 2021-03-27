package chess.db.domain.board;

import chess.db.dao.PlayerPiecePositionDAO;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PlayerPiecePositionEntity;
import java.sql.SQLException;

public class PlayerPiecesPositionsForDB {
    private final PlayerPiecePositionDAO playerPiecePositionDAO;

    public PlayerPiecesPositionsForDB() {
        playerPiecePositionDAO = new PlayerPiecePositionDAO();
    }

    public void save(PlayerEntity playerEntity, PieceEntity pieceEntity,
        PositionEntity positionEntity) throws SQLException {

        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, pieceEntity, positionEntity));
    }

    public void update(PlayerEntity playerEntity, PieceEntity pieceEntity,
        PositionEntity positionEntity) throws SQLException {

        playerPiecePositionDAO.updatePieceAndPosition(
            new PlayerPiecePositionEntity(playerEntity, pieceEntity, positionEntity));
    }

    public void remove(PlayerPiecePositionEntity playerPiecePositionEntity) throws SQLException {
        playerPiecePositionDAO.remove(playerPiecePositionEntity);
    }
}
