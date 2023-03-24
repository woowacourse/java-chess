package domain.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @Test
    @DisplayName("Connection 을 확인한다.")
    void checkConnection() {
        // Then
        try (final var connection = chessGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
