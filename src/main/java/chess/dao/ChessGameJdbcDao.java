package chess.dao;

import chess.dto.MoveDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameJdbcDao implements ChessGameDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveMove(MoveDto moveDto) {
        var query = "INSERT INTO move_history(source, target) VALUES(?, ?)";
        try (var connection = getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveDto.getSource());
            preparedStatement.setString(2, moveDto.getTarget());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MoveDto> findAll() {
        var query = "SELECT * FROM move_history";
        List<MoveDto> moveHistories = new ArrayList<>();
        try (var connection = getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                moveHistories.add(MoveDto.of(
                        resultSet.getInt("id"),
                        resultSet.getString("source"),
                        resultSet.getString("target")
                ));
            }
            return moveHistories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        var query = "DELETE FROM move_history";
        try (var connection = getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

