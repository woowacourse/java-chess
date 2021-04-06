package chess.service;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.domain.dao.CommandDao;
import chess.domain.dao.HistoryDao;
import chess.domain.dto.CommandDto;
import chess.domain.dto.GameInfoDto;
import chess.domain.dto.HistoryDto;
import chess.domain.utils.BoardInitializer;
import spark.utils.StringUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessService {
    private ChessGame chessGame;
    private CommandDao commandDao;
    private HistoryDao historyDao;

    public ChessService(CommandDao commandDao, HistoryDao historyDao) {
        this.chessGame = new ChessGame();
        this.commandDao = commandDao;
        this.historyDao = historyDao;
    }

    public void init() {
        chessGame.initBoard(BoardInitializer.init());
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

    public GameInfoDto gameInfo() {
        return new GameInfoDto(chessGame);
    }

    public String addHistory(String name) throws SQLException {
        final Optional<String> id = historyDao.insert(name);
        if (!id.isPresent()) {
            throw new SQLException("[ERROR] id 값을 불러올 수 없습니다.");
        }
        return id.get();
    }

    public void end() {
        chessGame.endGame();
    }

    public void movePiece(String command, String historyId) throws SQLException {
        move(command);
        if (!StringUtils.isEmpty(historyId)) {
            flushCommands(command, historyId);
        }
        if (chessGame.isEnd() && historyId != null) {
            updateDB(historyId);
        }
    }

    public void move(String command) throws SQLException {
        chessGame.moveAs(new Commands(command));
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

    public void continueLastGame(String historyName) throws SQLException {
        chessGame.makeBoardStateOf(commandDao.selectAllCommands(historyName));
    }

    public String getIdByName(String name) throws SQLException {
        final Optional<Integer> id = historyDao.findIdByName(name);
        if (!id.isPresent()) {
            throw new SQLException("[ERROR] 해당 이름의 사용자가 존재하지 않습니다.");
        }
        return String.valueOf(id.get());
    }
}
