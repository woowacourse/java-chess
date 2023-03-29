package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {
    
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호
    private final String tableName;
    
    public GameDAO(final String tableName) {
        this.tableName = tableName;
    }
    
    public void addGame() {
        final var query = String.format("INSERT INTO %s VALUES();", this.tableName);
        try (final var connection = this.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void resetGame(int id) {
        final var query = String.format("DELETE FROM %s WHERE id = %d", this.tableName, id);
        try (final var connection = this.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Integer> fetchGames() {
        final var query = String.format("SELECT * FROM %s", this.tableName);
        List<Integer> games = new ArrayList<>();
        try (final var connection = this.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final var id = resultSet.getInt("id");
                games.add(id);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        
        return games;
    }
    
    public void reset() {
        final var query = String.format("DELETE FROM %s", this.tableName);
        try (final var connection = this.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int getLastGameID() {
        final var query = String.format("SELECT * FROM %s", this.tableName);
        int id = -1;
        try (final var connection = this.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    
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
}
