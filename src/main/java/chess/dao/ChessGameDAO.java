package chess.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessGameDAO {
    private static ChessGameDAO chessGameDAO;


    private ChessGameDAO() {
    }

    public static ChessGameDAO getInstance() {
        if (chessGameDAO == null) {
            chessGameDAO = new ChessGameDAO();
        }
        return chessGameDAO;
    }

    public List<Integer> findPreviousGamesById(String name) {
        String query = "select game_id from chess_game where white_name = ? or black_name = ?";
        List<String> parameters = Arrays.asList(name, name);
        return SelectJdbcTemplate.getInstance().executeQuery(query, parameters, (resultSet -> {
            List<Integer> previousGameId = new ArrayList<>();
            while (resultSet.next()) {
                previousGameId.add(resultSet.getInt(1));
            }
            return previousGameId;
        }));
    }
}
