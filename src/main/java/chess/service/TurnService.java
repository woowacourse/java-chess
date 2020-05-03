package chess.service;

import chess.database.dao.TurnDao;
import chess.domain.Turn;

import java.sql.SQLException;

public class TurnService {
    private static TurnService instance = new TurnService();
    private static TurnDao turnDao = TurnDao.getInstance();

    public static TurnService getInstance() {
        if (instance == null) {
            instance = new TurnService();
        }
        return instance;
    }

    private TurnService() {
    }

    public void initialize() throws SQLException {
        turnDao.delete();
        turnDao.save(Turn.WHITE);
    }

    public void saveTurn(Turn turn) throws SQLException {
        turnDao.delete();
        turnDao.save(turn);
    }

    public Turn loadTurn() throws SQLException {
        return turnDao.load();
    }
}
