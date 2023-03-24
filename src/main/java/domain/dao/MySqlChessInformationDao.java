package domain.dao;

import domain.Location;
import domain.config.PieceSetting;
import domain.piece.Piece;
import domain.type.Color;
import domain.type.PieceType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySqlChessInformationDao implements ChessInformationDao {

    private static final String FIND_BOARD_INFORMATION_SQL = "select * from boardInformation where board_id = ?";
    private static final String FIND_COLOR_SQL = "select color from board where board_id = ?";
    private static final String COUNT_BOARD_SQL = "select count(*) from board where board_id = ?";
    private static final String INSERT_BOARD_SQL = "insert into board (board_id, color) values (?, ?)";
    private static final String INSERT_BOARD_INFORMATION_SQL = "insert into boardInformation "
        + "(column_, row_, piece_type, piece_color, board_id) "
        + "values(?, ?, ?, ?, ?)";
    private static final String UPDATE_COLOR_SQL = "update board set color = ? where board_id = ?";
    private static final String UPDATE_BOARD_INFORMATION_SQL =
        "update boardInformation set piece_type = ?, piece_color = ? "
            + "where board_id = ? and column_ = ? and row_ = ?";

    @Override
    public Map<Location, Piece> find(final String id, final Connection connection) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(FIND_BOARD_INFORMATION_SQL);
        preparedStatement.setString(1, id);
        final ResultSet resultSet = preparedStatement.executeQuery();
        return makeBoard(resultSet);
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
        return Location.of(resultSet.getInt("column_"), resultSet.getInt("row_"));
    }

    private PieceType makePieceType(final ResultSet resultSet) throws SQLException {
        final String typeName = resultSet.getString("piece_type");
        return PieceType.findByName(typeName);
    }

    private Color makeColor(final ResultSet resultSet) throws SQLException {
        final String colorName = resultSet.getString("piece_color");
        return Color.findByName(colorName);
    }

    @Override
    public Color findColor(final String id, final Connection connection) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(FIND_COLOR_SQL);
        preparedStatement.setString(1, id);
        final ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            final String color = resultSet.getString("color");
            return Color.findByName(color);
        }
        return Color.NONE;
    }

    @Override
    public Integer count(final String id, final Connection connection) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(COUNT_BOARD_SQL);
        preparedStatement.setString(1, id);
        final ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("count(*)");
        }
        return 0;
    }

    @Override
    public Void insert(final Map<Location, Piece> board, final String boardId, final Color color,
        final Connection connection)
        throws SQLException {
        insertBoard(boardId, color, connection);
        insertBoardInformation(board, boardId, connection);
        return null;
    }

    private void insertBoard(final String boardId, final Color color, final Connection connection) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOARD_SQL);
        preparedStatement.setString(1, boardId);
        preparedStatement.setString(2, color.name());
        preparedStatement.execute();
    }

    private void insertBoardInformation(final Map<Location, Piece> board, final String boardId,
        final Connection connection) throws SQLException {
        for (final Location location : board.keySet()) {
            final Piece piece = board.get(location);
            final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOARD_INFORMATION_SQL);
            preparedStatement.setInt(1, location.getColumn());
            preparedStatement.setInt(2, location.getRow());
            preparedStatement.setString(3, piece.getPieceType().name());
            preparedStatement.setString(4, piece.getColor().name());
            preparedStatement.setString(5, boardId);
            preparedStatement.execute();
        }
    }

    @Override
    public Void update(final Map<Location, Piece> board, final String boardId, final Color color,
        final Connection connection) throws SQLException {
        updateBoard(color, boardId, connection);
        updateBoardInformation(board, boardId, connection);
        return null;
    }

    private void updateBoard(final Color color, final String boardId, final Connection connection) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COLOR_SQL);
        preparedStatement.setString(1, color.name());
        preparedStatement.setString(2, boardId);
        preparedStatement.executeUpdate();
    }

    private void updateBoardInformation(final Map<Location, Piece> board, final String boardId,
        final Connection connection) throws SQLException {
        for (final Location location : board.keySet()) {
            final Piece piece = board.get(location);
            final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOARD_INFORMATION_SQL);
            preparedStatement.setString(1, piece.getPieceType().name());
            preparedStatement.setString(2, piece.getColor().name());
            preparedStatement.setString(3, boardId);
            preparedStatement.setInt(4, location.getColumn());
            preparedStatement.setInt(5, location.getRow());
            preparedStatement.executeUpdate();
        }
    }
}
