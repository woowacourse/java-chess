package chess.web.connection;

import chess.utils.DBConnectionUtils;
import java.sql.Connection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBConnectionUtilsTest {

    @Test
    @DisplayName("DB 연결 테스트")
    void connectDB(){
        Connection connection = DBConnectionUtils.getConnection();

        Assertions.assertThat(connection).isNotNull();
    }
}
