package chess.repository;

import java.sql.Connection;

public class ChessDaoGenerator {

    private static final ChessDao CHESS_DAO;

    static {
        final Connection connection = ConnectionGenerator.getConnection();
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(connection);
        CHESS_DAO = new ChessJdbcDao(jdbcTemplate);
    }

    private ChessDaoGenerator() {
    }

    public static ChessDao getChessDao() {
        return CHESS_DAO;
    }
}
