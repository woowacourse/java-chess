package chess.dao;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.factory.PieceFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PiecesDao {

    private static final String URL = "jdbc:mysql://localhost:3308/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final int COLUMN = 0;
    private static final int ROW = 1;

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Map<Position, Piece> getPieces(final int boardId) {
        final Connection connection = getConnection();
        final String sql = "SELECT ps.position, p.symbol, p.score, p.color, p.image_url FROM pieces AS ps\n"
                + "    LEFT JOIN piece p on ps.piece_id = p.id\n"
                + "WHERE board_id=?;";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, boardId);
            final ResultSet resultSet = statement.executeQuery();
            final Map<Position, Piece> pieces = new HashMap<>();
            while (resultSet.next()) {
                final Position position = createPosition(resultSet);
                final Piece piece = createPiece(resultSet);
                pieces.put(position, piece);
            }
            return pieces;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Piece createPiece(final ResultSet resultSet) throws SQLException {
        final String symbol = resultSet.getString("symbol");
        final String color = resultSet.getString("color");
        return PieceFactory.createPiece(PieceType.of(symbol), Color.valueOf(color));
    }

    private Position createPosition(final ResultSet resultSet) throws SQLException {
        final String rawPosition = resultSet.getString("position");
        return new Position(Column.from(rawPosition.substring(ROW, 1)),
                Row.from(Integer.parseInt(rawPosition.substring(COLUMN, 2))));
    }

    public void changePosition(final String currentPosition, final String targetPosition, final int boardId) {
        final Connection connection = getConnection();
        final String sql = "UPDATE pieces SET position=? WHERE position=? AND board_id=?;";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, targetPosition);
            statement.setString(2, currentPosition);
            statement.setInt(3, boardId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePiece(final String position) {
        final Connection connection = getConnection();
        final String sql = "DELETE FROM pieces WHERE position=?;";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
