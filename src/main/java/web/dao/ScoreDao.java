package web.dao;

import chess.Score;
import chess.piece.Color;

public class ScoreDao {

    private final JdbcTemplate jdbcTemplate;

    public ScoreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Score findScoreByColor(Color color) {
        return jdbcTemplate.queryForObject("SELECT score FROM Score WHERE color = ?",
                rs -> new Score(rs.getBigDecimal("score")), color.name());
    }

    public void saveScoreByColor(Score score, Color color) {
        jdbcTemplate.update("INSERT INTO Score(color, score) VALUES(?, ?)",
                color.name(), score.getValue().toPlainString());
    }

    public void updateScoreByColor(Score score, Color color) {
        jdbcTemplate.update("UPDATE Score SET score = ? WHERE color = ?",
                score.getValue().toPlainString(), color.name());
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM Score");
    }
}
