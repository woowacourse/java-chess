package chess.dao;

import static chess.helper.PositionFixture.A2;
import static chess.helper.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcChessMovementDaoTest {

    private ChessMovementDao dao;

    @BeforeEach
    void beforeEach() {
        final ConnectionStrategy connectionStrategy = new MySqlConnectionStrategy();
        dao = new JdbcChessMovementDao(connectionStrategy);


        try (final Connection connection = connectionStrategy.findConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE movement")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("save()는 MOVE 명령어 입력 시 전달한 source와 target을 건네주면 명령어를 저장한다")
    void save_givenMovement_thenSave() {
        // when, then
        assertThatCode(() -> dao.save(A2, A3)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findAll()은 이전에 저장한 모든 MOVE 명령어를 조회한다")
    void findAll_whenCall_thenReturnAllMovement() {
        // given
        dao.save(A2, A3);

        // when
        final List<Movement> actual = dao.findAll();

        // then
        assertThat(actual).hasSize(1);

        final Movement movement = actual.get(0);

        assertThat(movement.getSource()).isEqualTo(A2.getPosition());
        assertThat(movement.getTarget()).isEqualTo(A3.getPosition());
    }

    @Test
    @DisplayName("delete()는 모든 MOVE 명령어를 삭제한다")
    void delete_whenCall_thenDeleteAllMovement() {
        // given
        dao.save(A2, A3);
        final List<Movement> before = dao.findAll();

        // when
        dao.delete();

        // then
        final List<Movement> after = dao.findAll();

        assertThat(before).hasSize(1);
        assertThat(before.size()).isGreaterThan(after.size());
        assertThat(after).isEmpty();
    }
}
