package chess.db.domain.board;

import chess.db.dao.PiecePositionEntities;
import chess.db.dao.PiecePositionDAO;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.player.ScoreCalculator;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PiecePositionEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiecesPositionsForDB {
    private final PiecePositionDAO piecePositionDAO;
    private final ScoreCalculator scoreCalculator;


    public PiecesPositionsForDB() {
        piecePositionDAO = new PiecePositionDAO();
        scoreCalculator = new ScoreCalculator();
    }

    public void save(PlayerEntity playerEntity, PiecePositionEntities piecePositionEntities)
        throws SQLException {

        piecePositionDAO.save(
            new PiecePositionEntity(playerEntity, piecePositionEntities)
        );
    }

    public void getScoreOfPlayer(PlayerEntity playerEntity) throws SQLException {
        List<PiecePositionFromDB> allPiecesPositionsFromDBOfPlayer
            = piecePositionDAO.findAllByPlayer(playerEntity);
        // scoreCalculator.
    }

    public List<PiecePositionEntity> getAllPiecesPositionsOfPlayer(PlayerEntity playerEntity)
        throws SQLException {

        List<PiecePositionFromDB> piecesPositionsFromDB
            = piecePositionDAO.findAllByPlayer(playerEntity);

        return getParsedPiecePositionEntities(playerEntity, piecesPositionsFromDB);
    }

    private List<PiecePositionEntity> getParsedPiecePositionEntities(
        PlayerEntity playerEntity, List<PiecePositionFromDB> piecesPositionsFromDB) {

        List<PiecePositionEntity> playerPiecePositionEntities = new ArrayList<>();
        for (PiecePositionFromDB piecePositionFromDB : piecesPositionsFromDB) {
            PiecePositionEntities piecePositionEntities =
                getParsedPiecePositionEntities(piecePositionFromDB);
            playerPiecePositionEntities.add(
                new PiecePositionEntity(playerEntity, piecePositionEntities));
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

    public void update(PlayerEntity playerEntity, PiecePositionEntities piecePositionEntities)
        throws SQLException {

        piecePositionDAO.updatePieceAndPosition(
            new PiecePositionEntity(playerEntity, piecePositionEntities)
        );
    }

    public void remove(PiecePositionEntity playerPiecePositionEntity) throws SQLException {
        piecePositionDAO.remove(playerPiecePositionEntity);
    }

    public void removePiecesPositionsOfPlayer(PlayerEntity playerEntity) throws SQLException {
        piecePositionDAO.removeAllByPlayer(playerEntity);
    }
}
