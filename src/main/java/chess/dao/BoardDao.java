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
    private static final String BOARD_CREATE_SQL = "INSERT into board (name, turn) values (?, ?);";
    private static final String FIND_BOARD_SQL = "select * from board where name = ?";
    private static final String SQUARES_CREATE_SQL = "INSERT into squares (board_name, position, piece) values (?, ?, ?);";
    private static final String FINE_PIECE_SQL = "select piece from squares where board_name = ? AND position = ?";
    private static final String BOARD_UPDATE_SQL = "update board set turn = ? where name = ?";
    private static final String SQUARES_UPDATE_SQL = "update squares set piece = ? where board_name = ? AND position = ?";
    private static final String SQUARES_DELETE_SQL = "DELETE FROM squares WHERE board_name = ?";
    private static final String BOARD_DELETE_SQL = "DELETE FROM board WHERE name = ?";
    private static final String DB_NOT_FOUND_ERROR_MESSAGE = "해당 이름의 데이터가 DB에 없습니다.";

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

    private PreparedStatement getStatement(Connection connection, String sql, List<String> data) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < data.size(); i++) {
            statement.setString(i + 1, data.get(i));
        }
        return statement;
    }

    private void executeQuery(String sql, List<String> data, Connection connection) throws SQLException {
        getStatement(connection, sql, data).executeUpdate();
    }

    private ResultSet getResultSet(String sql, List<String> data, Connection connection) throws SQLException {
        return getStatement(connection, sql, data).executeQuery();
    }

    public void create(final ChessGameDto game) {
        if (isNameIn(game.getName())) {
            return;
        }
        try (Connection connection = getConnection()) {
            executeQuery(BOARD_CREATE_SQL, List.of(game.getName(), game.getTurn()), connection);
            createSquares(game, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isNameIn(String name) {
        try (Connection connection = getConnection()) {
            ResultSet set = getResultSet(FIND_BOARD_SQL, List.of(name), connection);
            return set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createSquares(ChessGameDto game, Connection connection) throws SQLException {
        for (Entry<String, String> entry : game.getSquaresOfDB()) {
            executeQuery(SQUARES_CREATE_SQL, List.of(game.getName(), entry.getKey(), entry.getValue()), connection);
        }
    }

    public ChessGameDto findByName(String name) {
        try (Connection connection = getConnection()) {
            return new ChessGameDto(name,
                    new ChessGame(getTurn(name, connection), new Board(getSquares(name, connection))));
        } catch (SQLException e) {
            System.out.println(DB_NOT_FOUND_ERROR_MESSAGE);
        }
        return null;
    }

    private Team getTurn(String name, Connection connection) throws SQLException {
        final ResultSet result = getResultSet(FIND_BOARD_SQL, List.of(name), connection);
        result.next();
        return Team.valueOf(result.getString("turn"));
    }

    private Map<Position, Piece> getSquares(String name, Connection connection) throws SQLException {
        Map<Position, Piece> squares = new HashMap<>();
        for (String posStr : BoardDto.getAblePositions()) {
            ResultSet result = getResultSet(FINE_PIECE_SQL, List.of(name, posStr), connection);
            result.next();
            squares.put(Position.from(posStr), PieceDto.getPiece(result.getString("piece")));
        }
        return squares;
    }

    public void save(final ChessGameDto game) {
        try (Connection connection = getConnection()) {
            executeQuery(BOARD_UPDATE_SQL, List.of(game.getTurn(), game.getName()), connection);
            saveSquares(game, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveSquares(ChessGameDto game, Connection connection) throws SQLException {
        for (String posStr : BoardDto.getAblePositions()) {
            executeQuery(SQUARES_UPDATE_SQL, List.of(game.findPiece(posStr), game.getName(), posStr), connection);
        }
    }

    public void delete(final String name) {
        try (Connection connection = getConnection()) {
            executeQuery(SQUARES_DELETE_SQL, List.of(name), connection);
            executeQuery(BOARD_DELETE_SQL, List.of(name), connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
