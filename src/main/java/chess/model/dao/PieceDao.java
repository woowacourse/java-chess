package chess.model.dao;

import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.piece.PieceFactory;
import chess.model.position.Position;
import chess.utils.DBConnector;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PieceDao {
    private static final Connection connection = DBConnector.getConnection();

    public void init(Board board) {
        String query = "insert into pieces (position, name) values (?, ?)";
        board.getBoard().forEach(((position, piece) -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, position.getPosition());
                preparedStatement.setString(2, piece.getPieceName());
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }));
    }

    public Map<Position, Piece> findAll() {
        String query = "select position, name from pieces";
        Map<Position, Piece> board = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            makeBoard(board, resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return board;
    }

    private void makeBoard(Map<Position, Piece> board, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String pieceResult = resultSet.getString("name");
            Piece piece = PieceFactory.create(pieceResult);
            String positionResult = resultSet.getString("position");
            Position position = Position.from(positionResult);
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
