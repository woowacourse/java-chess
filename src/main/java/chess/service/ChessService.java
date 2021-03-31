package chess.service;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.domain.dao.HistoryDao;
import chess.domain.dao.HistoryDatabase;
import chess.domain.dto.CommandDto;
import chess.domain.dto.GameInfoDto;
import chess.domain.utils.BoardInitializer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessService {
    private ChessGame chessGame;
    private HistoryDao historyDao;

    public ChessService(HistoryDao historyDao) {
        this.chessGame = new ChessGame();
        this.historyDao = historyDao;
    }

    public void init() throws SQLException {
        chessGame.initBoard(BoardInitializer.init());
    }

    public Map<String, Object> startResponse() {
        Map<String, Object> model = new HashMap<>();
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
        historyDao.insert(new CommandDto(command));
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

    public void continueLastGame(HistoryDatabase historyDatabase) throws SQLException {
        chessGame.makeBoardStateOf(historyDatabase);
        play();
    }

    public Map<String, Object> continueResponse() {
        return makeCommonResponse();
    }

    public Map<String, Object> endResponse() {
        return new HashMap<>();
    }

    public void play() throws SQLException {
        historyDao.clear();
    }
}
