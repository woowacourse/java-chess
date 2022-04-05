package chess.dao;

import chess.dto.TurnDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static chess.utils.DbConnector.getConnection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TurnDaoTest {

    private final TurnDao turnDao = new TurnDaoImpl();

    @AfterEach
    void tearDown() {
        final String sql = "update turn set team = 'WHITE'";
        try (final Connection connection = getConnection()) {
            connection.prepareStatement(sql)
                    .executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("db에서 현재 턴을 찾는다.")
    void findTurn() {
        final TurnDto turnDto = turnDao.findTurn();
        final String expected = "WHITE";

        final String actual = turnDto.getTurn();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("db에서 턴을 업데이트해준다.")
    void updateTurn() {
        turnDao.updateTurn("WHITE");
        final String expected = "BLACK";

        final String actual = turnDao.findTurn().getTurn();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("db에서 턴을 화이트로 리셋해준다.")
    void resetTurn() {
        turnDao.updateTurn("WHITE");
        turnDao.resetTurn();
        final String expected = "WHITE";

        final String actual = turnDao.findTurn().getTurn();

        assertThat(actual).isEqualTo(expected);
    }
}