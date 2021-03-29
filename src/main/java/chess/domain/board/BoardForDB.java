package chess.domain.board;

import static chess.domain.piece.type.PieceType.KING;
import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.controller.dto.response.BoardStatusResponseDTOForDB;
import chess.dao.entity.GamePiecePosition;
import chess.dao.entity.PositionEntity;
import chess.domain.board.move.MoveChecker;
import chess.domain.board.move.MoveRequestForDB;
import chess.domain.board.setting.BoardSetting;
import chess.domain.player.PlayersPieces;
import chess.domain.player.score.ScoresEntity;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BoardForDB {
    private static final int NUMBER_OF_ALL_KINGS = 2;

    private final PlayersPieces playersPieces;
    private final MoveChecker moveChecker;

    public BoardForDB() {
        playersPieces = new PlayersPieces();
        moveChecker = new MoveChecker();
    }

    public void createAndSaveNewPlayersAndPiecesPositionsOfGame(Long gameId,
        BoardSetting boardSetting) throws SQLException {

        playersPieces.createAndSaveNewPlayers(gameId);
        playersPieces.saveInitialPieces(boardSetting, gameId);
    }


    public void validateRoute(Long gameId, MoveRequestForDB moveRequestForDB) throws SQLException {
        Map<PositionEntity, CellForDB> cells = playersPieces.getAllCellsByGameId(gameId);
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
        GamePiecePosition startPositionPiece = playersPieces
            .getGamePiecePositionByGameIdAndPosition(gameId, moveRequestForDB.getStartPosition());
        GamePiecePosition destinationPiece = playersPieces
            .getGamePiecePositionByGameIdAndPosition(gameId, moveRequestForDB.getDestination());
        if (destinationPiece != null) {
            playersPieces.removePieceOfGame(destinationPiece);
        }
        startPositionPiece.setPositionId(moveRequestForDB.getDestinationId());
        playersPieces.updatePiecePosition(startPositionPiece);
    }

    public ScoresEntity getScores(Long gameId) throws SQLException {
        playersPieces.calculateAndUpdateScoresOfGame(gameId);
        return playersPieces.getPlayersScores(gameId);
    }

    public BoardStatusResponseDTOForDB getStatus(Long gameId) throws SQLException {
        List<String> cellsStatus = playersPieces.getCellsStatusByGameIdInOrderAsString(gameId);
        return new BoardStatusResponseDTOForDB(cellsStatus, isKingDead(cellsStatus));
    }

    private boolean isKingDead(List<String> cellsStatus) {
        return cellsStatus.stream()
            .filter(cellStatus ->
                cellStatus.equals(KING.getName(WHITE))
                    || cellStatus.equals(KING.getName(BLACK)))
            .count() < NUMBER_OF_ALL_KINGS;
    }

    public void removeAllPlayersAndPiecesPositionsOfGame(Long gameId) throws SQLException {
        playersPieces.removeAllPlayersAndPiecesPositions(gameId);
    }
}

