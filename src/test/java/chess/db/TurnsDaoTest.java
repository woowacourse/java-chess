package chess.db;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.TurnDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnsDaoTest {
    private TurnsDao turnsDao;

    @BeforeEach
    void setUp() {
        turnsDao = DBFixtures.createTurnsDao();
    }

    @AfterEach
    void tearDown() {
        turnsDao.deleteAll();
    }

    @Test
    @DisplayName("턴 색상을 저장한다.")
    void createTurns() {
        TurnDto turnDto = new TurnDto("WHITE");

        turnsDao.create(turnDto);

        assertThat(turnsDao.find()).isEqualTo(turnDto);
    }

    @Test
    @DisplayName("저장된 턴 색상을 가져온다.")
    void findTurn() {
        TurnDto turnDto = new TurnDto("WHITE");
        turnsDao.create(turnDto);

        assertThat(turnsDao.find()).isEqualTo(turnDto);
    }

    @Test
    @DisplayName("저장된 턴을 삭제한다.")
    void deleteTurn() {
        TurnDto turnDto = new TurnDto("WHITE");
        turnsDao.create(turnDto);

        turnsDao.deleteAll();

        assertThat(turnsDao.find()).isNull();
    }
}
