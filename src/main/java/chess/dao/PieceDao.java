package chess.dao;

import chess.game.Position;
import chess.piece.Piece;
import chess.piece.PieceStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PieceDao {
    private static final String URL = "jdbc:mysql://localhost:3307/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void save(final Long boardId, final String position, final Piece piece) {
        final Connection connection = getConnection();
        final String sql = "insert into piece (id, position, board_id, name, color) values (NULL, ?, ?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setLong(2, boardId);
            statement.setString(3, piece.getName().name());
            statement.setString(4, piece.getColor().name());
            statement.executeUpdate();
        } catch (final SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    public void saveAll(final Long boardId, final Map<Position, Piece> pieces) {
        final Connection connection = getConnection();
        final String sql = "insert into piece (id, position, board_id, name, color) values (NULL, ?, ?, ?, ?)";
        try {
            saveBoard(boardId, pieces, connection.prepareStatement(sql));
        } catch (final SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    public Map<Position, Piece> findAllByBoardId(final Long boardId) {
        final Connection connection = getConnection();
        final String sql = "select * from piece where board_id = (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, boardId);
            return getBoard(statement);
        } catch (final SQLException e) {
            logger.warn(e.getMessage());
        }
        return Collections.emptyMap();
    }

    public void deleteByPositionAndBoardId(final String position, final Long boardId) {
        final Connection connection = getConnection();
        final String sql = "delete from piece where position = (?) and board_id = (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setLong(2, boardId);
            statement.executeUpdate();
        } catch (final SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    private void saveBoard(final Long boardId, final Map<Position, Piece> board, final PreparedStatement statement) throws SQLException {
        for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
            final Position position = entry.getKey();
            statement.setString(1, position.getColumn().name() + position.getRow().getValue());
            statement.setLong(2, boardId);
            statement.setString(3, entry.getValue().getName().name());
            statement.setString(4, entry.getValue().getColor().name());
            statement.executeUpdate();
        }
    }

    private Piece getPiece(final ResultSet resultSet) throws SQLException {
        return PieceStorage.valueOf(
                resultSet.getString("name"),
                resultSet.getString("color")
        );
    }

    private Map<Position, Piece> getBoard(final PreparedStatement statement) throws SQLException {
        final ResultSet resultSet = statement.executeQuery();
        final Map<Position, Piece> board = new HashMap<>();
        while (resultSet.next()) {
            board.put(getPosition(resultSet), getPiece(resultSet));
        }
        return board;
    }

    private Position getPosition(final ResultSet resultSet) throws SQLException {
        return Position.of(resultSet.getString("position"));
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
