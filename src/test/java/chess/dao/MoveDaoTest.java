package chess.dao;

import chess.dto.MoveDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MoveDaoTest {
    private final MoveDao moveDao = new MoveDao();
    private final List<MoveDto> movements = List.of(
            new MoveDto("a1", "a2"),
            new MoveDto("a2", "a3"),
            new MoveDto("h3", "h5")
    );

    @BeforeEach
    void setup() {
        moveDao.deleteAll();

        for (MoveDto movement : movements) {
            moveDao.addMove(movement);
        }
    }

    @DisplayName("DB에 연결할 수 있다.")
    @Test
    void connectionTest() {
        try (Connection connection = moveDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("DB에 움직임(move)을 저장할 수 있다.")
    @Test
    void addMovementTest() {
        MoveDto moveDto = new MoveDto("a1", "a2");

        Assertions.assertDoesNotThrow(() -> moveDao.addMove(moveDto));
    }

    @DisplayName("DB에 저장된 모든 움직임을 가져올 수 있다.")
    @Test
    void loadMovementTest() {
        assertThat(moveDao.findAll())
                .hasSize(3);
    }

}
