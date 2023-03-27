package chess.dao;

import chess.dto.PieceDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PieceDao {

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

    public void create(final PieceDto pieceDto) {
        final String query = "INSERT INTO piece VALUES (?, ?, ?, ?, ?, ?)";
        final int id = autoIncrementId();
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, pieceDto.getRunningGameId());
            preparedStatement.setString(3, pieceDto.getType());
            preparedStatement.setString(4, pieceDto.getFile());
            preparedStatement.setInt(5, pieceDto.getRank());
            preparedStatement.setString(6, pieceDto.getColor());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> findAllIds() {
        String query = "SELECT id FROM piece";
        List<Integer> pieceIds = new ArrayList<>();
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pieceIds.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pieceIds;
    }

    private int autoIncrementId() {
        List<Integer> pieceIds = findAllIds();
        if (pieceIds.isEmpty()) {
            return 1;
        }
        return Collections.max(pieceIds) + 1;
    }
}
