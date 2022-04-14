package chess.model.dao;

import chess.utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {
    private static final Connection connection = DBConnector.getConnection();

    public void init() {
        String query = "insert into turns (turn) values (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "WHITE");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String findOne() {
        String query = "select turn from turns limit 1";
        String turn = "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            turn = resultSet.getString("turn");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return turn;
    }

    public void update(String nextTurn) {
        String query = "UPDATE turns SET turn = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nextTurn);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAll() {
        String query = "DELETE FROM turns";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
