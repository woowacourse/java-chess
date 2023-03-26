package database;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Role;
import chess.domain.piece.Team;
import database.dto.SquareDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BoardDao {

    public void save(List<SquareDto> squareDtos, int gameId) {
        String query = "INSERT INTO board VALUES (?, ?, ?, ?, ?, ?) ";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            executeSave(squareDtos, preparedStatement, gameId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeSave(List<SquareDto> squareDtos, PreparedStatement preparedStatement, int gameId) throws SQLException {
        int boardId = generateId();
        for (SquareDto squareDto : squareDtos) {
            preparedStatement.setInt(1, boardId);
            preparedStatement.setInt(2, gameId);
            preparedStatement.setInt(3, squareDto.getX_pos());
            preparedStatement.setInt(4, squareDto.getY_pos());
            preparedStatement.setString(5, squareDto.getRole());
            preparedStatement.setString(6, squareDto.getTeam());
            preparedStatement.executeUpdate();
        }
    }

    public void update(List<SquareDto> squareDtos, int gameId) {
        String query = "UPDATE board SET role = ?, team = ? " +
                "WHERE game_id = ? AND x_pos = ? AND y_pos = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            executeUpdate(squareDtos, preparedStatement, gameId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeUpdate(List<SquareDto> squareDtos, PreparedStatement preparedStatement, int gameId) throws SQLException {
        for (SquareDto squareDto : squareDtos) {
            preparedStatement.setString(1, squareDto.getRole());
            preparedStatement.setString(2, squareDto.getTeam());
            preparedStatement.setInt(3, gameId);
            preparedStatement.setInt(4, squareDto.getX_pos());
            preparedStatement.setInt(5, squareDto.getY_pos());
            preparedStatement.executeUpdate();
        }
    }

    public Board findByGameId(int gameId) {
        String query = "SELECT x_pos, y_pos, role, team FROM board WHERE game_id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return convertToBoard(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM board WHERE game_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Board convertToBoard(ResultSet resultSet) throws SQLException {
        Map<Position, Piece> squares = new HashMap<>(64);
        while (resultSet.next()) {
            int x_pos = resultSet.getInt("x_pos");
            int y_pos = resultSet.getInt("y_pos");
            String role = resultSet.getString("role");
            String team = resultSet.getString("team");
            Position position = Position.of(x_pos, y_pos);
            Piece piece = PieceFactory.of(Role.valueOf(role), Team.valueOf(team));
            squares.put(position, piece);
        }
        return new Board(squares);
    }

    private int generateId() {
        List<Integer> allIds = findAllIds();
        if (allIds.isEmpty()) {
            return 1;
        }
        return Collections.max(allIds) + 1;
    }

    private List<Integer> findAllIds() {
        String query = "SELECT distinct(id) FROM board";
        List<Integer> allBoardIds = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                allBoardIds.add(id);
            }
            return allBoardIds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
