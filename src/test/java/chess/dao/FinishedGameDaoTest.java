package chess.dao;

import static org.junit.jupiter.api.Assertions.*;

import chess.dto.FinishedGameDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("FinishedGameDao 는")
class FinishedGameDaoTest {

    private final FinishedGameDao finishedGameDao = new FinishedGameDao();

    @Test
    void 종료된_게임을_저장할_수_있다() {
        // given
        final FinishedGameDto finishedGameDto = new FinishedGameDto("White");

        // when & then
        assertDoesNotThrow(() -> finishedGameDao.create(finishedGameDto));
    }
}
