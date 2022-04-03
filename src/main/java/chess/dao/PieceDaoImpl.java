package chess.dao;

import chess.piece.Piece;
import chess.piece.PieceFactory;
import chess.piece.Pieces;
import chess.piece.position.Position;
import chess.utils.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDaoImpl implements PieceDao {

    @Override
    public void updatePosition(final String position, final String type, final String team) {
        String query = "update piece set type =?, team =? where position = ?";

        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, team);
            preparedStatement.setString(3, position);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Piece> findAllByBoardId(final Long boardId) {
        String query = "select position, team, type from piece WHERE board_id = ?";

        try (Connection connection = JdbcConnector.getConnection()){
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             preparedStatement.setLong(1, boardId);
             ResultSet resultSet = preparedStatement.executeQuery();
            List<Piece> pieces = new ArrayList<>();
            while (resultSet.next()) {
                String position = resultSet.getString(1);
                String team = resultSet.getString(2);
                String type = resultSet.getString(3);

                pieces.add(PieceFactory.create(position, team, type));
            }
            return pieces;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long findIdByPositionAndBoardId(String position, Long boardId) {
        String query = "select id from piece WHERE position = ? AND board_id = ?";

        try (Connection connection = JdbcConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, position);
            preparedStatement.setLong(2, boardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
