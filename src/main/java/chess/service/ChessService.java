package chess.service;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.domain.dao.CommandDao;
import chess.domain.dao.CommandDatabase;
import chess.domain.dao.HistoryDao;
import chess.domain.dto.CommandDto;
import chess.domain.dto.GameInfoDto;
import chess.domain.dto.HistoryDto;
import chess.domain.utils.BoardInitializer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void init(){
        chessGame.initBoard(BoardInitializer.init());
    }

    public Map<String, Object> startResponse() throws SQLException {
        Map<String, Object> model = new HashMap<>();
        final List<HistoryDto> history = histories();
        if (!history.isEmpty()) {
            model.put("history", history);
        }
        return model;
    }

    private List<HistoryDto> histories() throws SQLException {
        return historyDao.selectActive()
                .stream()
                .map(HistoryDto::new)
                .collect(Collectors.toList());
    }

    public Map<String, Object> initResponse(String name) throws SQLException {
        final String id = historyDao.insert(name);
        final Map<String, Object> model = makeCommonResponse();
        model.put("gameId", id);
        return model;
    }

    public void end() {
        chessGame.endGame();
    }

    public void move(String command) throws SQLException {
        chessGame.move(new Commands(command));
    }

    public Map<String, Object> moveResponse(String historyId) throws SQLException {
        final Map<String, Object> model = makeCommonResponse();
        if (chessGame.isEnd()) {
            model.put("winner", chessGame.winner());
            if (historyId != null) {
                updateDB(historyId);
            }
        }
        return model;
    }

    private void updateDB(String historyId) throws SQLException {
        historyDao.updateEndState(historyId);
    }


    private Map<String, Object> makeCommonResponse() {
        final GameInfoDto gameInfoDto = new GameInfoDto(chessGame);
        Map<String, Object> model = new HashMap<>();
        model.put("squares", gameInfoDto.squares());
        model.put("turn", gameInfoDto.turn());
        model.put("scores", gameInfoDto.scores());
        return model;
    }

//    public void continueLastGame(CommandDatabase commandDatabase, String historyName) throws SQLException {
//        commandDatabase.init(commandDao.selectAllCommands(historyName));
//        chessGame.makeBoardStateOf(commandDatabase);
//    }

    public void continueLastGame(String historyName) throws SQLException {
        chessGame.makeBoardStateOf(commandDao.selectAllCommands(historyName));
    }

    public Map<String, Object> continueResponse() {
        return makeCommonResponse();
    }

    public void flushCommands(String command, String gameId) throws SQLException {
        try {
            commandDao.insert(new CommandDto(command), gameId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
