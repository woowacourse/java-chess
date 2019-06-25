package chess.service;

import chess.dao.RoundInfoDao;
import chess.dto.RoundInfoDto;

import java.sql.SQLDataException;
import java.util.List;

public class HistoryService {
    private HistoryService() {
    }

    private static class HistoryServiceHolder {
        private static final HistoryService INSTANCE = new HistoryService();
    }

    public static HistoryService getInstance() {
        return HistoryServiceHolder.INSTANCE;
    }

    public List<RoundInfoDto> selectAllUnfinishedGame() throws SQLDataException {
        return RoundInfoDao.getInstance().selectAllUnfinishedGame();
    }
}
