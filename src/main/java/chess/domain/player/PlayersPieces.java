package chess.domain.player;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.dao.entity.GamePiecePositionEntity;
import chess.dao.entity.PiecePositionEntity;
import chess.domain.board.Cell;
import chess.domain.board.setting.BoardSetting;
import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceWithColorType;
import chess.domain.player.score.Scores;
import chess.domain.position.PiecePosition;
import chess.domain.position.PiecesPositions;
import chess.domain.position.Position;
import chess.domain.position.cache.PositionsCache;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PlayersPieces {
    private final Players players;
    private final PiecesPositions piecesPositions;

    public PlayersPieces() {
        this.players = new Players();
        this.piecesPositions = new PiecesPositions();
    }

    public void createAndSaveNewPlayers(Long gameId) throws SQLException {
        players.createAndSaveNewPlayers(gameId);
    }

    public void saveInitialPieces(BoardSetting boardSetting, Long gameId) throws SQLException {
        PlayersIds playersIds = getPlayersIdOfGame(gameId);
        List<PieceWithColorType> piecesSetting = boardSetting.getPiecesSetting();
        for (int index = 0; index < piecesSetting.size(); index++) {
            PieceWithColorType pieceWithColorType = piecesSetting.get(index);
            Piece piece = Piece.of(pieceWithColorType);
            Position position = PositionsCache.get(index);
            PiecePosition piecePosition = new PiecePosition(piece, position);
            savePieceIfExists(playersIds, piecePosition);
        }
    }

    private PlayersIds getPlayersIdOfGame(Long gameId) throws SQLException {
        Long whitePlayerId = players.getPlayerIdByGameIdAndTeamColor(gameId, WHITE);
        Long blackPlayerId = players.getPlayerIdByGameIdAndTeamColor(gameId, BLACK);
        return new PlayersIds(whitePlayerId, blackPlayerId);
    }

    private void savePieceIfExists(PlayersIds playersIds, PiecePosition piecePosition) throws SQLException {
        if (piecePosition.isPieceExists()) {
            savePiecePositionByPlayerTeamColor(playersIds, piecePosition);
        }
    }

    private void savePiecePositionByPlayerTeamColor(PlayersIds playersIds, PiecePosition piecePosition) throws SQLException {
        if (piecePosition.getTeamColor() == WHITE) {
            piecesPositions.save(playersIds.getWhitePlayerId(), piecePosition);
            return;
        }
        piecesPositions.save(playersIds.getBlackPlayerId(), piecePosition);
    }

    public Map<Position, Cell> getAllCellsByGameId(Long gameId) throws SQLException {
        return piecesPositions.getAllCellsStatusByGameId(gameId);
    }

    public GamePiecePositionEntity getGamePiecePositionByGameIdAndPosition(Long gameId, Position position) throws SQLException {
        return piecesPositions.getGamePiecePositionByGameIdAndPosition(gameId, position);
    }

    public void removePieceOfGame(GamePiecePositionEntity gamePiecePositionEntity) throws SQLException {
        piecesPositions.removePieceOfGame(gamePiecePositionEntity);
    }

    public Scores getScores(Long gameId) throws SQLException {
        Long whitePlayerId = players.getPlayerIdByGameIdAndTeamColor(gameId, WHITE);
        Long blackPlayerId = players.getPlayerIdByGameIdAndTeamColor(gameId, BLACK);
        List<PiecePositionEntity> whitePiecesPositions = piecesPositions.getAllPiecesPositionsOfPlayer(whitePlayerId);
        List<PiecePositionEntity> blackPiecesPositions = piecesPositions.getAllPiecesPositionsOfPlayer(blackPlayerId);
        double whitePlayerScore = players.getCalculatedScore(whitePiecesPositions);
        double blackPlayerScore = players.getCalculatedScore(blackPiecesPositions);
        return new Scores(whitePlayerScore, blackPlayerScore);
    }

    public void updatePiecePosition(GamePiecePositionEntity gamePiecePositionEntity) throws SQLException {
        piecesPositions.updatePiecePosition(gamePiecePositionEntity);
    }

    public void removeAllPlayersAndPiecesPositions(Long gameId) throws SQLException {
        Long whitePlayerId = players.getPlayerIdByGameIdAndTeamColor(gameId, WHITE);
        Long blackPlayerId = players.getPlayerIdByGameIdAndTeamColor(gameId, BLACK);
        piecesPositions.removeAllPiecesPositionsByPlayerId(whitePlayerId);
        piecesPositions.removeAllPiecesPositionsByPlayerId(blackPlayerId);
        players.removeAllByChessGame(gameId);
    }

    public List<String> getCellsStatusByGameIdInOrderAsString(Long gameId) throws SQLException {
        return piecesPositions.getCellsStatusByGameIdInOrderAsString(gameId);
    }
}
