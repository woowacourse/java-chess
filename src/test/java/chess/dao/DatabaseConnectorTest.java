package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatabaseConnectorTest {

    @DisplayName("docker 실행 상태에서 chess DB에 연결한다.")
    @Test
    void getConnection_chess() {
        DatabaseConnector databaseConnector = new DatabaseConnector(
                "jdbc:mysql://localhost:3307/chess", "user", "password");
        assertThatNoException().isThrownBy(databaseConnector::getConnection);
    }

    @DisplayName("H2 test DB에 연결한다.")
    @Test
    void getConnection_test() {
        DatabaseConnector databaseConnector = new DatabaseConnector(
                "jdbc:h2:~/test", "tester", "");
        assertThatNoException().isThrownBy(databaseConnector::getConnection);
    }
}
