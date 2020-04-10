package chess.dao;

import chess.utils.ConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionManagerTest {
    @Test
    @DisplayName("데이터베이스에 연결하는 것을 테스트")
    public void connection() {
        Connection con = ConnectionManager.getConnection();
        assertNotNull(con);
    }
}
