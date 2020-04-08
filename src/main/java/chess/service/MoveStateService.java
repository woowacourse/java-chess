package chess.service;

import chess.dao.MoveStateDAO;
import chess.domain.Square;
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

    public Map<Square, Square> searchMoveHistory(MoveStateDTO moveStateDTO) throws SQLException {
        Map<Square, Square> moveHistory = new LinkedHashMap<>();
        Square source;
        Square target;
        ResultSet resultSet = moveStateDAO.findByPlayerId(moveStateDTO.getPlayer().getPlayerId());
        resultSet.beforeFirst();
        while (resultSet.next()) {
            source = Square.of(resultSet.getString("source"));
            target = Square.of(resultSet.getString("target"));
            moveHistory.put(source, target);
        }
        return moveHistory;
    }
}
