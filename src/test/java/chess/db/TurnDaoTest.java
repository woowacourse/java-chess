package chess.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.character.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurnDaoTest {
    private TurnDao turnDao;

    @BeforeEach
    void setUp() {
        turnDao = new TurnDao(new TestConnectionGenerator());
        turnDao.delete();
    }

    @Test
    void load() {
        turnDao.add(Team.WHITE);
        assertThat(turnDao.load()).isEqualTo(Team.WHITE);
    }

    @Test
    void change() {
        turnDao.add(Team.WHITE);
        turnDao.change(Team.BLACK);
        assertThat(turnDao.load()).isEqualTo(Team.BLACK);
    }

    @Test
    void add() {
        turnDao.add(Team.BLACK);
        assertThat(turnDao.load()).isEqualTo(Team.BLACK);
    }

    @Test
    void delete() {
        turnDao.add(Team.WHITE);
        turnDao.delete();
        assertThatThrownBy(() -> turnDao.load())
                .isInstanceOf(RuntimeException.class);
    }
}
