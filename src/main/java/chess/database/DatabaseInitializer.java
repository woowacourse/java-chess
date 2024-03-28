package chess.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String CHESS_DDL_PATH = "chess.sql";
    private static final String SQL_QUERY_DELIMITER = ";";

    private final JdbcConnectionPool connectionPool;

    public DatabaseInitializer(final JdbcConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void initialize() {
        final Connection connection = connectionPool.getConnection();
        try (final Statement statement = connection.createStatement()) {
            String[] queries = getQueries();

            for (String query : queries) {
                statement.execute(query);
            }
        } catch (IOException | SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public String[] getQueries() throws IOException {
        try (InputStream inputStream = DatabaseInitializer.class.getClassLoader().getResourceAsStream(CHESS_DDL_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            StringBuilder queryBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                queryBuilder.append(line);
            }
            return queryBuilder.toString().split(SQL_QUERY_DELIMITER);
        }
    }
}
