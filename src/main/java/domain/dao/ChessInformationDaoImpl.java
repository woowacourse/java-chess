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

public class ChessInformationDaoImpl implements ChessInformationDao {

    @Override
    public Map<Location, Piece> find(final String id, final Connection connection) throws SQLException {
        final String query = "select * from boardInformation where board_id = ?";
        final PreparedStatement preparedStatement = connection.prepareStatement(query);
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

    private Color makeColor(final ResultSet resultSet) throws SQLException {
        final String colorName = resultSet.getString("piece_color");
        return Color.findByName(colorName);
    }

    private PieceType makePieceType(final ResultSet resultSet) throws SQLException {
        final String typeName = resultSet.getString("piece_type");
        return PieceType.findByName(typeName);
    }

    @Override
    public Integer count(final String id, final Connection connection) throws SQLException {
        final String countBoardQuery = "select count(*) from board where board_id = ?";
        final PreparedStatement preparedStatement = connection.prepareStatement(countBoardQuery);
        preparedStatement.setString(1, id);
        final ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("count(*)");
        }
        return 0;
    }

    @Override
    public Void insert(final Map<Location, Piece> board, final String boardId, final Connection connection)
        throws SQLException {
        insertBoard(boardId, connection);
        insertBoardInformation(board, boardId, connection);
        return null;
    }

    private void insertBoard(final String boardId, final Connection connection) throws SQLException {
        final String query = "insert into board values (?)";
        final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, boardId);
        preparedStatement.execute();
    }

    private void insertBoardInformation(final Map<Location, Piece> board, final String boardId, final Connection connection) throws SQLException {
        final String query = "insert into boardInformation "
            + "(column_, row_, piece_type, piece_color, board_id) "
            + "values(?, ?, ?, ?, ?)";
        addBoardInformation(board, boardId, query, connection);
    }

    @Override
    public Integer update(final Map<Location, Piece> board, final String boardId, final Connection connection)
        throws SQLException {
        final String query = "update boardInformation piece_type = ?, piece_color = ? "
            + "where board_id = ? and column_ = ? and row_ = ?";
        return addBoardInformation(board, boardId, query, connection);
    }

    private int addBoardInformation(final Map<Location, Piece> board, final String boardId, final String query,
        final Connection connection)
        throws SQLException {
        int count = 0;
        for (Location location : board.keySet()) {
            final Piece piece = board.get(location);
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, location.getColumn());
            preparedStatement.setInt(2, location.getRow());
            preparedStatement.setString(3, piece.getPieceType().name());
            preparedStatement.setString(4, piece.getColor().name());
            preparedStatement.setString(5, boardId);
            count += preparedStatement.executeUpdate();
        }
        return count;
    }
}
