package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;

public abstract class MoveTruncator {

    @AfterEach
    void tearDown() {
        try (
                final Connection connection = ConnectionGenerator.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE Move");
        ) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
