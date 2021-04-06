package chess.service;

import chess.domain.dao.CommandDao;
import chess.domain.dao.HistoryDao;
import chess.domain.dto.CommandDto;
import chess.domain.dto.HistoryDto;
import spark.utils.StringUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessService {
    private CommandDao commandDao;
    private HistoryDao historyDao;

    public ChessService(CommandDao commandDao, HistoryDao historyDao) {
        this.commandDao = commandDao;
        this.historyDao = historyDao;
    }

    public List<HistoryDto> loadHistory() throws SQLException {
        return histories();
    }

    private List<HistoryDto> histories() throws SQLException {
        return historyDao.selectActive()
                .stream()
                .map(HistoryDto::new)
                .collect(Collectors.toList());
    }

    public String addHistory(String name) throws SQLException {
        final Optional<String> id = historyDao.insert(name);
        if (!id.isPresent()) {
            throw new SQLException("[ERROR] id 값을 불러올 수 없습니다.");
        }
        return id.get();
    }

    public void updateMoveInfo(String command, String historyId, boolean isEnd) throws SQLException {
        if (!StringUtils.isEmpty(historyId)) {
            flushCommands(command, historyId);
        }
        if (isEnd && historyId != null) {
            updateDB(historyId);
        }
    }

    private void updateDB(String historyId) throws SQLException {
        historyDao.updateEndState(historyId);
    }

    public void flushCommands(String command, String gameId) throws SQLException {
        try {
            commandDao.insert(new CommandDto(command), Integer.valueOf(gameId));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<CommandDto> lastState(String historyName) throws SQLException {
        return commandDao.selectAllCommands(historyName);
    }

    public String getIdByName(String name) throws SQLException {
        final Optional<Integer> id = historyDao.findIdByName(name);
        if (!id.isPresent()) {
            throw new SQLException("[ERROR] 해당 이름의 사용자가 존재하지 않습니다.");
        }
        return String.valueOf(id.get());
    }
}
