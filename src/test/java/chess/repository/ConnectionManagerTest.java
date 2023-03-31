package chess.repository;

import java.sql.Connection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectionManagerTest {

    @Test
    void 커넥션을_획득한다() {
        final Connection connection = ConnectionManager.getConnection();

        Assertions.assertThat(connection).isNotNull();
    }
}
