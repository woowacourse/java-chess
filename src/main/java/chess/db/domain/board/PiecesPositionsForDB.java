package chess.db.domain.board;

import chess.beforedb.domain.piece.type.PieceWithColorType;
import chess.beforedb.domain.position.type.File;
import chess.beforedb.domain.position.type.Rank;
import chess.db.dao.GamePiecePosition;
import chess.db.dao.PlayerPiecePositionDAO;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PiecesPositionsForDB {
    private static final String NOT_EXISTS = ".";
    private final PlayerPiecePositionDAO playerPiecePositionDAO;

    public PiecesPositionsForDB() {
        playerPiecePositionDAO = new PlayerPiecePositionDAO();
    }

    public void save(Long playerId, PiecePositionNew piecePositionNew) throws SQLException {
        playerPiecePositionDAO.save(playerId, piecePositionNew);
    }

    public Map<PositionEntity, CellForDB> getAllCellsStatusByGameId(Long gameId)
        throws SQLException {

        Map<PositionEntity, PieceEntity> existsPieces
            = playerPiecePositionDAO.findAllByGameId(gameId);
        Map<PositionEntity, CellForDB> allCells = new HashMap<>();
        List<Rank> reversedRanks = Rank.reversedRanks();
        for (Rank rank : reversedRanks) {
            allCells.putAll(getCellsStatusByGameIdAndRank(rank, existsPieces));
        }
        return allCells;
    }

    private Map<PositionEntity, CellForDB> getCellsStatusByGameIdAndRank(Rank rank,
        Map<PositionEntity, PieceEntity> existsPieces) {

        Map<PositionEntity, CellForDB> cells = new HashMap<>();
        for (File file : File.values()) {
            PositionEntity position = PositionEntity.of(file, rank);
            CellForDB cell = new CellForDB(existsPieces.get(position));
            cells.put(position, cell);
        }
        return cells;
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

    public void removeAllPiecesPositionsByPlayerId(Long playerId) throws SQLException {
        playerPiecePositionDAO.removeAllByPlayer(playerId);
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

    public List<PiecePositionFromDB> getAllPiecesPositionsOfPlayer(Long playerId)
        throws SQLException {

        return playerPiecePositionDAO.findAllByPlayerId(playerId);
    }
}
