package chess.Dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

class MySQLConnectionSettingTest {

    private final MySQLConnectionSetting connection = MySQLConnectionSetting.getInstance();

    @Test
    public void connection() {
        Connection con = connection.getConnection();
        assertNotNull(con);
    }

}