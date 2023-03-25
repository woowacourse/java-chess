package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class MoveTruncator {

    @AfterAll
    static void tearDown() {
        truncateMove();
    }

    @BeforeEach
    void setUp() {
        truncateMove();
    }

    private static void truncateMove() {
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
