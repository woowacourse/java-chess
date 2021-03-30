package chess.domain.board;

import static chess.domain.piece.type.PieceType.KING;
import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.controller.dto.response.BoardStatusResponseDTOForDB;
import chess.dao.entity.GamePiecePositionEntity;
import chess.domain.board.move.MoveChecker;
import chess.domain.board.move.MoveRequest;
import chess.domain.board.setting.BoardSetting;
import chess.domain.player.PlayersPieces;
import chess.domain.player.score.Scores;
import chess.domain.player.type.TeamColor;
import chess.domain.position.Position;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Board {
    private static final int NUMBER_OF_ALL_KINGS = 2;

    private final PlayersPieces playersPieces;
    private final MoveChecker moveChecker;

    public Board() {
        playersPieces = new PlayersPieces();
        moveChecker = new MoveChecker();
    }

    public void createAndSaveNewPlayersAndPiecesPositionsOfGame(Long gameId,
        BoardSetting boardSetting) throws SQLException {

        playersPieces.createAndSaveNewPlayers(gameId);
        playersPieces.saveInitialPieces(boardSetting, gameId);
    }


    public void validateRoute(Long gameId, MoveRequest moveRequest) throws SQLException {
        Map<Position, Cell> cells = playersPieces.getAllCellsByGameId(gameId);
        Cell startPositionCell = cells.get(moveRequest.getStartPosition());
        validateOwnPiece(startPositionCell, moveRequest.getCurrentTurnTeamColor());
        validateMoveRoute(cells, moveRequest);
    }

    private void validateOwnPiece(Cell startPositionCell, TeamColor teamColor) {
        if (startPositionCell.isEmpty()) {
            throw new IllegalArgumentException("출발 위치에 기물이 존재하지 않습니다.");
        }
        if (startPositionCell.getTeamColor() != teamColor) {
            throw new IllegalArgumentException("자신의 기물이 아닙니다.");
        }
    }

    private void validateMoveRoute(Map<Position, Cell> cells,
        MoveRequest moveRequest) {

        if (!moveChecker.canMove(moveRequest, cells)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
    }

    public void move(Long gameId, MoveRequest moveRequest) throws SQLException {
        GamePiecePositionEntity startPositionPiece = playersPieces
            .getGamePiecePositionByGameIdAndPosition(gameId, moveRequest.getStartPosition());
        GamePiecePositionEntity destinationPiece = playersPieces
            .getGamePiecePositionByGameIdAndPosition(gameId, moveRequest.getDestination());
        if (destinationPiece != null) {
            playersPieces.removePieceOfGame(destinationPiece);
        }
        startPositionPiece.setPositionId(moveRequest.getDestinationId());
        playersPieces.updatePiecePosition(startPositionPiece);
    }

    public Scores getScores(Long gameId) throws SQLException {
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

