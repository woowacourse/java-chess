package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.dto.RunningGameDto;
import java.sql.SQLException;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("RunningGameDao 는")
class RunningGameDaoTest {

    private final RunningGameDao runningGameDao = new RunningGameDao();

    @AfterEach
    void clear() {
        final String query = "DELETE FROM running_game";
        try (final var connection = runningGameDao.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void connection() throws SQLException {
        try (final var connection = runningGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    void 새로운_게임을_저장할_수_있다() {
        // given
        final RunningGameDto runningGameDto = new RunningGameDto("White");

        // when & then
        assertDoesNotThrow(() -> runningGameDao.save(runningGameDto));
    }

    @Test
    void 생성된_게임이_없으면_EMPTY_LIST를_반환한다() {
        assertThat(runningGameDao.findAllIds()).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    void 생성된_게임_id를_조회할_수_있다() {
        // given
        final RunningGameDto runningGameDto = new RunningGameDto("White");
        runningGameDao.save(runningGameDto);

        // when & then
        assertThat(runningGameDao.findAllIds().get(0)).isEqualTo(1);
    }

    @Test
    void 생성된_게임_id로_turn을_조회할_수_있다() {
        // given
        final RunningGameDto runningGameDto = new RunningGameDto("White");
        runningGameDao.save(runningGameDto);

        // when & then
        assertThat(runningGameDao.findTurnById(1)).isEqualTo(new Turn(Color.WHITE));
    }
}
