package chess.utils;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DbUtilTest {
    @Test
    void connection() throws SQLException {
        DataSource dataSource = DbUtil.getDataSource("chess");
        assertNotNull(dataSource.getConnection());
    }
}
