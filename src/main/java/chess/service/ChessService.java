package chess.service;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.domain.dao.CommandDao;
import chess.domain.dao.HistoryDao;
import chess.domain.dto.CommandDto;
import chess.domain.dto.GameInfoDto;
import chess.domain.dto.HistoryDto;
import chess.domain.utils.BoardInitializer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

//    public Map<String, Object> startResponse() throws SQLException {
//        Map<String, Object> model = new HashMap<>();
//        final List<HistoryDto> history = histories();
//        if (!history.isEmpty()) {
//            model.put("history", history);
//        }
//        return model;
//    }

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
            throw new SQLException("[ERROR] 해당 사용자가 존재하지 않습니다.");
        }
        return id.get();
    }

//    public Map<String, Object> initResponse(String name) throws SQLException {
//        final Optional<String> id = historyDao.insert(name);
//        if (!id.isPresent()) {
//            throw new SQLException("[ERROR] 해당 사용자가 존재하지 않습니다.");
//        }
//        return makeCommonResponse(id.get());
//    }

    public void end() {
        chessGame.endGame();
    }

    public void move(String command) throws SQLException {
        chessGame.move(new Commands(command));
    }

//    public Map<String, Object> moveResponse(String historyId) throws SQLException {
//        final Map<String, Object> model = makeCommonResponse(historyId);
//        if (chessGame.isEnd()) {
//            model.put("winner", chessGame.winner());
//            if (historyId != null) {
//                updateDB(historyId);
//            }
//        }
//        return model;
//    }
//

    public void movePiece(String command, String historyId) throws SQLException {
        move(command);
        flushCommands(command, historyId);
        if (chessGame.isEnd() && historyId != null) {
            updateDB(historyId);
        }
    }

//    public Map<String, Object> movePiece(String command, String historyId) throws SQLException {
//        move(command);
//        flushCommands(command, historyId);
//        return moveResponse(historyId);
//    }

    private void updateDB(String historyId) throws SQLException {
        historyDao.updateEndState(historyId);
    }


//    private Map<String, Object> makeCommonResponse(String id) {
//        final GameInfoDto gameInfoDto = new GameInfoDto(chessGame);
//        Map<String, Object> model = new HashMap<>();
//        model.put("squares", gameInfoDto.squares());
//        model.put("turn", gameInfoDto.turn());
//        model.put("scores", gameInfoDto.scores());
//        model.put("gameId", id);
//        return model;
//    }

    public void continueLastGame(String historyName) throws SQLException {
        chessGame.makeBoardStateOf(commandDao.selectAllCommands(historyName));
    }

//    public Map<String, Object> continueResponse(String id) {
//        return makeCommonResponse(id);
//    }

    public void flushCommands(String command, String gameId) throws SQLException {
        try {
            commandDao.insert(new CommandDto(command), Integer.valueOf(gameId));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getIdByName(String name) throws SQLException {
        final Optional<Integer> id = historyDao.findIdByName(name);
        if (!id.isPresent()) {
            throw new SQLException("[ERROR] 해당 이름의 사용자가 존재하지 않습니다.");
        }
        return String.valueOf(id.get());
    }
}
