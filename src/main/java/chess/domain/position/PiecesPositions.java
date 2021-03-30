package chess.domain.position;

import chess.dao.PlayerPiecePositionDAO;
import chess.dao.entity.GamePiecePositionEntity;
import chess.dao.entity.PiecePositionEntity;
import chess.domain.board.Cell;
import chess.domain.piece.Piece;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PiecesPositions {
    private final PlayerPiecePositionDAO playerPiecePositionDAO;

    public PiecesPositions() {
        playerPiecePositionDAO = new PlayerPiecePositionDAO();
    }

    public void save(Long playerId, PiecePosition piecePosition) throws SQLException {
        playerPiecePositionDAO.save(playerId, piecePosition);
    }

    public Map<Position, Cell> getAllCellsStatusByGameId(Long gameId) throws SQLException {
        Map<Position, Piece> existsPieces = playerPiecePositionDAO.findAllByGameId(gameId);
        Map<Position, Cell> allCells = new HashMap<>();
        List<Rank> reversedRanks = Rank.reversedRanks();
        for (Rank rank : reversedRanks) {
            allCells.putAll(getCellsStatusByGameIdAndRank(rank, existsPieces));
        }
        return allCells;
    }

    private Map<Position, Cell> getCellsStatusByGameIdAndRank(Rank rank,
        Map<Position, Piece> existsPieces) {

        Map<Position, Cell> cells = new HashMap<>();
        for (File file : File.values()) {
            Position position = Position.of(file, rank);
            Cell cell = new Cell(existsPieces.get(position));
            cells.put(position, cell);
        }
        return cells;
    }

    public List<String> getCellsStatusByGameIdInOrderAsString(Long gameId) throws SQLException {
        List<String> cellsStatus = new ArrayList<>();
        Map<Position, Cell> allCells = this.getAllCellsStatusByGameId(gameId);
        List<Rank> reversedRanks = Rank.reversedRanks();
        for (Rank rank : reversedRanks) {
            cellsStatus.addAll(getCellsStatusAsStringByGameIdAndRank(rank, allCells));
        }
        return cellsStatus;
    }

    private List<String> getCellsStatusAsStringByGameIdAndRank(Rank rank,
        Map<Position, Cell> allCells) {

        List<String> cells = new ArrayList<>();
        for (File file : File.values()) {
            Position position = Position.of(file, rank);
            Cell cell = allCells.get(position);
            cells.add(cell.getStatus());
        }
        return cells;
    }

    public void removeAllPiecesPositionsByPlayerId(Long playerId) throws SQLException {
        playerPiecePositionDAO.removeAllByPlayer(playerId);
    }

    public GamePiecePositionEntity getGamePiecePositionByGameIdAndPosition(Long gameId,
        Position startPosition) throws SQLException {

        return playerPiecePositionDAO
            .findGamePiecePositionByGameIdAndPositionId(gameId, startPosition.getId());
    }

    public void removePieceOfGame(GamePiecePositionEntity gamePiecePositionEntity)
        throws SQLException {

        playerPiecePositionDAO.removePiecePositionOfGame(gamePiecePositionEntity);
    }

    public void updatePiecePosition(GamePiecePositionEntity gamePiecePositionEntity)
        throws SQLException {

        playerPiecePositionDAO.updatePiecePosition(gamePiecePositionEntity);
    }

    public List<PiecePositionEntity> getAllPiecesPositionsOfPlayer(Long playerId)
        throws SQLException {

        return playerPiecePositionDAO.findAllByPlayerId(playerId);
    }
}
