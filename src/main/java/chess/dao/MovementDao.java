package chess.dao;

import chess.domain.Color;
import chess.domain.Position;
import chess.dto.Command;
import chess.dto.MovementDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovementDao {
    public long save(final Command command, final Color color, final long gameId) {
        final String sql = "INSERT INTO MOVEMENT(square_source, square_target, square_color, game_id) VALUES(?, ?, ?, ?)";
        try (
                final Connection connection = ConnectionUtil.connection();
                final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, command.getSource().toString());
            ps.setString(2, command.getTarget().toString());
            ps.setString(3, color.name());
            ps.setLong(4, gameId);
            ps.executeUpdate();

            final ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public List<MovementDto> findAllBy(final Long gameId) {
        final String sql = "SELECT * FROM MOVEMENT WHERE game_id = ? ORDER BY id ASC";
        final List<MovementDto> moveMappers = new ArrayList<>();

        try (
                final Connection connection = ConnectionUtil.connection();
                final PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setLong(1, gameId);
            final ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final MovementDto moveMapper = rowMapper(rs);
                moveMappers.add(moveMapper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moveMappers;
    }

    private MovementDto rowMapper(final ResultSet rs) throws SQLException {
        final String sourceValue = rs.getString("square_source");
        final String targetValue = rs.getString("square_target");
        final String colorValue = rs.getString("square_color");

        final Position source = Position.from(sourceValue);
        final Position target = Position.from(targetValue);
        final Color color = Color.valueOf(colorValue);

        return MovementDto.create(source, target, color);
    }

    public boolean deleteAll() {
        final String sql = "DELETE FROM MOVEMENT WHERE 1=1";
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
