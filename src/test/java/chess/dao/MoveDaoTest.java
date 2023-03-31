package chess.dao;

import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A3;
import static chess.model.board.PositionFixture.A4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        // when, then
        assertThatCode(() -> moveDao.save(A2, A3)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findAll()을 테스트한다.")
    void findAll_whenCall_thenReturnMoves() {
        // given
        moveDao.save(A2, A3);

        // when, then
        assertThat(moveDao.findAll()).hasSize(1);
    }

    @Test
    @DisplayName("hasGame()을 했을 때, 기존 게임이 있으면 true 없으면 false를 반환한다.")
    void hasGame_whenCall_thenReturnBoolean() {
        // when, then
        assertAll(
                () -> assertThat(moveDao.hasGame()).isFalse(),
                () -> {
                    moveDao.save(A2, A3);
                    assertThat(moveDao.hasGame()).isTrue();
                }
        );
    }

    @Test
    @DisplayName("truncateMove()를 실행하면 해당 테이블이 초기화 된다.")
    void truncateMove_whenCall_thenTruncateTable() {
        // given
        moveDao.save(A2, A4);

        // when
        moveDao.truncateMove();

        // then
        assertThat(moveDao.hasGame()).isFalse();
    }
}
