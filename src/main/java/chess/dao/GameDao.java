package chess.dao;

import chess.domain.Color;
import chess.dto.GameDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameDao {
    public Optional<GameDto> findBy(final long gameId) {
        final String sql = "SELECT * FROM GAME WHERE id = ?";
        try (
                final Connection connection = ConnectionUtil.connection();
                final PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setLong(1, gameId);
            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                final boolean isEnd = rs.getBoolean("is_end");
                final Color lastPlayer = Color.valueOf(rs.getString("turn_color"));

                return Optional.of(new GameDto(gameId, isEnd, lastPlayer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<GameDto> findLatestGamesWithoutBy(final long gameId) {
        final List<GameDto> gameDtos = new ArrayList<>();
        final String sql = "SELECT * FROM GAME WHERE id != ? ORDER BY id DESC LIMIT 10";
        try (
                final Connection connection = ConnectionUtil.connection();
                final PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setLong(1, gameId);
            final ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final long gamdId = rs.getLong("id");
                final boolean isEnd = rs.getBoolean("is_end");
                final Color lastPlayer = Color.valueOf(rs.getString("turn_color"));

                gameDtos.add(new GameDto(gamdId, isEnd, lastPlayer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gameDtos;
    }

    public long save(final boolean isEnd, final Color lastPlayer) {
        final String sql = "INSERT INTO GAME(is_end, turn_color) VALUES(?, ?)";
        try (
                final Connection connection = ConnectionUtil.connection();
                final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setBoolean(1, isEnd);
            ps.setString(2, lastPlayer.name());
            ps.executeUpdate();

            final ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean deleteBy(final long gameId) {
        final String sql = "DELETE FROM GAME WHERE id = ?";
        try (
                final Connection connection = ConnectionUtil.connection();
                final PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setLong(1, gameId);
            return ps.executeUpdate() >= 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAll() {
        final String sql = "DELETE FROM GAME WHERE 1=1";
        try (
                final Connection connection = ConnectionUtil.connection();
                final PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            return ps.executeUpdate() >= 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
