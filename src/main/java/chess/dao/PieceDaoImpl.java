package chess.dao;

import static chess.util.JdbcConnector.getConnection;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PieceDaoImpl implements PieceDao {

    private static final PieceDaoImpl INSTANCE = new PieceDaoImpl();

    public static PieceDaoImpl getInstance() {
        return INSTANCE;
    }

    private PieceDaoImpl() {
    }

    @Override
    public void saveAllPieces(final Map<Position, Piece> board) {
        final String sql = "insert into piece (position, team, name) values (?, ?, ?)";
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            saveAllPieces(board, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveAllPieces(final Map<Position, Piece> board, final PreparedStatement statement)
            throws SQLException {
        for (Entry<Position, Piece> entry : board.entrySet()) {
            statement.setString(1, entry.getKey().toString());
            statement.setString(2, entry.getValue().getTeam());
            statement.setString(3, entry.getValue().getName());
            statement.executeUpdate();
        }
    }

    @Override
    public Map<String, PieceDto> findAllPieces() {
        final String sql = "select position, team, name from piece";
        final Map<String, PieceDto> allPieces = new HashMap<>();

        try (final PreparedStatement statement = getConnection().prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            putAllPieces(allPieces, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPieces;
    }

    private void putAllPieces(final Map<String, PieceDto> all, final ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            all.put(resultSet.getString("position"),
                    new PieceDto(
                            resultSet.getString("team"),
                            resultSet.getString("name")
                    )
            );
        }
    }

    @Override
    public void removePieceByPosition(final String position) {
        final String sql = "delete from piece where position = (?)";
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePiece(final String position, final Piece piece) {
        final String sql = "insert into piece (position, team, name) values (?, ?, ?) "
                + "on duplicate key update team = (?), name = (?)";
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, position);
            statement.setString(2, piece.getTeam());
            statement.setString(3, piece.getName());
            statement.setString(4, piece.getTeam());
            statement.setString(5, piece.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAllPieces() {
        final String sql = "TRUNCATE piece";
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
