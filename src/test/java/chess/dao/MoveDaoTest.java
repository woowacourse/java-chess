package chess.dao;

import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveDaoTest extends MoveTruncator {

    private MoveDao moveDao;

    @BeforeEach
    void init() {
        moveDao = new MoveDaoImpl();
    }

    @Test
    @DisplayName("saveAll()을 테스트한다.")
    void saveAll_givenMoveDto_thenSuccess() {
        // given
        final MoveSaveStrategy moveSaveStrategy = new MoveSaveStrategy(A2, A3);

        // when, then
        assertThatCode(() -> moveDao.save(moveSaveStrategy)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findAll()을 테스트한다.")
    void findAll_whenCall_thenReturnMoves() {
        // given
        saveAll_givenMoveDto_thenSuccess();

        // when
        final MoveFindAllStrategy moveFindAllStrategy = new MoveFindAllStrategy();

        // then
        assertThat(moveDao.findAll(moveFindAllStrategy)).hasSize(1);
    }
}
