package chess.service;

import chess.dao.MoveStateDAO;
import chess.dto.MoveStateDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoveStateService {

    private MoveStateDAO moveStateDAO = new MoveStateDAO();

    public void addMoveState(MoveStateDTO moveStateDTO) throws SQLException {
        moveStateDAO.addMoveState(moveStateDTO);
    }

    public void deleteMoveStates(MoveStateDTO moveStateDTO) throws SQLException {
        moveStateDAO.deleteMoveStateById(moveStateDTO.getPlayer().getPlayerId());
    }

    public Map<String, String> searchMoveHistory(String playerId) throws SQLException {
        Map<String, String> moveHistory = new LinkedHashMap<>();
        String source;
        String target;
        ResultSet resultSet = moveStateDAO.findByPlayerId(playerId);
        resultSet.beforeFirst();
        while (resultSet.next()) {
            source = resultSet.getString("source").toLowerCase();
            target = resultSet.getString("target").toLowerCase();
            moveHistory.put(source, target);
        }
        return moveHistory;
    }
}
