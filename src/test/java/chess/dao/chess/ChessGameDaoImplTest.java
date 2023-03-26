package chess.dao.chess;

import chess.entity.ChessGameEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessGameDaoImplTest {

    @Test
    @DisplayName("사용자의 아이디에 존재하는 체스 게임이 존재하면, 해당 체스 게임을 반환한다.")
    void findByUserId() {
        // given
        final ChessGameDao chessGameDao = new MockChessGameDao();
        final ChessGameEntity expected = new ChessGameEntity(1L, "WHITE", 1L);
        final long userId = 1L;

        // when
        final Optional<ChessGameEntity> chessGameEntity = chessGameDao.findByUserId(userId);
        final ChessGameEntity actual = chessGameEntity.get();

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자가 아이디에 해당하는 체스 게임이 존재하지 않으면, 빈 객체를 반환한다.")
    void findByUserId_empty() {
        // given
        final ChessGameDao chessGameDao = new MockChessGameDao();
        final long userId = 2L;

        // when
        final Optional<ChessGameEntity> chessGameEntity = chessGameDao.findByUserId(userId);

        // then
        assertThat(chessGameEntity)
                .isEqualTo(Optional.empty());
    }
}
