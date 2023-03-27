package chessgame.dao;

import chessgame.repository.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatabaseConnectionTest {

    @Test
    void getConnection() {
        final DatabaseConnection databaseConnection = new DatabaseConnection();

        Assertions.assertDoesNotThrow(databaseConnection::getConnection);
    }
}
