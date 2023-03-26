package chess.dao;

import chess.dto.MoveDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호
    
    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public int save(MoveDto moveDto) {
        final var query = "INSERT INTO movement(source, destination) VALUES(?, ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveDto.sourceCoordinate());
            preparedStatement.setString(2, moveDto.destinationCoordinate());
            return preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int deleteAll() {
        final var query = "DELETE FROM movement";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<MoveDto> selectAllMovement() {
        final var query = "SELECT * FROM movement";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
    
            List<MoveDto> moveDtos = new ArrayList<>();
            final var resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                moveDtos.add(new MoveDto(List.of(
                        resultSet.getString("source"),
                        resultSet.getString("destination")
                )));
            }
            return moveDtos;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
