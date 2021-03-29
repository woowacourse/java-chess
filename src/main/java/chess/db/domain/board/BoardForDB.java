package chess.db.domain.board;

import static chess.beforedb.domain.piece.type.PieceType.KING;
import static chess.beforedb.domain.player.type.TeamColor.*;

import chess.beforedb.domain.player.type.TeamColor;
import chess.db.dao.GamePiecePosition;
import chess.db.domain.game.ScoresEntity;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.player.PlayersForDB;
import chess.db.domain.position.MoveRequestForDB;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.PlayerEntity;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardForDB {
    private static final int NUMBER_OF_ALL_KINGS = 2;

    private final PiecesPositionsForDB piecesPositionsForDB;
    private final PlayersForDB playersForDB;
    private final MoveChecker moveChecker;
    private final Map<PositionEntity, CellForDB> cellsForDB = new HashMap<>();

    public BoardForDB() {
        piecesPositionsForDB = new PiecesPositionsForDB();
        playersForDB = new PlayersForDB();
        moveChecker = new MoveChecker();
    }

    public void validateRoute(Long gameId, MoveRequestForDB moveRequestForDB) throws SQLException {
        Map<PositionEntity, CellForDB> cells = new HashMap<>();
        piecesPositionsForDB.setCellsStatusByGameId(gameId, cells);

        CellForDB startPositionCell = cells.get(moveRequestForDB.getStartPosition());

        validateOwnPiece(startPositionCell, moveRequestForDB.getCurrentTurnTeamColor());
        validateMoveRoute(cells, moveRequestForDB);
    }

    private void validateOwnPiece(CellForDB startPositionCell, TeamColor teamColor) {
        if (startPositionCell.isEmpty()) {
            throw new IllegalArgumentException("출발 위치에 기물이 존재하지 않습니다.");
        }
        if (startPositionCell.getTeamColor() != teamColor) {
            throw new IllegalArgumentException("자신의 기물이 아닙니다.");
        }
    }

    private void validateMoveRoute(Map<PositionEntity, CellForDB> cells,
        MoveRequestForDB moveRequestForDB) {

        if (!moveChecker.canMove(moveRequestForDB, cells)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
    }

    public void move(Long gameId, MoveRequestForDB moveRequestForDB) throws SQLException {

        // 현재 게임 id의 출발 위치에 있는 플레이어_기물_위치 Entity를 가져온다.
        GamePiecePosition startPositionPiece = piecesPositionsForDB
            .getGamePiecePositionByGameIdAndPosition(gameId, moveRequestForDB.getStartPosition());

        // 현재 게임 id의 도착 위치에 있는 플레이어_기물_위치 Entity를 가져온다. (null 또는 존재)
        GamePiecePosition destinationPiece = piecesPositionsForDB
            .getGamePiecePositionByGameIdAndPosition(gameId, moveRequestForDB.getStartPosition());

        // 가져온 현재 게임 id의 도착 위치에 있는 플레이어_기물_위치 Entity가 null이 아니면, 삭제한다. (잡혔으므로)
        if (destinationPiece != null) {
            piecesPositionsForDB.removePieceOfGame(destinationPiece);
            // 백 팀 플레이어, 흑 팀 플레이어 점수를 계산해서 디비에 업데이트한다.
            playersForDB.calculateAndUpdateScoresOfGame(gameId);
        }
        // 현재 게임 id의 출발 위치에 있는 플레이어_기물_위치 Entity의 위치 id를 도착 위치의 id로 업데이트 한다.
        startPositionPiece.setPositionId(moveRequestForDB.getDestinationId());
        piecesPositionsForDB.updatePiecePosition(startPositionPiece);
    }

    public ScoresEntity getScores(Long gameId) throws SQLException {
        return playersForDB.getPlayersScores(gameId);
    }

    public List<String> getStatus(Long gameId) throws SQLException {
        return piecesPositionsForDB.getCellsStatusByGameIdInOrderAsString(gameId);
    }

    public CellForDB findCell(PositionEntity positionEntity) {
        return cellsForDB.get(positionEntity);
    }

    public PieceEntity findPiece(PositionEntity positionEntity) {
        return findCell(positionEntity).getPieceEntity();
    }

    public boolean isKingDead() {
        return cellsForDB.values().stream()
            .filter(cell -> !cell.isEmpty() && cell.getPieceType() == KING)
            .count() < NUMBER_OF_ALL_KINGS;
    }

    public void removeAllPlayersAndPiecesPositionsOfGame(Long gameId) throws SQLException {
        playersForDB.removeAllPlayersAndPiecesPositions(gameId);
    }
}

