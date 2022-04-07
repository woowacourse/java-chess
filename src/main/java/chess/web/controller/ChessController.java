package chess.web.controller;

import chess.service.BoardDto;
import chess.service.ChessService;
import chess.service.GameResultDto;

public class ChessController {
    private static final int VALUE_INDEX = 1;
    private static final int TO_SQUARE_INDEX = 1;
    private static final int FROM_SQUARE_INDEX = 0;
    private static final String BODY_DELIMITER = "&";
    private static final String KEY_VALUE_DELIMITER = "=";

    private final ChessService service;

    public ChessController(ChessService service) {
        this.service = service;
    }

    public void initGame(String gameName) {
        service.initGame(gameName);
    }

    public BoardDto getRunningBoard(String gameName) {
        if (service.isRunning(gameName) || service.isGameEmpty(gameName)) {
            return service.getBoard(gameName);
        }
        return null;
    }

    public void move(String gameName, String body) {
        String[] keyValues = body.split(BODY_DELIMITER);
        String from = keyValues[FROM_SQUARE_INDEX].split(KEY_VALUE_DELIMITER)[VALUE_INDEX];
        String to = keyValues[TO_SQUARE_INDEX].split(KEY_VALUE_DELIMITER)[VALUE_INDEX];
        service.move(gameName, from, to);
    }

    public GameResultDto status(String gameName) {
        return service.getResult(gameName);
    }

    public void end(String gameName) {
        service.endGame(gameName);
    }
}
