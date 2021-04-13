package chess.domain.dao;

import chess.domain.dto.PlayerDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {
    private DBConnector dbConnector = new DBConnector();

    public boolean isExistedPlayer(String playerName) {
        String query = "SELECT * FROM player WHERE (player_name = ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return false;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addPlayer(String playerName) {
        String query = "INSERT INTO player(player_name) VALUES(?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PlayerDto> allPlayers() {
        String query = "SELECT * From player";
        List<PlayerDto> playerDtos = new ArrayList<>();
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            insertPlayerDto(rs, playerDtos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerDtos;
    }

    private void insertPlayerDto(ResultSet resultSet, List<PlayerDto> playerDtos)
        throws SQLException {
        while (resultSet.next()) {
            PlayerDto playerDto = new PlayerDto(
                resultSet.getInt("player_id"),
                resultSet.getString("player_name")
            );
            playerDtos.add(playerDto);
        }
    }
}
