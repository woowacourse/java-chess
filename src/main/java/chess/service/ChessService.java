package chess.service;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.domain.dao.CommandDao;
import chess.domain.dao.CommandDatabase;
import chess.domain.dto.CommandDto;
import chess.domain.dto.GameInfoDto;
import chess.domain.utils.BoardInitializer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessService {
    private ChessGame chessGame;
    private CommandDao commandDao;

    public ChessService(CommandDao commandDao) {
        this.chessGame = new ChessGame();
        this.commandDao = commandDao;
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
        commandDao.insert(new CommandDto(command, "1"));
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

    public void continueLastGame(CommandDatabase commandDatabase) throws SQLException {
        chessGame.makeBoardStateOf(commandDatabase);
        play();
    }

    public Map<String, Object> continueResponse() {
        return makeCommonResponse();
    }

    public Map<String, Object> endResponse() {
        return new HashMap<>();
    }

    public void play() throws SQLException {
        commandDao.clear();
    }
}
