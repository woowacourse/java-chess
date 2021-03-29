package chess.db.domain.board;

import chess.beforedb.domain.piece.type.PieceWithColorType;
import chess.beforedb.domain.position.type.File;
import chess.beforedb.domain.position.type.Rank;
import chess.db.dao.GamePiecePosition;
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
import java.util.Map;

public class PiecesPositionsForDB {
    private static final String NOT_EXISTS = ".";
    private final PlayerPiecePositionDAO playerPiecePositionDAO;
    private final ScoreCalculator scoreCalculator;


    public PiecesPositionsForDB() {
        playerPiecePositionDAO = new PlayerPiecePositionDAO();
        scoreCalculator = new ScoreCalculator();
    }

    public void save(Long playerId, PiecePosition piecePosition) throws SQLException {
        playerPiecePositionDAO.save(playerId, piecePosition);
    }

    public void save(PlayerEntity playerEntity, PiecePosition piecePositionEntities)
        throws SQLException {

        playerPiecePositionDAO.save(
            new PlayerPiecePosition(playerEntity, piecePositionEntities)
        );
    }

    public Map<PositionEntity, CellForDB> setCellsStatusByGameId(Long gameId,
        Map<PositionEntity, CellForDB> cells) throws SQLException {

        List<Rank> reversedRanks = Rank.reversedRanks();
        for (Rank rank : reversedRanks) {
            getCellsStatusByGameIdAndRank(gameId, rank, cells);
        }
        return cells;
    }

    private void getCellsStatusByGameIdAndRank(Long gameId, Rank rank,
        Map<PositionEntity, CellForDB> cells) throws SQLException {
        for (File file : File.values()) {
            PositionEntity position = PositionEntity.of(file, rank);
            PieceWithColorType pieceWithColorType = playerPiecePositionDAO
                .findPieceWithColorTypeByChessGameIdAndFileAndRank(gameId, position.getId());
            PieceEntity piece = PieceEntity.of(pieceWithColorType);
            PiecePosition piecePosition = new PiecePosition(piece, position);
            putCellStatus(cells, piecePosition);
        }
    }

    private void putCellStatus(Map<PositionEntity, CellForDB> cells, PiecePosition piecePosition) {
        CellForDB cell = new CellForDB();
        cell.put(piecePosition.getPieceEntity());
        cells.put(piecePosition.getPositionEntity(), cell);
    }


    public List<String> getCellsStatusByGameIdInOrderAsString(Long gameId) throws SQLException {
        List<String> cellsStatus = new ArrayList<>();
        List<Rank> reversedRanks = Rank.reversedRanks();
        for (Rank rank : reversedRanks) {
            getCellsStatusByGameIdAndRankInOrderAsString(gameId, rank, cellsStatus);
        }
        return cellsStatus;
    }

    private void getCellsStatusByGameIdAndRankInOrderAsString(Long gameId, Rank rank,
        List<String> cellsStatus) throws SQLException {
        for (File file : File.values()) {
            PositionEntity position = PositionEntity.of(file, rank);
            PieceWithColorType pieceWithColorType = playerPiecePositionDAO
                .findPieceWithColorTypeByChessGameIdAndFileAndRank(gameId, position.getId());
            addCellStatusAsString(cellsStatus, pieceWithColorType);
        }
    }

    private void addCellStatusAsString(List<String> cellsStatus,
        PieceWithColorType pieceWithColorType) {

        String pieceName = NOT_EXISTS;
        if (pieceWithColorType != null) {
            pieceName = pieceWithColorType.getName();
        }
        cellsStatus.add(pieceName);
    }

    public void getScoreOfPlayer(PlayerEntity playerEntity) throws SQLException {
        List<PiecePositionFromDB> allPiecesPositionsFromDBOfPlayer
            = playerPiecePositionDAO.findAllByPlayer(playerEntity);
        // scoreCalculator.
    }

    public List<PiecePositionFromDB> getAllPiecesPositionsOfPlayer(Long playerId)
        throws SQLException {

        return playerPiecePositionDAO.findAllByPlayerId(playerId);
    }

    public List<PlayerPiecePosition> getAllPiecesPositionsOfPlayer(PlayerEntity playerEntity)
        throws SQLException {

        List<PiecePositionFromDB> piecesPositionsFromDB
            = playerPiecePositionDAO.findAllByPlayer(playerEntity);

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

        playerPiecePositionDAO.updatePiecePosition(
            new PlayerPiecePosition(playerEntity, piecePositionEntities)
        );
    }

    public void removePiece(PlayerPiecePosition playerPiecePositionEntity) throws SQLException {
        playerPiecePositionDAO.removePiece(playerPiecePositionEntity);
    }

    public void removePiecesPositionsOfPlayer(PlayerEntity playerEntity) throws SQLException {
        playerPiecePositionDAO.removeAllByPlayer(playerEntity);
    }

    public PieceEntity getPieceByGameIdAndPosition(Long gameId, PositionEntity position)
        throws SQLException {

        PieceWithColorType pieceWithColorType = playerPiecePositionDAO
            .findPieceWithColorTypeByChessGameIdAndFileAndRank(gameId, position.getId());
        return PieceEntity.of(pieceWithColorType);
    }

    public GamePiecePosition getGamePiecePositionByGameIdAndPosition(Long gameId,
        PositionEntity startPosition) throws SQLException {

        return playerPiecePositionDAO
            .findGamePiecePositionByGameIdAndPositionId(gameId, startPosition.getId());
    }

    public void removePieceOfGame(GamePiecePosition gamePiecePosition) throws SQLException {
        playerPiecePositionDAO.removePiecePositionOfGame(gamePiecePosition);
    }

    public void updatePiecePosition(GamePiecePosition gamePiecePosition) throws SQLException {
        playerPiecePositionDAO.updatePiecePosition(gamePiecePosition);
    }
}
