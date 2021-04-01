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
import chess.view.OutputView;

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
        final List<HistoryDto> history = historyDao.selectActive()
                        .stream()
                        .map(HistoryDto::new)
                        .collect(Collectors.toList());
        if (!history.isEmpty()) {
            model.put("history", history);
        }
        return model;
    }

    public Map<String, Object> initResponse() {
        return makeCommonResponse();
    }

    public void end() {
        chessGame.endGame();
    }

    public void move(String command) throws SQLException {
        chessGame.move(new Commands(command));
        commandDao.insert(new CommandDto(command) ,"1");
    }

    public Map<String, Object> moveResponse() {
        final Map<String, Object> model = makeCommonResponse();
        if (chessGame.isEnd()) {
            model.put("winner", chessGame.winner());
        }
        return model;
    }

    private Map<String, Object> makeCommonResponse() {
        final GameInfoDto gameInfoDto = new GameInfoDto(chessGame);
        Map<String, Object> model = new HashMap<>();
        model.put("squares", gameInfoDto.squares());
        model.put("turn", gameInfoDto.turn());
        model.put("scores", gameInfoDto.scores());
        return model;
    }

    public void continueLastGame(CommandDatabase commandDatabase, String historyName) throws SQLException {
        commandDatabase.init(commandDao.selectAllCommands(historyName));
        chessGame.makeBoardStateOf(commandDatabase);
        System.out.println("====================");
        OutputView.printBoard(chessGame.boardDto());
    }

    public Map<String, Object> continueResponse() {
        return makeCommonResponse();
    }

    public Map<String, Object> endResponse() {
        return new HashMap<>();
    }

    public void flush(String name, CommandDatabase commandDatabase) throws SQLException {
        try {
            historyDao.insert(name);
            final int id = historyDao.findIdByName(name);
            commandDao.insertAll(commandDatabase, String.valueOf(id));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
