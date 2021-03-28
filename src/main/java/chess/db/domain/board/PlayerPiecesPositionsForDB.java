package chess.db.domain.board;

import chess.db.dao.PiecePositionEntities;
import chess.db.dao.PlayerPiecePositionDAO;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PlayerPiecePositionEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerPiecesPositionsForDB {
    private final PlayerPiecePositionDAO playerPiecePositionDAO;

    public PlayerPiecesPositionsForDB() {
        playerPiecePositionDAO = new PlayerPiecePositionDAO();
    }

    public void save(PlayerEntity playerEntity, PiecePositionEntities piecePositionEntities)
        throws SQLException {

        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, piecePositionEntities));
    }

    public void update(PlayerEntity playerEntity, PiecePositionEntities piecePositionEntities)
        throws SQLException {

        playerPiecePositionDAO.updatePieceAndPosition(
            new PlayerPiecePositionEntity(playerEntity, piecePositionEntities));
    }

    public void remove(PlayerPiecePositionEntity playerPiecePositionEntity) throws SQLException {
        playerPiecePositionDAO.remove(playerPiecePositionEntity);
    }

    public List<PlayerPiecePositionEntity> getAllPiecesPositionsOfPlayer(PlayerEntity playerEntity)
        throws SQLException {

        List<PiecePositionFromDB> piecesPositionsFromDB
            = playerPiecePositionDAO.findAllByPlayer(playerEntity);

        return getParsedPlayerPiecePositionEntities(playerEntity, piecesPositionsFromDB);
    }

    private List<PlayerPiecePositionEntity> getParsedPlayerPiecePositionEntities(
        PlayerEntity playerEntity, List<PiecePositionFromDB> piecesPositionsFromDB) {

        List<PlayerPiecePositionEntity> playerPiecePositionEntities = new ArrayList<>();
        for (PiecePositionFromDB piecePositionFromDB : piecesPositionsFromDB) {
            PiecePositionEntities piecePositionEntities =
                getParsedPiecePositionEntities(piecePositionFromDB);
            playerPiecePositionEntities.add(
                new PlayerPiecePositionEntity(playerEntity, piecePositionEntities));
        }
        return playerPiecePositionEntities;
    }

    private PiecePositionEntities getParsedPiecePositionEntities(
        PiecePositionFromDB piecePositionFromDB) {

        PieceEntity pieceEntity = PieceEntity.of(
            piecePositionFromDB.getPieceType(), piecePositionFromDB.getTeamColor());

        PositionEntity positionEntity = PositionEntity.of(
            piecePositionFromDB.getFile(), piecePositionFromDB.getRank());

        return new PiecePositionEntities(pieceEntity, positionEntity);
    }
}
