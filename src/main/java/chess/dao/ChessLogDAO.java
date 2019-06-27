package chess.dao;

import chess.domain.ChessGameException;
import chess.dto.ChessLogDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessLogDAO {
    private static ChessLogDAO chessLogDAO;

    private ChessLogDAO() {
    }

    public static ChessLogDAO getInstance() {
        if (chessLogDAO == null) {
            chessLogDAO = new ChessLogDAO();
        }
        return chessLogDAO;
    }

    public List<ChessLogDTO> findGameLogById(String gameId) {
        SelectJdbcTemplate<List<ChessLogDTO>> selectJdbcTemplate = new SelectJdbcTemplate<List<ChessLogDTO>>() {
            @Override
            public List<ChessLogDTO> getResult(ResultSet resultSet) throws SQLException {
                List<ChessLogDTO> gameLog = new ArrayList<>();

                while (resultSet.next()) {
                    gameLog.add(new ChessLogDTO(resultSet.getString(1), resultSet.getString(2)));
                }

                return gameLog;
            }
        };

        String query = "select source,destination from game_log where game_id = ?";
        List<String> parameters = Arrays.asList(gameId);
        try {
            return selectJdbcTemplate.executeQuery(query, parameters);
        } catch (SQLException e) {
            throw new ChessGameException("게임 로그를 찾을 수 없습니다. : " + e.getMessage());
        }
    }

    public void insertLog(String from, String to, String gameId) {
        UpdateJdbcTemplate updateJdbcTemplate = new UpdateJdbcTemplate();
        String query = "insert into game_log(source, destination,game_id) values(?,?,?);";
        List<String> parameters = Arrays.asList(from, to, gameId);

        try {
            updateJdbcTemplate.updateQuery(query, parameters);
        } catch (SQLException e) {
            throw new ChessGameException("게임 로그를 정상적으로 기록하지 못하였습니다. :" + e.getMessage());
        }
    }
}
