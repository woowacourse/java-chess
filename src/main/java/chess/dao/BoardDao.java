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

    public void create(final ChessGameDto chessGameDto) {
        if (findTurnByName(chessGameDto.getName()) != null) {
            return;
        }
        try (Connection connection = getConnection()) {
            createBoard(chessGameDto, connection);
            createSquares(chessGameDto, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createBoard(ChessGameDto chessGameDto, Connection connection) throws SQLException {
        final String board_sql = "INSERT into board (name, turn) values (?, ?);";
        PreparedStatement board_statement = connection.prepareStatement(board_sql);
        board_statement.setString(1, chessGameDto.getName());
        board_statement.setString(2, chessGameDto.getChessGame().getTurn().name());
        board_statement.executeUpdate();
    }

    private void createSquares(ChessGameDto chessGameDto, Connection connection) throws SQLException {
        final String squares_sql = "INSERT into squares (board_name, position, piece) values (?, ?, ?);";
        for (Entry<String, String> entry : chessGameDto.getSquaresOfDB().entrySet()) {
            PreparedStatement squares_statement = connection.prepareStatement(squares_sql);
            squares_statement.setString(1, chessGameDto.getName());
            squares_statement.setString(2, entry.getKey());
            squares_statement.setString(3, entry.getValue());
            squares_statement.executeUpdate();
        }
    }

    public ChessGameDto findByName(String name) {
        Team turn = findTurnByName(name);
        Map<Position, Piece> squares = findSquaresByName(name);
        return new ChessGameDto(name, new ChessGame(turn, new Board(squares)));
    }

    private Team findTurnByName(String name) {
        try (Connection connection = getConnection()) {
            return getTeam(name, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Team getTeam(String name, Connection connection) throws SQLException {
        final String sql = "select * from board where name = ?";
        final PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        final ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            return null;
        }
        return Team.valueOf(resultSet.getString("turn"));
    }

    private Map<Position, Piece> findSquaresByName(String name) {
        Map<Position, Piece> squares = new HashMap<>();
        try (Connection connection = getConnection()) {
            if (addToSquares(name, squares, connection)) {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return squares;
    }

    private boolean addToSquares(String name, Map<Position, Piece> squares, Connection connection) throws SQLException {
        final String sql = "select piece from squares where board_name = ? AND position = ?";
        for (Position position : BoardDto.getAblePositions()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, position.toString());
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return true;
            }
            String pieceStr = resultSet.getString("piece");
            squares.put(position, PieceDto.getPiece(pieceStr));
        }
        return false;
    }

    public void save(final ChessGameDto chessGameDto) {
        saveBoard(chessGameDto);
        saveSquares(chessGameDto);
    }

    private void saveBoard(final ChessGameDto chessGameDto) {
        try (Connection connection = getConnection()) {
            final String sql = "update board set turn = ? where name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, chessGameDto.getChessGame().getTurn().name());
            statement.setString(2, chessGameDto.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveSquares(ChessGameDto chessGameDto) {
        try (Connection connection = getConnection()) {
            final String sql = "update squares set piece = ? where board_name = ? AND position = ?";
            for (Position position : BoardDto.getAblePositions()) {
                final PreparedStatement statement = connection.prepareStatement(sql);
                Piece piece = chessGameDto.getSquares().get(position);
                statement.setString(1, piece.getName().getValue(piece.getTeam()));
                statement.setString(2, chessGameDto.getName());
                statement.setString(3, position.toString());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(final String name) {
        deleteSquares(name);
        deleteBoard(name);
    }

    private void deleteBoard(final String name) {
        try (Connection connection = getConnection()) {
            final String sql = "DELETE FROM board WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSquares(String name) {
        try (Connection connection = getConnection()) {
            final String sql = "DELETE FROM squares WHERE board_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
