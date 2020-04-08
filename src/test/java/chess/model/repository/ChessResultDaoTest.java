package chess.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.domain.board.ChessGame;
import chess.model.domain.board.TeamScore;
import chess.model.domain.piece.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessResultDaoTest {

    private ChessResultDao chessResultDao;

    @BeforeEach
    public void setup() {
        chessResultDao = ChessResultDao.getInstance();
    }

    @DisplayName("Connection Test")
    @Test
    public void connection() {
        Connection con = chessResultDao.getConnection();
        assertThat(Objects.nonNull(con)).isTrue();
    }

    @Test
    void closeConnection() {
    }

    @DisplayName("ChessGameResult CRUD")
    @Test
    public void crud() throws SQLException {
        TeamScore defaultTeamScore = new ChessGame().getTeamScore();
        Map<Color, Double> teamScore = defaultTeamScore.getTeamScore();
        Map<Color, Double> teamScoreUpdate = new HashMap<>(teamScore);
        String gameId = "T01-20200405-001";

        // Select
        assertThat(chessResultDao.selectTeamScore(gameId)).isEmpty();

        // Insert
        chessResultDao.insertTeamScore(gameId, teamScore);
        assertThat(chessResultDao.selectTeamScore(gameId)).isEqualTo(teamScore);

        // Update
        teamScoreUpdate.put(Color.BLACK, 1.0);
        chessResultDao.updateTeamScore(gameId, teamScoreUpdate);
        assertThat(chessResultDao.selectTeamScore(gameId)).isEqualTo(teamScoreUpdate);

        // Delete
        chessResultDao.deleteTeamScore(gameId);
        assertThat(chessResultDao.selectTeamScore(gameId)).isEmpty();
    }
}