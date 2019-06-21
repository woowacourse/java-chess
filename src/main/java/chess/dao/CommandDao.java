package chess.dao;

import chess.config.DbConnector;
import chess.dto.CommandDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandDao {

    private final DbConnector dbConnector;

    private CommandDao(final DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public static CommandDao from(final DbConnector dbConnector) {
        return new CommandDao(dbConnector);
    }


    public void add(final CommandDto commandDto) {
        String sql = "INSERT INTO command (origin, target, round, room_id) VALUES(?,?,?,?)";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, commandDto.getTarget());
            ps.setString(2, commandDto.getOrigin());
            ps.setLong(3, commandDto.getRound());
            ps.setLong(4, commandDto.getRoom_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<CommandDto> findByRoomId(final long room_id) {
        ArrayList<CommandDto> commandDtos = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, room_id);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CommandDto commandDto = new CommandDto();
                commandDto.setOrigin(rs.getString("origin"));
                commandDto.setTarget(rs.getString("target"));
                commandDto.setRound(rs.getLong("round"));
                commandDtos.add(commandDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandDtos;
    }

    private PreparedStatement createPreparedStatement(final Connection conn, final long room_id) throws SQLException {
        String sql = "SELECT * FROM command WHERE room_id = ? ORDER BY round";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, room_id);
        return ps;
    }

}
