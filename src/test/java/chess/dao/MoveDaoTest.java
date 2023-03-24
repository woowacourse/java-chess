package chess.dao;

import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A3;
import static chess.model.board.PositionFixture.B6;
import static chess.model.board.PositionFixture.B7;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
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
        final MoveDto whiteMove = new MoveDto(A2, A3);
        final MoveDto blackMove = new MoveDto(B7, B6);
        final MoveSaveStrategy moveSaveStrategy = new MoveSaveStrategy(List.of(whiteMove, blackMove));

        // when, then
        assertThatCode(() -> moveDao.saveAll(moveSaveStrategy)).doesNotThrowAnyException();
    }
}
