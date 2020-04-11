package chess.service;

import chess.controller.command.Command;
import chess.dao.ChessDao;
import chess.domain.ChessManager;
import chess.dto.CommandDto;
import chess.dto.GameResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {
    private static final String MOVE_ERROR_MESSAGE = "이동할 수 없는 곳입니다. 다시 입력해주세요";

    private ChessDao chessDao;
    private ChessManager chessManager;

    public ChessService(ChessDao chessDao) {
        this.chessDao = chessDao;
    }

    public void start() {
        this.chessManager = new ChessManager();
    }

    public void playNewGame() {
        initializeDatabase();
    }

    public void playLastGame() {
        List<CommandDto> commands = chessDao.selectCommands();
        for (CommandDto command : commands) {
            Command.MOVE.apply(chessManager, command.get());
        }
    }

    public void move(String source, String target) {
        String command = String.join(" ", new String[]{"move", source, target});

        try {
            Command.MOVE.apply(chessManager, command);
            saveToDatabase(command);
        } catch (Exception e) {
            throw new IllegalArgumentException(MOVE_ERROR_MESSAGE);
        }

        if (!chessManager.isPlaying()) {
            initializeDatabase();
        }
    }

    public void end() {
        chessManager.end();
    }

    public Map<String, Object> makeStartResponse() {
        GameResponse gameResponse = new GameResponse(chessManager);
        Map<String, Object> model = new HashMap<>();
        model.put("chessPieces", gameResponse.getTiles());
        model.put("currentTeam", gameResponse.getCurrentTeam());
        model.put("currentTeamScore", gameResponse.getCurrentTeamScore());
        if (!chessDao.selectCommands().isEmpty()) {
            model.put("haveLastGameRecord", "true");
        }

        return model;
    }

    public Map<String, Object> makeMoveResponse() {
        GameResponse gameResponse = new GameResponse(chessManager);
        Map<String, Object> model = new HashMap<>();
        model.put("chessPieces", gameResponse.getTiles());
        model.put("currentTeam", gameResponse.getCurrentTeam());
        model.put("currentTeamScore", gameResponse.getCurrentTeamScore());
        chessManager.getWinner().ifPresent(winner -> model.put("winner", winner));

        return model;
    }

    private void initializeDatabase() {
        chessDao.clearCommands();
    }

    private void saveToDatabase(String command) {
        chessDao.addCommand(new CommandDto(command));
    }
}