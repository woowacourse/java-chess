package chess.dao;

import chess.dto.ChessLogDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessLogDAO {
    private static ChessLogDAO chessLogDAO;
    private UpdateJdbcTemplate updateJdbcTemplate = UpdateJdbcTemplate.getInstance();

    private ChessLogDAO() {
    }

    public static ChessLogDAO getInstance() {
        if (chessLogDAO == null) {
            chessLogDAO = new ChessLogDAO();
        }
        return chessLogDAO;
    }

    public List<ChessLogDTO> findGameLogById(String gameId) {
        String query = "select source,destination from game_log where game_id = ?";
        List<String> parameters = Arrays.asList(gameId);
        return SelectJdbcTemplate.getInstance().executeQuery(query, parameters, (resultSet) -> {
            List<ChessLogDTO> gameLog = new ArrayList<>();

            while (resultSet.next()) {
                gameLog.add(new ChessLogDTO(resultSet.getString(1), resultSet.getString(2)));
            }
            return gameLog;
        });
    }

    public void insertLog(String from, String to, String gameId) {
        String query = "insert into game_log(source, destination,game_id) values(?,?,?);";
        List<String> parameters = Arrays.asList(from, to, gameId);
        updateJdbcTemplate.updateQuery(query, parameters);
    }
}
