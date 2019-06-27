package chess.persistence.dao;

import chess.persistence.dao.core.JDBCTemplate;
import chess.persistence.dto.ChessGameDTO;

public class ChessGameDAO {
    private static final JDBCTemplate JDBC_TEMPLATE = JDBCTemplate.getInstance();

    private ChessGameDAO() {
    }

    public static ChessGameDAO getInstance() {
        return ChessGameDAOHandler.INSTANCE;
    }

    public long addGameStatus(ChessGameDTO chessGameDTO) {
        String query = "INSERT INTO game(game_status, last_user) VALUES (?,?)";

        return JDBC_TEMPLATE.update(query, chessGameDTO.getGameStatus(), chessGameDTO.getLastUser());
    }

    public long updateGameStatus(ChessGameDTO chessGameDTO) {
        String query = "UPDATE game SET game_status=?, last_user=? WHERE game_id=?";

        return JDBC_TEMPLATE.update(query, chessGameDTO.getGameStatus(), chessGameDTO.getLastUser(), chessGameDTO.getGameId());
    }

    public ChessGameDTO findByMaxGameId() {
        String query = "SELECT * FROM chess.game ORDER BY game_id DESC LIMIT 1;";

        return JDBC_TEMPLATE.queryForGame(query);
    }

    private static class ChessGameDAOHandler {
        static final ChessGameDAO INSTANCE = new ChessGameDAO();
    }
}
