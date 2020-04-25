package chess.service;

import chess.database.dao.TurnDao;
import chess.domain.Turn;

import java.sql.SQLException;

public class TurnService {
    private static TurnService INSTANCE = new TurnService();
    private static TurnDao turnDao = TurnDao.getInstance();

    public static TurnService getInstance() {
        if (INSTANCE == null) return new TurnService();
        return INSTANCE;
    }

    private TurnService() {
    }

    public void initialize() throws SQLException {
        turnDao.delete();
        turnDao.save(Turn.WHITE);
    }

    public Turn load() throws SQLException {
        return turnDao.load();
    }

    public void save(Turn turn) throws SQLException {
        turnDao.delete();
        turnDao.save(turn);
    }
}
