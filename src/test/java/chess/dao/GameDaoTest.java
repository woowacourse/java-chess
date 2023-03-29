package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.dto.GameDto;
import java.sql.SQLException;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("GameDao 는")
class GameDaoTest {

    private final GameDao gameDao = new GameDao();

    @BeforeEach
    @Test
    void 새로운_게임을_저장할_수_있다() {
        // given
        clear();
        final GameDto gameDto = GameDto.create();

        // when & then
        assertDoesNotThrow(() -> gameDao.create(gameDto));
    }

    @AfterEach
    void clear() {
        final String query = "DELETE FROM game";
        try (final var connection = gameDao.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void connection() throws SQLException {
        try (final var connection = gameDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    void 생성된_게임이_없으면_EMPTY_LIST를_반환한다() {
        clear();
        final GameDto gameDto = GameDto.from(true);
        assertThat(gameDao.findAllIdsByIsRunning(gameDto)).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    void 생성된_게임_id를_조회할_수_있다() {
        final GameDto gameDto = GameDto.from(true);
        assertThat(gameDao.findAllIdsByIsRunning(gameDto).get(0)).isEqualTo(1);
    }

    @Test
    void 생성된_게임_id로_turn을_조회할_수_있다() {
        final GameDto gameDto = GameDto.from(1);
        assertThat(gameDao.findTurnById(gameDto)).isEqualTo(new Turn(Color.WHITE));
    }

    @Test
    void 새로운_turn으로_업데이트할_수_있다() {
        // given
        GameDto gameDto = GameDto.of(1, "Black");

        // when
        gameDao.updateTurn(gameDto);
        gameDto = GameDto.from(1);

        // then
        assertThat(gameDao.findTurnById(gameDto)).isEqualTo(new Turn(Color.BLACK));
    }

    @Test
    void 진행중인_게임을_삭제할_수_있다() {
        // given & when
        final GameDto gameDto = GameDto.from(true);
        gameDao.delete(1);

        // then
        assertThat(gameDao.findAllIdsByIsRunning(gameDto)).isEqualTo(Collections.EMPTY_LIST);
    }
}
