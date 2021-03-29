package chess.domain.player;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.dao.PlayerDAO;
import chess.dao.entity.PiecePositionFromDB;
import chess.domain.player.score.ScoreCalculator;
import chess.domain.player.score.ScoresEntity;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import java.util.List;

public class PlayersForDB {
    private final PlayerDAO playerDAO;
    private final ScoreCalculator scoreCalculator;

    public PlayersForDB() {
        playerDAO = new PlayerDAO();
        scoreCalculator = new ScoreCalculator();
    }

    public void createAndSaveNewPlayers(Long gameId) throws SQLException {
        playerDAO.save(WHITE, gameId);
        playerDAO.save(BLACK, gameId);
    }

    public Long getPlayerIdByGameIdAndTeamColor(Long gameId, TeamColor teamColor)
        throws SQLException {
        return playerDAO.findIdByGameIdAndTeamColor(gameId, teamColor);
    }

    public ScoresEntity getPlayersScores(Long gameId) throws SQLException {
        Long whitePlayerId = playerDAO.findIdByGameIdAndTeamColor(gameId, WHITE);
        Long blackPlayerId = playerDAO.findIdByGameIdAndTeamColor(gameId, BLACK);
        double whitePlayerScore = playerDAO.findScoreByPlayerId(whitePlayerId);
        double blackPlayerScore = playerDAO.findScoreByPlayerId(blackPlayerId);
        return new ScoresEntity(whitePlayerScore, blackPlayerScore);
    }

    public void removeAllByChessGame(Long gameId) throws SQLException {
        playerDAO.removeAllByChessGame(gameId);
    }

    public void calculateAndUpdateScores(Long playerId, List<PiecePositionFromDB> piecesPositions)
        throws SQLException {

        double score = scoreCalculator.getCalculatedScore(piecesPositions);
        playerDAO.updateScore(playerId, score);
    }
}
