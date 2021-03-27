package chess.domain.dao;

import chess.dao.ConnectionSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionSetupTest {
    @Test
    @DisplayName("정상적으로 DB에 연결되는 지 체크")
    public void getConnection() {
        ConnectionSetup connectionSetup = new ConnectionSetup();
        Connection con = connectionSetup.getConnection();
        assertNotNull(con);
    }
}
