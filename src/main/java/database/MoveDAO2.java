package database;

import chess.history.Move;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public final class MoveDAO2 {
    
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호
    
    private final String tableName;
    
    public MoveDAO2(String tableName) {
        this.tableName = tableName;
    }
    
    public void addMove(final Move move, final int gameID) {
        final var query = String.format("INSERT INTO %s VALUES(?, ?, ?, ?)", this.tableName);
        try (final var connection = this.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, new Timestamp(move.getID()));
            preparedStatement.setString(2, move.getFromLabel());
            preparedStatement.setString(3, move.getToLabel());
            preparedStatement.setInt(4, gameID);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Move> fetchMoves(final int gameID) {
        final var query = String.format("SELECT * FROM %s WHERE game_id = ?", this.tableName);
        List<Move> moves = new ArrayList<>();
        try (final var connection = this.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameID);
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final var timestamp = resultSet.getTimestamp("id");
                final var id = timestamp.getTime();
                final var from = resultSet.getString("from");
                final var to = resultSet.getString("to");
                moves.add(Move.create(id, from, to));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        
        return moves;
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