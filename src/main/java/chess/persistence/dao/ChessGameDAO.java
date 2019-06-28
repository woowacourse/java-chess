package chess.persistence.dao;

import chess.persistence.dao.core.JDBCTemplate;
import chess.persistence.dto.ChessGameDTO;

import java.util.ArrayList;
import java.util.List;

public class ChessGameDAO {
    private static final JDBCTemplate JDBC_TEMPLATE = JDBCTemplate.getInstance();

    private ChessGameDAO() {
    }

    public static ChessGameDAO getInstance() {
        return ChessGameDAOHandler.INSTANCE;
    }

    public long addGameStatus(ChessGameDTO chessGameDTO) {
        String query = "INSERT INTO game(game_status, last_user) VALUES (?,?)";
        List<Object> params = new ArrayList<>();
        params.add(chessGameDTO.getGameStatus());
        params.add(chessGameDTO.getLastUser());

        return JDBC_TEMPLATE.excuteUpdate(query, params);
    }

    public long updateGameStatus(ChessGameDTO chessGameDTO) {
        String query = "UPDATE game SET game_status=?, last_user=? WHERE game_id=?";
        List<Object> params = new ArrayList<>();
        params.add(chessGameDTO.getGameStatus());
        params.add(chessGameDTO.getLastUser());
        params.add(chessGameDTO.getGameId());

        return JDBC_TEMPLATE.excuteUpdate(query, params);
    }

    public ChessGameDTO findByMaxGameId() {
        String query = "SELECT * FROM chess.game ORDER BY game_id DESC LIMIT 1;";
        return JDBC_TEMPLATE.queryForObject(query, rs -> {
            ChessGameDTO chessGameDTO = new ChessGameDTO();
            if (!rs.next()) {
                chessGameDTO.setGameId(0);
                return chessGameDTO;
            }
            chessGameDTO.setGameId(rs.getInt("game_id"));
            chessGameDTO.setGameStatus(rs.getBoolean("game_status"));
            chessGameDTO.setLastUser(rs.getString("last_user"));

            return chessGameDTO;
        });
    }

    private static class ChessGameDAOHandler {
        static final ChessGameDAO INSTANCE = new ChessGameDAO();
    }
}
