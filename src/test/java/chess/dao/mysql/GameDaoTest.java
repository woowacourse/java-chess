package chess.dao.mysql;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.dao.connect.JdbcTemplate;
import chess.dao.connect.TestDbConnector;
import chess.dao.dto.GameDto;
import chess.dao.dto.GameUpdateDto;

class GameDaoTest {

    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameDao(new JdbcTemplate(new TestDbConnector()));
    }

    @DisplayName("데이터 저장 및 조회가 가능해야 한다.")
    @Test
    void saveAndFind() {
        final boolean finished = false;
        final String currentTurnColor = WHITE.getName();
        final Long id = gameDao.save(new GameDto(0L, 1L, 2L, finished, currentTurnColor));

        final GameDto gameDto = gameDao.findById(id);
        assertAll(() -> {
                    assertThat(gameDto.getFinished()).isEqualTo(finished);
                    assertThat(gameDto.getCurrentTurnColor()).isEqualTo(currentTurnColor);
                });
        gameDao.remove(id);
    }

    @DisplayName("데이터를 수정할 수 있어야 한다.")
    @Test
    void update() {
        final Long id = gameDao.save(new GameDto(0L, 1L, 2L, false, WHITE.getName()));

        final boolean finished = true;
        final String currentTurnColor = BLACK.getName();
        final GameUpdateDto gameUpdateDto = new GameUpdateDto(id, finished, currentTurnColor);
        gameDao.update(gameUpdateDto);

        final GameDto updatedGameDto = gameDao.findById(id);
        assertAll(() -> {
            assertThat(updatedGameDto.getFinished()).isEqualTo(finished);
            assertThat(updatedGameDto.getCurrentTurnColor()).isEqualTo(currentTurnColor);
        });
        gameDao.remove(id);
    }
}