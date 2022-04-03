package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;
import chess.dto.StatusDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusDaoTest {

    @BeforeAll
    static void clearBeforeTest() {
        final StatusDao dao = new StatusDao();
        dao.delete();
    }

    @AfterEach
    void clear() {
        final StatusDao dao = new StatusDao();
        dao.delete();
    }

    @Test
    @DisplayName("게임 정보를 저장한다.")
    void save() {
        // given
        final StatusDao dao = new StatusDao();
        final GameStatus gameStatus = GameStatus.PLAYING;
        final Color currentTurn = Color.BLACK;

        // when
        final int actual = dao.save(gameStatus, currentTurn);

        // then
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("게임 정보를 삭제한다.")
    void delete() {
        // given
        final StatusDao dao = new StatusDao();
        final GameStatus gameStatus = GameStatus.PLAYING;
        final Color currentTurn = Color.BLACK;
        dao.save(gameStatus, currentTurn);

        // when
        final int actual = dao.delete();

        // then
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("게임 정보를 조회한다.")
    void find() {
        // given
        final StatusDao dao = new StatusDao();
        final GameStatus gameStatus = GameStatus.PLAYING;
        final Color currentTurn = Color.BLACK;
        dao.save(gameStatus, currentTurn);

        // when
        final StatusDto dto = dao.find();

        // then
        assertThat(dto.getGameStatus()).isEqualTo(gameStatus);
        assertThat(dto.getCurrentTurn()).isEqualTo(currentTurn);
    }

    @Test
    @DisplayName("게임 정보를 업데이트한다.")
    void update() {
        // given
        final StatusDao dao = new StatusDao();
        dao.save(GameStatus.PLAYING, Color.BLACK);

        // when
        final GameStatus gameStatus = GameStatus.KING_DIE;
        final Color currentTurn = Color.WHITE;

        dao.update(gameStatus, currentTurn);
        final StatusDto dto = dao.find();

        // then
        assertThat(dto.getGameStatus()).isEqualTo(gameStatus);
        assertThat(dto.getCurrentTurn()).isEqualTo(currentTurn);
    }
}