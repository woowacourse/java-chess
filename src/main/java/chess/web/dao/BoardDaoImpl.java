package chess.web.dao;

import chess.board.Board;
import chess.board.Team;
import chess.board.Turn;
import chess.board.piece.Piece;
import chess.board.piece.PieceFactory;
import chess.board.piece.Pieces;
import chess.web.utils.JdbcConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardDaoImpl implements BoardDao {

    @Override
    public Optional<Turn> findTurnById(Long id) {
        final String query = "SELECT (turn) from board where id = ?";
        try (
                Connection connection = JdbcConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String turn = result.getString("turn");
                return Optional.of(new Turn(Team.from(turn)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateTurnById(Long id, String newTurn) {
        final String query = "UPDATE board set turn = ? where id = ?";
        try (
                Connection connection = JdbcConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, newTurn);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long save() {
        final String query = "INSERT INTO board (turn) values (?)";
        try (
                Connection connection = JdbcConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, "white");
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("[ERROR] 디비 저장에 실패했습니다.");
    }

    @Override
    public Optional<Board> findById(Long id) {
        final String query = "SELECT *" +
                "FROM board as b " +
                "JOIN piece as p ON b.id = p.board_id " +
                "WHERE b.id = ?";
        try (
                Connection connection = JdbcConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Piece> pieces = new ArrayList<>();
            String turn = "";

            while (resultSet.next()) {
                turn = resultSet.getString("turn");
                String position = resultSet.getString("position");
                String type = resultSet.getString("type");
                String team = resultSet.getString("team");
                pieces.add(PieceFactory.create(position, team, type));
            }

            return Optional.of(Board.create(Pieces.from(pieces), new Turn(Team.from(turn))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        final String query = "DELETE FROM board WHERE id = ?";
        try (
                Connection connection = JdbcConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
