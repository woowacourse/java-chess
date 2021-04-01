package chess.domain.dao;

import chess.db.MySQLConnector;
import chess.domain.dto.HistoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

    public void insert(String name) throws SQLException {
        String query = "INSERT INTO History (Name) VALUES (?)";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public int findIdByName(String name) throws SQLException {
        String query = "SELECT * FROM History WHERE Name = ?";
        int id = -1;
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) return id;
            id = rs.getInt("history_id");

            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return id;
    }

    public void delete(String name) throws SQLException {
        String query = "DELETE FROM History WHERE Name = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

//    public void deleteById(String id) throws SQLException {
//        System.out.println("delete by id");
//        System.out.println(id);
//        String query = "DELETE FROM History WHERE HistoryId = ?";
//        try (Connection connection = MySQLConnector.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, id);
//            preparedStatement.executeUpdate();
//
//            MySQLConnector.closeConnection(connection);
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//    }

    public List<String> selectActive() throws SQLException {
        List<String> names = new ArrayList<>();
        String query = "SELECT * FROM History WHERE is_end = false";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                names.add(rs.getString("name"));

            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return names;
    }

//    public void clear(CommandDao commandDao) throws SQLException {
//        final List<String> historyIds = commandDao.selectAllHistoryId();
//        for (int i = 0; i < historyIds.size(); i++) {
//            System.out.println(historyIds.get(i));
//        }
//        for (String historyId : historyIds) {
//            final HistoryDto history = findById(historyId);
//            if (history == null) {
//                deleteById(historyId);
//            }
//            if (history != null) {
//                System.out.println(history.getName());
//            }
//        }
//    }

    public HistoryDto findById(String id) throws SQLException {
        Connection connection = MySQLConnector.getConnection();
        String query = "SELECT * FROM History WHERE history_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (!rs.next()) return null;
        return new HistoryDto(rs.getString("name"));
    }
}
