package domain.dao;

import common.JdbcContext;
import domain.Location;
import domain.config.PieceSetting;
import domain.piece.Piece;
import domain.type.Color;
import domain.type.PieceType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class PieceDaoImpl implements PieceDao {

    private static final String FIND_PIECE_SQL = "select * from piece where `board_id` = ?";
    private static final String INSERT_PIECE_SQL = "insert into piece "
        + "(`column`, `row`, `piece_type`, `piece_color`, `board_id`) "
        + "values(?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL =
        "update piece set `piece_type` = ?, `piece_color` = ? "
            + "where `board_id` = ? and `column` = ? and `row` = ?";
    private final JdbcContext jdbcContext;

    public PieceDaoImpl(final JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    @Override
    public Map<Location, Piece> findAllByBoardId(final String boardId) {
        return jdbcContext.workWithTransaction(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(FIND_PIECE_SQL);
            preparedStatement.setString(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            return makeBoard(resultSet);
        });
    }

    @Override
    public Void insert(final Map<Location, Piece> board, final String boardId) {
        return jdbcContext.workWithTransaction(connection -> {
            for (final Location location : board.keySet()) {
                final Piece piece = board.get(location);
                final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PIECE_SQL);
                preparedStatement.setInt(1, location.getColumn());
                preparedStatement.setInt(2, location.getRow());
                preparedStatement.setString(3, piece.getPieceType().name());
                preparedStatement.setString(4, piece.getColor().name());
                preparedStatement.setString(5, boardId);
                preparedStatement.execute();
            }
            return null;
        });
    }

    @Override
    public Integer update(final Map<Location, Piece> board, final String boardId) {
        return jdbcContext.workWithTransaction(connection -> {
            int count = 0;
            for (final Location location : board.keySet()) {
                final Piece piece = board.get(location);
                final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
                preparedStatement.setString(1, piece.getPieceType().name());
                preparedStatement.setString(2, piece.getColor().name());
                preparedStatement.setString(3, boardId);
                preparedStatement.setInt(4, location.getColumn());
                preparedStatement.setInt(5, location.getRow());
                count += preparedStatement.executeUpdate();
            }
            return count;
        });
    }

    private Map<Location, Piece> makeBoard(final ResultSet resultSet) throws SQLException {
        final Map<Location, Piece> board = new HashMap<>();
        while (resultSet.next()) {
            final Location location = makeLocation(resultSet);
            final PieceType pieceType = makePieceType(resultSet);
            final Color color = makeColor(resultSet);
            final Piece piece = PieceSetting.findByPieceTypeAndColor(pieceType, color);
            board.put(location, piece);
        }
        return board;
    }

    private Location makeLocation(final ResultSet resultSet) throws SQLException {
        return Location.of(resultSet.getInt("column"), resultSet.getInt("row"));
    }

    private PieceType makePieceType(final ResultSet resultSet) throws SQLException {
        final String typeName = resultSet.getString("piece_type");
        return PieceType.findByName(typeName);
    }

    private Color makeColor(final ResultSet resultSet) throws SQLException {
        final String colorName = resultSet.getString("piece_color");
        return Color.findByName(colorName);
    }
}
