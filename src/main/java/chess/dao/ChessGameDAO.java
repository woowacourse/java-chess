package chess.dao;

import chess.domain.ChessGameException;

import java.sql.SQLException;
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
        try {
            return SelectJdbcTemplate.getInstance().executeQuery(query, parameters, (resultSet -> {
                List<Integer> previousGameId = new ArrayList<>();
                while (resultSet.next()) {
                    previousGameId.add(resultSet.getInt(1));
                }
                return previousGameId;
            }));
        } catch (SQLException e) {
            throw new ChessGameException("체스 게임을 찾을 수 없습니다." + e.getMessage());
        }
    }
}
