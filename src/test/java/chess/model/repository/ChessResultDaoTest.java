package chess.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.domain.board.ChessGame;
import chess.model.domain.board.TeamScore;
import chess.model.domain.piece.Color;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessResultDaoTest {

    private ChessResultDao chessResultDao;

    @BeforeEach
    public void setup() {
        chessResultDao = ChessResultDao.getInstance();
    }

    @DisplayName("ChessGameResult CRUD")
    @Test
    public void crud() throws SQLException {
        TeamScore defaultTeamScore = new ChessGame().getTeamScore();
        Map<Color, Double> teamScore = defaultTeamScore.getTeamScore();
        Map<Color, Double> teamScoreUpdate = new HashMap<>(teamScore);
        int gameId = 1;

        // Select
        assertThat(chessResultDao.select(gameId)).isEmpty();

        // Insert
        chessResultDao.insert(gameId, new TeamScore(teamScore));
        assertThat(chessResultDao.select(gameId)).isEqualTo(teamScore);

        // Update
        teamScoreUpdate.put(Color.BLACK, 1.0);
        chessResultDao.update(gameId, new TeamScore(teamScoreUpdate));
        assertThat(chessResultDao.select(gameId)).isEqualTo(teamScoreUpdate);

        // Delete
        chessResultDao.delete(gameId);
        assertThat(chessResultDao.select(gameId)).isEmpty();
    }
}