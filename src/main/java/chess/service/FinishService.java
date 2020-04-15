package chess.service;

import chess.domain.dao.FinishDAO;

import java.sql.SQLException;

public class FinishService {
    private FinishDAO finishDAO = new FinishDAO();

    public void updateFinish(final boolean isFinish) throws SQLException {
        finishDAO.updateFinish(isFinish);
    }

    public boolean isFinish() throws SQLException {
        return finishDAO.getIsFinish().equals("true");
    }

    public void initialize() throws SQLException {
        finishDAO.updateFinish(false);
    }
}
