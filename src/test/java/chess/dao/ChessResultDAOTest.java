package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.ChessGame;
import chess.domain.board.TeamScore;
import chess.domain.piece.Color;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessResultDAOTest {

    private static ChessResultDAO CHESS_RESULT_DAO;

    @BeforeAll
    public static void setup() {
        CHESS_RESULT_DAO = new ChessResultDAO();
    }

    @DisplayName("ChessGameResult CRUD")
    @Test
    public void crud() throws SQLException, IllegalAccessException {
        TeamScore defaultTeamScore = new ChessGame().getTeamScore();
        Map<Color, Double> teamScore = defaultTeamScore.getTeamScore();
        Map<Color, Double> teamScoreUpdate = new HashMap<>(teamScore);
        String gameId = "T01-20200405-001";

        // Select
        assertThatThrownBy(() -> CHESS_RESULT_DAO.selectTeamScore(gameId))
            .isInstanceOf(IllegalAccessException.class);

        // Insert
        CHESS_RESULT_DAO.insertTeamScore(gameId, teamScore);
        assertThat(CHESS_RESULT_DAO.selectTeamScore(gameId)).isEqualTo(teamScore);

        // Update
        teamScoreUpdate.put(Color.BLACK, 1.0);
        CHESS_RESULT_DAO.updateTeamScore(gameId, teamScoreUpdate);
        assertThat(CHESS_RESULT_DAO.selectTeamScore(gameId)).isEqualTo(teamScoreUpdate);

        // Delete
        CHESS_RESULT_DAO.deleteTeamScore(gameId);
        assertThatThrownBy(() -> CHESS_RESULT_DAO.selectTeamScore(gameId))
            .isInstanceOf(IllegalAccessException.class);
    }
}