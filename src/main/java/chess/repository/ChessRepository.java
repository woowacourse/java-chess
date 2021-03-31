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

    public List<History> findAllHistoriesByRoomId(int roomId) throws SQLException {
        String query = "SELECT * FROM HISTORY WHERE ROOM_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(roomId));
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
                resultSet.getString("team_type"));
    }

    public void insertHistory(String source, String destination, String teamType, int roomId) throws SQLException {
        String query = "INSERT INTO HISTORY (SOURCE, DESTINATION, TEAM_TYPE, ROOM_ID) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, source);
        preparedStatement.setString(2, destination);
        preparedStatement.setString(3, teamType);
        preparedStatement.setString(4, String.valueOf(roomId));
        preparedStatement.executeUpdate();
    }

    public void deleteAllHistoriesByRoomId(int roomId) throws SQLException {
        String query = "DELETE FROM HISTORY WHERE ROOM_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(roomId));
        preparedStatement.executeUpdate();
    }
}
