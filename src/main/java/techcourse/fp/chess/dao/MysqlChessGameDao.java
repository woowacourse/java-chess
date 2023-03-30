package techcourse.fp.chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Turn;
import techcourse.fp.chess.dto.response.ChessGameInfo;

public class MysqlChessGameDao implements ChessGameDao {

    public int save(final String name, final Color turn) {
        try (final Connection connection = ConnectionProvider.getConnection()) {
            final String query = "INSERT INTO chess_game(name, turn) VALUES (?,?);";
            final PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, turn.name());
            ps.executeUpdate();

            return findChessGameId(ps);
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private int findChessGameId(final PreparedStatement ps) throws SQLException {
        final ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        throw new IllegalStateException();
    }

    @Override
    public Turn findTurn(final long chessGameId) {
        try (final Connection connection = ConnectionProvider.getConnection()) {
            final String query = "SELECT turn FROM chess_game WHERE id = ?";

            final PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, chessGameId);
            final ResultSet resultSet = ps.executeQuery();

            String color = null;

            if (resultSet.next()) {
                color = resultSet.getString(1);
            }

            return Turn.createByStartTurn(Color.createByName(color));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public List<ChessGameInfo> findInfos() {
        final String query = "SELECT * FROM chess_game;";

        try (final Connection connection = ConnectionProvider.getConnection()) {
            final PreparedStatement ps = connection.prepareStatement(query);
            final ResultSet resultSet = ps.executeQuery();

            List<ChessGameInfo> chessGameInfos = new ArrayList<>();

            while (resultSet.next()) {
                final long id = resultSet.getLong("id");
                final String name = resultSet.getString("name");
                final String turn = resultSet.getString("turn");
                final Timestamp createAt = resultSet.getTimestamp("created_at");

                chessGameInfos.add(new ChessGameInfo(id, name, turn, createAt.toLocalDateTime()));
            }
            return chessGameInfos;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
