package chess.domain.position;

import chess.dao.PlayerPiecePositionDAO;
import chess.dao.entity.GamePiecePosition;
import chess.dao.entity.PiecePositionFromDB;
import chess.dao.entity.PositionEntity;
import chess.domain.board.CellForDB;
import chess.domain.piece.PieceEntity;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PiecesPositionsForDB {
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
        Map<PositionEntity, CellForDB> allCells = this.getAllCellsStatusByGameId(gameId);
        List<Rank> reversedRanks = Rank.reversedRanks();
        for (Rank rank : reversedRanks) {
            cellsStatus.addAll(getCellsStatusAsStringByGameIdAndRank(rank, allCells));
        }
        return cellsStatus;
    }

    private List<String> getCellsStatusAsStringByGameIdAndRank(Rank rank,
        Map<PositionEntity, CellForDB> allCells) {

        List<String> cells = new ArrayList<>();
        for (File file : File.values()) {
            PositionEntity position = PositionEntity.of(file, rank);
            CellForDB cell = allCells.get(position);
            cells.add(cell.getStatus());
        }
        return cells;
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
