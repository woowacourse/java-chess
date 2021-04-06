package chess.domain.game;

import static chess.web.persistence.DBConnectionUtils.getConnection;

import chess.web.dto.ChessGameDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDAO {

    public String addGame(ChessGame chessGame) throws SQLException {
        String query = "INSERT INTO chess_game (name) VALUES (?)";

        final Connection con = getConnection();
        final PreparedStatement pstmt = con
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, chessGame.getName());
        pstmt.executeUpdate();
        final ResultSet rs = pstmt.getGeneratedKeys();

        try (con; pstmt; rs) {
            rs.next();
            return rs.getString(1);
        }
    }

    public List<ChessGameDTO> findActiveGames() throws SQLException {
        String query = "SELECT * FROM chess_game WHERE is_end = false ORDER BY created_at";

        final Connection con = getConnection();
        final PreparedStatement pstmt = con.prepareStatement(query);
        final ResultSet rs = pstmt.executeQuery();

        try (con; pstmt; rs) {
            return activeGamesFromResultSet(rs);
        }
    }

    private List<ChessGameDTO> activeGamesFromResultSet(ResultSet rs) throws SQLException {
        List<ChessGameDTO> gameDTOs = new ArrayList<>();
        while (rs.next()) {
            ChessGameDTO gameDTO = new ChessGameDTO(
                    rs.getString("id"),
                    rs.getString("name"));
            gameDTOs.add(gameDTO);
        }
        return gameDTOs;
    }

    public void updateGameEnd(String gameId) throws SQLException {
        String query = "UPDATE chess_game SET is_end = true WHERE id = ?";

        final Connection con = getConnection();
        final PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, gameId);

        try (con; pstmt) {
            pstmt.executeUpdate();
        }
    }
}
