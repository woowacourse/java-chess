package chess.model.dao;

import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PieceDao {
    private static final Connection connection = DBConnector.getConnection();

    public static String getPieceName(Piece piece) {
        return (piece.getTeam().name() + "-" + piece.getName()).toLowerCase();
    }

    public void init(Board board) {
        String query = "insert into pieces (position, name) values (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            for (Map.Entry<Position, Piece> entry : board.getBoard().entrySet()) {
                preparedStatement.setString(1, entry.getKey().getPosition());
                preparedStatement.setString(2, getPieceName(entry.getValue()));
                preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Map<String, String> findAll() {
        String query = "select position, name from pieces";
        Map<String, String> board = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            makeBoard(board, resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return board;
    }

    private void makeBoard(Map<String, String> board, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String piece = resultSet.getString("name");
            String position = resultSet.getString("position");
            board.put(position, piece);
        }
    }

    public String findByPosition(String source) {
        String query = "select name from pieces where position = (?)";
        String piece = "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, source);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            piece = resultSet.getString("name");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return piece;
    }

    public void updateByPosition(String position, String pieceName) {
        String query = "UPDATE pieces SET name = (?) WHERE position = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pieceName);
            preparedStatement.setString(2, position);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAll() {
        String query = "DELETE FROM pieces";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
