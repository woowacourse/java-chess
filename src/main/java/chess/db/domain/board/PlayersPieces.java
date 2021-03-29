package chess.db.domain.board;

import static chess.beforedb.domain.player.type.TeamColor.BLACK;
import static chess.beforedb.domain.player.type.TeamColor.WHITE;

import chess.beforedb.domain.board.setting.BoardSetting;
import chess.beforedb.domain.piece.type.PieceWithColorType;
import chess.db.dao.GamePiecePosition;
import chess.db.domain.game.ScoresEntity;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.player.PlayersForDB;
import chess.db.domain.position.PositionEntitiesCache;
import chess.db.domain.position.PositionEntity;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PlayersPieces {
    private final PlayersForDB playersForDB;
    private final PiecesPositionsForDB piecesPositionsForDB;

    public PlayersPieces() {
        this.playersForDB = new PlayersForDB();
        this.piecesPositionsForDB = new PiecesPositionsForDB();
    }

    public void createAndSaveNewPlayers(Long gameId) throws SQLException {
        playersForDB.createAndSaveNewPlayers(gameId);
    }

    public void saveInitialPieces(BoardSetting boardSetting, Long gameId) throws SQLException {
        PlayersIds playersIds = getPlayersIdOfGame(gameId);
        List<PieceWithColorType> piecesSetting = boardSetting.getPiecesSetting();
        for (int index = 0; index < piecesSetting.size(); index++) {
            PieceWithColorType pieceWithColorType = piecesSetting.get(index);
            PieceEntity pieceEntity = PieceEntity.of(pieceWithColorType);
            PositionEntity positionEntity = PositionEntitiesCache.get(index);
            PiecePositionNew piecePositionNew = new PiecePositionNew(pieceEntity, positionEntity);
            savePieceIfExists(playersIds, piecePositionNew);
        }
    }

    private PlayersIds getPlayersIdOfGame(Long gameId) throws SQLException {
        Long whitePlayerId = playersForDB.getPlayerIdByGameIdAndTeamColor(gameId, WHITE);
        Long blackPlayerId = playersForDB.getPlayerIdByGameIdAndTeamColor(gameId, BLACK);
        return new PlayersIds(whitePlayerId, blackPlayerId);
    }

    private void savePieceIfExists(PlayersIds playersIds, PiecePositionNew piecePositionNew)
        throws SQLException {
        if (piecePositionNew.isPieceExists()) {
            if (piecePositionNew.getTeamColor() == WHITE) {
                piecesPositionsForDB.save(playersIds.getWhitePlayerId(), piecePositionNew);
                return;
            }
            piecesPositionsForDB.save(playersIds.getBlackPlayerId(), piecePositionNew);
        }
    }

    public Map<PositionEntity, CellForDB> getAllCellsByGameId(Long gameId) throws SQLException {
        return piecesPositionsForDB.getAllCellsStatusByGameId(gameId);
    }

    public GamePiecePosition getGamePiecePositionByGameIdAndPosition(Long gameId,
        PositionEntity position) throws SQLException {

        return piecesPositionsForDB.getGamePiecePositionByGameIdAndPosition(gameId, position);
    }

    public void removePieceOfGame(GamePiecePosition gamePiecePosition) throws SQLException {
        piecesPositionsForDB.removePieceOfGame(gamePiecePosition);
    }

    public void calculateAndUpdateScoresOfGame(Long gameId) throws SQLException {
        Long whitePlayerId = playersForDB.getPlayerIdByGameIdAndTeamColor(gameId, WHITE);
        Long blackPlayerId = playersForDB.getPlayerIdByGameIdAndTeamColor(gameId, BLACK);
        List<PiecePositionFromDB> whitePiecesPositions = piecesPositionsForDB
            .getAllPiecesPositionsOfPlayer(whitePlayerId);
        List<PiecePositionFromDB> blackPiecesPositions = piecesPositionsForDB
            .getAllPiecesPositionsOfPlayer(blackPlayerId);
        playersForDB.calculateAndUpdateScores(whitePlayerId, whitePiecesPositions);
        playersForDB.calculateAndUpdateScores(blackPlayerId, blackPiecesPositions);
    }

    public void updatePiecePosition(GamePiecePosition gamePiecePosition) throws SQLException {
        piecesPositionsForDB.updatePiecePosition(gamePiecePosition);
    }

    public void removeAllPlayersAndPiecesPositions(Long gameId) throws SQLException {
        Long whitePlayerId = playersForDB.getPlayerIdByGameIdAndTeamColor(gameId, WHITE);
        Long blackPlayerId = playersForDB.getPlayerIdByGameIdAndTeamColor(gameId, BLACK);
        piecesPositionsForDB.removeAllPiecesPositionsByPlayerId(whitePlayerId);
        piecesPositionsForDB.removeAllPiecesPositionsByPlayerId(blackPlayerId);
        playersForDB.removeAllByChessGame(gameId);
    }

    public ScoresEntity getPlayersScores(Long gameId) throws SQLException {
        return playersForDB.getPlayersScores(gameId);
    }

    public List<String> getCellsStatusByGameIdInOrderAsString(Long gameId) throws SQLException {
        return piecesPositionsForDB.getCellsStatusByGameIdInOrderAsString(gameId);
    }
}
