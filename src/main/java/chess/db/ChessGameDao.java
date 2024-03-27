package chess.db;

import chess.domain.piece.character.Team;
import chess.exception.InvalidGameRoomException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {
    private final ConnectionGenerator connectionGenerator;

    ChessGameDao() {
        this(new ProductionConnectionGenerator());
    }

    ChessGameDao(ConnectionGenerator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    public void add(String roomName, Team currentTeam) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO chess_game(room_name, current_team) VALUES (?, ?)");

            statement.setString(1, roomName);
            statement.setString(2, currentTeam.name());
            statement.execute();
        } catch (SQLException e) {
            throw new InvalidGameRoomException("중복된 방 이름이 존재하거나, 방이름이 17자 이상입니다.");
        }
    }

    public Team findCurrentTeamByRoomName(String roomName) {
        try (final Connection connection = connectionGenerator.getConnection();) {
            final PreparedStatement statement = connection.prepareStatement(
                    "SELECT current_team FROM chess_game WHERE room_name = ?");

            statement.setString(1, roomName);
            final ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return Team.valueOf(resultSet.getString("current_team"));
        } catch (SQLException e) {
            throw new InvalidGameRoomException("존재하지 않는 방 이름입니다.");
        }
    }

    public void update(Team currentTeam, String roomName) throws SQLException {
        final Connection connection = connectionGenerator.getConnection();
        final PreparedStatement statement = connection.prepareStatement(
                "UPDATE chess_game SET current_team = ? WHERE room_name = ?");

        statement.setString(1, currentTeam.name());
        statement.setString(2, roomName);

        statement.execute();
    }

    public void delete(String roomName) throws SQLException {
        final Connection connection = connectionGenerator.getConnection();
        final PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM chess_game WHERE room_name = ?");
        statement.setString(1, roomName);

        statement.execute();
    }
}
