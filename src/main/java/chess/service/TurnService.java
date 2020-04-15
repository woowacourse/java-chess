package chess.service;

import chess.domain.dao.TurnDAO;
import chess.domain.piece.Team;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TurnService {
    private TurnDAO turnDAO;

    public TurnService() throws SQLException {
        this.turnDAO = new TurnDAO();
    }

    public void updateTurn() throws SQLException {
        if (turnDAO.findTurn() == Team.WHITE) {
            turnDAO.updateTurn(Team.BLACK);
        } else {
            turnDAO.updateTurn(Team.WHITE);
        }
    }

    public void initializeTurn() throws SQLException {
        turnDAO.deleteTurn();
        turnDAO.updateTurn(Team.WHITE);
    }

    public Team getCurrentTurn() throws SQLException {
        return turnDAO.findTurn();
    }

    public Map<String, Object> receiveWinner() throws SQLException {
        updateTurn();
        Team team = turnDAO.findTurn();
        Map<String, Object> model = new HashMap<>();
        model.put("turn", team);
        return model;
    }
}
