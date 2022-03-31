package web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Score;
import chess.piece.Color;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreDaoTest {

    private ScoreDao dao;

    @BeforeEach
    void setUp() {
        dao = new ScoreDao(new JdbcTemplate());
        dao.deleteAll();
        dao.saveScoreByColor(new Score(new BigDecimal("10.0")), Color.BLACK);
        dao.saveScoreByColor(new Score(new BigDecimal("15.0")), Color.WHITE);
    }

    @AfterEach
    void tearDown() {
        dao.deleteAll();
    }

    @Test
    void findScoreByColor() {
        assertThat(dao.findScoreByColor(Color.BLACK)).isEqualTo(new Score(new BigDecimal("10.0")));
        assertThat(dao.findScoreByColor(Color.WHITE)).isEqualTo(new Score(new BigDecimal("15.0")));
    }

    @Test
    void updateScoreByColor() {
        dao.updateScoreByColor(new Score(new BigDecimal("20.6")), Color.WHITE);
        assertThat(dao.findScoreByColor(Color.WHITE)).isEqualTo(new Score(new BigDecimal("20.6")));
    }

}