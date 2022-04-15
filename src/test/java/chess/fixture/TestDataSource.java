package chess.fixture;

import chess.util.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDataSource implements DataSource {

    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public TestDataSource() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "CREATE DATABASE IF NOT EXISTS test DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci");
             PreparedStatement preparedStatement1 = connection.prepareStatement("USE test");
             PreparedStatement preparedStatement2 = connection.prepareStatement("DROP TABLE IF EXISTS game, piece");
             PreparedStatement preparedStatement3 = connection.prepareStatement("CREATE TABLE `game`"
                     + "("
                     + "    `current_turn` enum('WHITE_TURN', 'BLACK_TURN', 'END') NOT NULL,"
                     + "    PRIMARY KEY (`current_turn`)"
                     + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;");
             PreparedStatement preparedStatement4 = connection.prepareStatement("CREATE TABLE `piece`"
                     + "("
                     + "    `id`              bigint    NOT NULL AUTO_INCREMENT,"
                     + "    `color`           varchar(8) NOT NULL,"
                     + "    `piece_type`      varchar(8) NOT NULL,"
                     + "    `position_column` varchar(8) NOT NULL,"
                     + "    `position_row`    varchar(8) NOT NULL,"
                     + "    PRIMARY KEY (`id`)"
                     + ") ENGINE=InnoDB AUTO_INCREMENT=568 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;");
        ) {
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            preparedStatement3.executeUpdate();
            preparedStatement4.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Connection getConnection() {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement("USE test");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("connection 획득 실패");
        }

        return connection;
    }
}
