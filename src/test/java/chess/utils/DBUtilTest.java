package chess.utils;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBUtilTest {
    @Test
    void connection() throws SQLException {
        DataSource dataSource = DBUtil.getDataSource("chess");
        assertNotNull(dataSource.getConnection());
    }
}
