package chess.domain.game;

import static chess.web.persistence.DBConnectionUtils.closeConnection;
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
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, chessGame.getName());

        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        rs.next();
        String gameId = rs.getString(1);
        closeConnection(con);
        return gameId;
    }

    public List<ChessGameDTO> findActiveGames() throws SQLException {
        String query = "SELECT * FROM chess_game WHERE is_end = false";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        List<ChessGameDTO> gameDTOS = new ArrayList<>();
        while (rs.next()) {
            ChessGameDTO gameDTO = new ChessGameDTO(
                    rs.getString("id"),
                    rs.getString("name"));
            gameDTOS.add(gameDTO);
        }

        closeConnection(con);
        return gameDTOS;
    }
}
