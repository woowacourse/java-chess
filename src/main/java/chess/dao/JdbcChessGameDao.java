package chess.dao;

import chess.domain.ChessGame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class JdbcChessGameDao implements ChessGameDao {
    private static final String SERVER = "localhost:3306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "username"; //  MySQL 서버 아이디
    private static final String PASSWORD = "password"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Integer> findAllId() {
        final var query = "SELECT game_id FROM game WHERE game_status != ? AND game_status != ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "end");
            preparedStatement.setString(2, "catch");

            final var resultSet = preparedStatement.executeQuery();
            List<Integer> ids = new ArrayList<>();
            while (resultSet.next()) {
                ids.add(Integer.parseInt(resultSet.getString("game_id")));
            }
            return ids;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessGame findById(int gameId) {
        return null;
    }

    @Override
    public void save(int gameId, ChessGame chessGame) {

    }

    @Override
    public void updateById(int gameId, ChessGame chessGame) {

    }

    @Override
    public void deleteById(int gameId) {

    }
}
