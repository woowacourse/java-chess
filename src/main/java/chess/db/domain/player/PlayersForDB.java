package chess.db.domain.player;

import static chess.beforedb.domain.player.type.TeamColor.BLACK;
import static chess.beforedb.domain.player.type.TeamColor.WHITE;

import chess.beforedb.domain.player.type.TeamColor;
import chess.db.dao.PlayerDAO;
import chess.db.domain.board.PiecePositionFromDB;
import chess.db.domain.game.ScoresEntity;
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
