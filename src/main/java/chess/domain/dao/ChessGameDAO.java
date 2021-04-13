package chess.domain.dao;

import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessGameDAO {

    private final DBConnection dbConnection;

    public ChessGameDAO() {
        this.dbConnection = new DBConnection();
    }

    public void create(String roomID) {
        String query = "INSERT INTO game(roomID, turn) VALUES(?, ?)";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, roomID);
            preparedStatement.setString(2, "white");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String loadGameTurnByRoomID(String roomID) {
        String query = "SELECT turn FROM game WHERE roomID = ?";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, roomID);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getString("turn");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Optional<ChessGameEntity> findRoomID(String roomID) {
        String query = "SELECT * FROM game WHERE roomID=?";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, roomID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new ChessGameEntity(rs.getString("roomID"), rs.getString("turn")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(ChessGame chessGame) {
        String query = "UPDATE game SET turn = ? WHERE roomID = ?";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, chessGame.getStatus());
            preparedStatement.setString(2, chessGame.getRoomID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteGameByRoomID(String roomID) {
        String query = "DELETE FROM game WHERE roomID = ?";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, roomID);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> chessGameNames() {
        List<String> rooms = new ArrayList<>();
        String query = "SELECT roomID FROM game";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                rooms.add(rs.getString("roomID"));
            }
            return rooms;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
}
