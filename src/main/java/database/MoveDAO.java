package database;

import chess.command.Command;
import chess.command.MoveCommand;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class MoveDAO {
    
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호
    
    private final String tableName;
    
    public MoveDAO(String tableName) {
        this.tableName = tableName;
    }
    
    public void addMove(final MoveCommand command) {
        final var query = String.format("INSERT INTO %s (`from`,`to`) VALUES(?, ?)", this.tableName);
        try (final var connection = this.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, command.getFrom().getLabel());
            preparedStatement.setString(2, command.getTo().getLabel());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Command> fetchCommands() {
        final var query = String.format("SELECT * FROM %s", this.tableName);
        List<Command> commands = new ArrayList<>();
        try (final var connection = this.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final var from = resultSet.getString("from");
                final var to = resultSet.getString("to");
                final var command = new MoveCommand(List.of(from, to));
                commands.add(command);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        
        return commands;
    }
    
    public void resetMoves() {
        final var query = String.format("DELETE FROM %s", this.tableName);
        try (final var connection = this.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
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