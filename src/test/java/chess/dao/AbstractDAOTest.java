package chess.dao;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbstractDAOTest {

    AbstractDAO dao = BoardDAO.instance();

    @Test
    @DisplayName("연결 해제 테스트")
    void connectionAndCloseConnection() {
        assertThatCode(() -> {
            Connection connection = dao.connection();
            dao.closeConnection(connection);
        }).doesNotThrowAnyException();
    }
}