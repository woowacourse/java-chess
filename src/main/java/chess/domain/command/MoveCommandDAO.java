package chess.domain.command;

import static chess.web.persistence.DBConnectionUtils.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveCommandDAO {

    public void addMoveCommand(Move moveCommand) throws SQLException {
        String query = "INSERT INTO move_command (game_id, source_position, target_position) VALUES (?, ?, ?)";

        final Connection con = getConnection();
        final PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, moveCommand.getGameId());
        pstmt.setString(2, moveCommand.getSource());
        pstmt.setString(3, moveCommand.getTarget());

        try (con; pstmt) {
            pstmt.executeUpdate();
        }
    }

    public List<Command> findCommandsByGameId(String gameId) throws SQLException {
        String query = "SELECT * FROM chess_game game JOIN move_command cmd on game.id = cmd.game_id WHERE is_end=false AND game.id=(?) ORDER BY cmd.created_at";

        final Connection con = getConnection();
        final PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, gameId);
        final ResultSet rs = pstmt.executeQuery();

        try (con; pstmt; rs) {
            return commandsFromResultSet(rs);
        }
    }

    private List<Command> commandsFromResultSet(ResultSet rs) throws SQLException {
        List<Command> commands = new ArrayList<>();
        while (rs.next()) {
            String source = rs.getString("cmd.source_position");
            String target = rs.getString("cmd.target_position");
            commands.add(new Move(source, target));
        }
        return commands;
    }
}
