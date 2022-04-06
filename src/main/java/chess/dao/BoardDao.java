package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import chess.dto.BoardDto;
import chess.dto.ChessGameDto;
import chess.dto.PieceDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void executeQuery(String sql, List<String> datas, Connection connection) throws SQLException {
        PreparedStatement statement = getStatement(connection, sql, datas);
        statement.executeUpdate();
    }

    private ResultSet getResultSet(String sql, List<String> datas, Connection connection) throws SQLException {
        PreparedStatement statement = getStatement(connection, sql, datas);
        return statement.executeQuery();
    }

    private PreparedStatement getStatement(Connection connection, String sql, List<String> datas) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        int index = 1;
        for (String data : datas) {
            statement.setString(index, data);
            index++;
        }
        return statement;
    }

    public void create(final ChessGameDto game) {
        if (isNameIn(game.getName())) {
            return;
        }
        try (Connection connection = getConnection()) {
            executeQuery("INSERT into board (name, turn) values (?, ?);",
                    List.of(game.getName(), game.getTurn()), connection);
            createSquares(game, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isNameIn(String name) {
        try (Connection connection = getConnection()) {
            ResultSet set = getResultSet("select * from board where name = ?",
                    List.of(name), connection);
            return set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createSquares(ChessGameDto game, Connection connection) throws SQLException {
        for (Entry<String, String> entry : game.getSquaresOfDB().entrySet()) {
            executeQuery("INSERT into squares (board_name, position, piece) values (?, ?, ?);",
                    List.of(game.getName(), entry.getKey(), entry.getValue()), connection);
        }
    }

    public ChessGameDto findByName(String name) {
        try (Connection connection = getConnection()) {
            Map<Position, Piece> squares = getSquares(name, connection);
            return new ChessGameDto(name, new ChessGame(getTurn(name, connection), new Board(squares)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Team getTurn(String name, Connection connection) throws SQLException {
        final ResultSet resultSet = getResultSet("select * from board where name = ?",
                List.of(name), connection);
        if (!resultSet.next()) {
            return null;
        }
        return Team.valueOf(resultSet.getString("turn"));
    }

    private Map<Position, Piece> getSquares(String name, Connection connection) throws SQLException {
        Map<Position, Piece> squares = new HashMap<>();
        for (Position position : BoardDto.getAblePositions()) {
            final ResultSet resultSet = getResultSet("select piece from squares where board_name = ? AND position = ?",
                    List.of(name, position.toString()), connection);
            if (!resultSet.next()) {
                return null;
            }
            squares.put(position, PieceDto.getPiece(resultSet.getString("piece")));
        }
        return squares;
    }

    public void save(final ChessGameDto game) {
        try (Connection connection = getConnection()) {
            executeQuery("update board set turn = ? where name = ?",
                    List.of(game.getTurn(), game.getName()), connection);
            updateSquares(game, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateSquares(ChessGameDto game, Connection connection) throws SQLException {
        for (Position position : BoardDto.getAblePositions()) {
            Piece piece = game.findPiece(position);
            executeQuery("update squares set piece = ? where board_name = ? AND position = ?",
                    List.of(piece.getName().getValue(piece.getTeam()), game.getName(), position.toString()),
                    connection);
        }
    }

    public void delete(final String name) {
        try (Connection connection = getConnection()) {
            executeQuery("DELETE FROM squares WHERE board_name = ?", List.of(name), connection);
            executeQuery("DELETE FROM board WHERE name = ?", List.of(name), connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
