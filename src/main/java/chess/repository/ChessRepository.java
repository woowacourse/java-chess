package chess.repository;

import chess.domain.history.History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessRepository {

    private final Connection connection;

    public ChessRepository(Connection connection) {
        this.connection = connection;

    }

    public void insertHistory(String source, String destination, String team) throws SQLException {
        String query = "INSERT INTO history (SOURCE, DESTINATION, TEAM) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, source);
        preparedStatement.setString(2, destination);
        preparedStatement.setString(3, team);
        preparedStatement.executeUpdate();
        System.out.println("좌표 삽입 성공");
    }

    public List<History> findAllHistories() throws SQLException {
        String query = "SELECT * FROM history";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<History> histories = new ArrayList<>();
        while (resultSet.next()) {
            History history = new History(
                    resultSet.getString("source"), resultSet.getString("destination"), resultSet.getString("team"));
            histories.add(history);
        }
        return histories;
    }

    public Optional<History> findHistoryById(int id) throws SQLException {
        String query = "SELECT * FROM history WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(id));
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return Optional.empty();
        }
        History history = new History(
                resultSet.getString("source"), resultSet.getString("destination"), resultSet.getString("team"));
        return Optional.of(history);
    }

    public void deleteHistoryById(int id) throws SQLException {
        String query = "DELETE FROM history WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(id));
        preparedStatement.executeUpdate();
    }

    public void deleteAllHistories() throws SQLException {
        String query = "TRUNCATE history";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }
}
