package dao;

import dto.MoveDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class ChessDao {

    private static final String SERVER = "localhost:3306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "4780"; // MySQL 서버 비밀번호

    private static Connection connection;

    static {
        // 드라이버 연결
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void save(final MoveDto moveDto) {
        final String query = "INSERT INTO move (source, target) VALUES(?, ?)";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveDto.getSource());
            preparedStatement.setString(2, moveDto.getTarget());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalArgumentException("입력이 올바르지 않습니다.");
        }
    }

    public List<MoveDto> findAllMove() {
        final String query = "SELECT * FROM move";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            final List<MoveDto> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(new MoveDto(
                        resultSet.getString("source"),
                        resultSet.getString("target")
                ));
            }

            return result;
        } catch (final SQLException e) {
            throw new IllegalArgumentException("입력이 올바르지 않습니다.");
        }
    }

    public void deleteAll() {
        final String query = "DELETE FROM move";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("입력이 올바르지 않습니다.");
        }
    }

}
