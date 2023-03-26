package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBChessGameDao implements ChessGameDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DBChessGameDao 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void save(String sourceCoordinateRow, String sourceCoordinateColumn, String destinationCoordinateRow,
        String destinationCoordinateColumn) {
        final var query = "INSERT INTO chess_game(source_coordinate_row, source_coordinate_column, destination_coordinate_row, destination_coordinate_column) VALUES(?, ?,?,?)";
        try (final var connection = getConnection();
            final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sourceCoordinateRow);
            preparedStatement.setString(2, sourceCoordinateColumn);
            preparedStatement.setString(3, destinationCoordinateRow);
            preparedStatement.setString(4, destinationCoordinateColumn);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<List<String>> select() {
        List<List<String>> coordinates = new ArrayList<>();
        final var query = "SELECT * FROM chess_game";
        try (final var connection = getConnection();
            final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                List<String> coordinate = new ArrayList<>();
                coordinate.add(resultSet.getString("source_coordinate_row"));
                coordinate.add(resultSet.getString("source_coordinate_column"));
                coordinate.add(resultSet.getString("destination_coordinate_row"));
                coordinate.add(resultSet.getString("destination_coordinate_column"));
                coordinates.add(coordinate);
            }

            return coordinates;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        String sql = "DELETE FROM chess_game";
        try (final Connection connection = getConnection();
            final var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
