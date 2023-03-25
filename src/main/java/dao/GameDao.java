package dao;

import chessgame.domain.Board;
import chessgame.domain.Game;
import chessgame.domain.piece.Piece;
import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;
import chessgame.domain.state.State;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static dao.ConnectionGenerator.getConnection;

public class GameDao {

    public void save(Board board, String gameName, State turn) {
        Map<Point, Piece> boardMap = board.getBoard();
        Connection connection = getConnection();
        try {
            insertGame(gameName, turn, connection);
            insertBoard(boardMap, gameName, connection);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertGame(String gameName, State turn, Connection connection) throws SQLException {
        final String query = "insert into game (name, team_turn) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, gameName);
        preparedStatement.setString(2, turn.getClass().getSimpleName());
        preparedStatement.execute();
        preparedStatement.close();
    }

    private void insertBoard(Map<Point, Piece> boardMap, String gameName, Connection connection) throws SQLException {
        final String query = "insert into board (board_name, piece_file, piece_rank, piece_type, piece_team) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (Point point : boardMap.keySet()) {
            preparedStatement.setString(1, gameName);
            preparedStatement.setString(2, point.getFile().toString());
            preparedStatement.setString(3, point.getRank().toString());
            preparedStatement.setString(4, boardMap.get(point).toString());
            preparedStatement.setString(5, boardMap.get(point).team().toString());
            preparedStatement.execute();
        }
        preparedStatement.close();
    }

    public Game read(String gameName) {
        final String query = "SELECT * FROM Board where board_name = ?";

        Connection connection = getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return makeGame(makeBoard(resultSet), gameName);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Point, Piece> makeBoard(ResultSet resultSet) throws SQLException {
        Map<Point, Piece> board = new HashMap<>();
        while (resultSet.next()) {
            File file = File.valueOf(resultSet.getString("piece_file"));
            Rank rank = Rank.valueOf(resultSet.getString("piece_rank"));
            String name = resultSet.getString("piece_type");
            String team = resultSet.getString("piece_team");
            Piece piece = PieceConveter.getPiece(name, team);

            board.put(Point.of(file, rank), piece);
        }
        return board;
    }

    private Game makeGame(Map<Point, Piece> board, String gameName) {
        if (board.isEmpty()) {
            return null;
        }
        return new Game(new Board(board), gameName);
    }

    public void remove(String gameName) {
        final String query = "delete from game where name = ?";

        Connection connection = getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameName);
            preparedStatement.execute();
            connection.close();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String findTurnByGame(String gameName) {
        final String query = "select team_turn from game where name = ?";

        Connection connection = getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("team_turn");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
