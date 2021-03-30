package chess.repository;

import chess.domain.history.History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChessRepository {

    private final Connection connection;

    public ChessRepository(Connection connection) {
        this.connection = Objects.requireNonNull(connection, "DB에 연결되지 않았습니다.");
    }

    public void insertHistory(String source, String destination, String team) throws SQLException {
        String query = "INSERT INTO history (SOURCE, DESTINATION, TEAM) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, source);
        preparedStatement.setString(2, destination);
        preparedStatement.setString(3, team);
        preparedStatement.executeUpdate();
    }

    public List<History> findAllHistories() throws SQLException {
        String query = "SELECT * FROM history";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<History> histories = new ArrayList<>();
        while (resultSet.next()) {
            History history = generateHistoryFrom(resultSet);
            histories.add(history);
        }
        return histories;
    }

    private History generateHistoryFrom(ResultSet resultSet) throws SQLException {
        return new History(resultSet.getString("source"),
                resultSet.getString("destination"),
                resultSet.getString("team"));
    }

    public void deleteAllHistories() throws SQLException {
        String query = "TRUNCATE history";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }
}
