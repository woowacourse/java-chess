package chess.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {
    private final JdbcConnector jdbcConnector;

    public ChessGameDao(JdbcConnector jdbcConnector) {
        this.jdbcConnector = jdbcConnector;
    }

    public void addGame() {
        final var query = "INSERT INTO ChessGameDB VALUES()";
        try (final var connection = jdbcConnector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findLastInsertGame() {
        final var query = "SELECT MAX(gameIdx) \"gameIdx\" FROM ChessGameDB";
        try (final var connection = jdbcConnector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("gameIdx");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> findRemainGames() {
        final var query = "select ChessGameDB.gameIdx\n"
                + "from ChessGameDB, ChessBoardDB\n"
                + "where ChessGameDB.gameIdx = ChessBoardDB.gameIdx\n"
                + "group by gameIdx";
        try (final var connection = jdbcConnector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            List<Integer> gameIdxs = new ArrayList<>();
            while (resultSet.next()) {
                gameIdxs.add(resultSet.getInt("gameIdx"));
            }
            return gameIdxs;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
