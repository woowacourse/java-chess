package chess.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addGame() {
        final var query = "INSERT INTO ChessGameDB VALUES()";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findLastInsertGame() {
        final var query = "SELECT MAX(gameIdx) \"gameIdx\" FROM ChessGameDB";
        try (final var connection = getConnection();
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
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            List<Integer> gameIdxs = new ArrayList<>();
            while(resultSet.next()){
                gameIdxs.add(resultSet.getInt("gameIdx"));
            }
            return gameIdxs;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
