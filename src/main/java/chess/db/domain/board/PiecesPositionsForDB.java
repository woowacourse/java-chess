package chess.db.domain.board;

import chess.beforedb.domain.piece.type.PieceWithColorType;
import chess.beforedb.domain.position.type.File;
import chess.beforedb.domain.position.type.Rank;
import chess.db.dao.PiecePosition;
import chess.db.dao.PlayerPiecePositionDAO;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.player.ScoreCalculator;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PlayerPiecePosition;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiecesPositionsForDB {
    private final PlayerPiecePositionDAO piecePositionDAO;
    private final ScoreCalculator scoreCalculator;


    public PiecesPositionsForDB() {
        piecePositionDAO = new PlayerPiecePositionDAO();
        scoreCalculator = new ScoreCalculator();
    }

    public void save(PlayerEntity playerEntity, PiecePosition piecePositionEntities)
        throws SQLException {

        piecePositionDAO.save(
            new PlayerPiecePosition(playerEntity, piecePositionEntities)
        );
    }

    public List<String> getCellsStatusGameOfInOrder(Long gameId) throws SQLException {
        List<String> cellsStatus = new ArrayList<>();
        List<Rank> reversedRanks = Rank.reversedRanks();
        for (Rank rank : reversedRanks) {
            getCellsStatusByGameIdAndRank(gameId, rank, cellsStatus);
        }
        return cellsStatus;
    }

    private void getCellsStatusByGameIdAndRank(Long gameId, Rank rank, List<String> cellsStatus)
        throws SQLException {
        for (File file : File.values()) {
            PositionEntity position = PositionEntity.of(file, rank);
            PieceWithColorType pieceWithColorType = piecePositionDAO
                .findByChessGameIdAndFileAndRank(gameId, position.getId());
            addCellStatus(cellsStatus, pieceWithColorType);
        }
    }

    private void addCellStatus(List<String> cellsStatus, PieceWithColorType pieceWithColorType) {
        String pieceName = ".";
        if (pieceWithColorType != null) {
            pieceName = pieceWithColorType.getName();
        }
        cellsStatus.add(pieceName);
    }

    public void getScoreOfPlayer(PlayerEntity playerEntity) throws SQLException {
        List<PiecePositionFromDB> allPiecesPositionsFromDBOfPlayer
            = piecePositionDAO.findAllByPlayer(playerEntity);
        // scoreCalculator.
    }

    public List<PiecePositionFromDB> getAllPiecesPositionsOfPlayer(Long playerId)
        throws SQLException {

        return piecePositionDAO.findAllByPlayerId(playerId);
    }

    public List<PlayerPiecePosition> getAllPiecesPositionsOfPlayer(PlayerEntity playerEntity)
        throws SQLException {

        List<PiecePositionFromDB> piecesPositionsFromDB
            = piecePositionDAO.findAllByPlayer(playerEntity);

        return getParsedPiecePositionEntities(playerEntity, piecesPositionsFromDB);
    }

    private List<PlayerPiecePosition> getParsedPiecePositionEntities(
        PlayerEntity playerEntity, List<PiecePositionFromDB> piecesPositionsFromDB) {

        List<PlayerPiecePosition> playerPiecePositionEntities = new ArrayList<>();
        for (PiecePositionFromDB piecePositionFromDB : piecesPositionsFromDB) {
            PiecePosition piecePositionEntities =
                getParsedPiecePositionEntities(piecePositionFromDB);
            playerPiecePositionEntities.add(
                new PlayerPiecePosition(playerEntity, piecePositionEntities));
        }
        return playerPiecePositionEntities;
    }

    private PiecePosition getParsedPiecePositionEntities(
        PiecePositionFromDB piecePositionFromDB) {

        PieceEntity pieceEntity = PieceEntity.of(
            piecePositionFromDB.getPieceType(), piecePositionFromDB.getTeamColor());

        PositionEntity positionEntity = PositionEntity.of(
            piecePositionFromDB.getFile(), piecePositionFromDB.getRank());

        return new PiecePosition(pieceEntity, positionEntity);
    }

    public void updatePiecePosition(PlayerEntity playerEntity, PiecePosition piecePositionEntities)
        throws SQLException {

        piecePositionDAO.updatePiecePosition(
            new PlayerPiecePosition(playerEntity, piecePositionEntities)
        );
    }

    public void removePiece(PlayerPiecePosition playerPiecePositionEntity) throws SQLException {
        piecePositionDAO.removePiece(playerPiecePositionEntity);
    }

    public void removePiecesPositionsOfPlayer(PlayerEntity playerEntity) throws SQLException {
        piecePositionDAO.removeAllByPlayer(playerEntity);
    }
}
