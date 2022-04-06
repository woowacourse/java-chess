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

    public void initGame() {
        service.initGame();
    }

    public BoardDto getRunningBoard() {
        if (service.isWaitingOrRunning()) {
            return service.getBoard();
        }
        return null;
    }

    public void move(String body) {
        String[] keyValues = body.split(BODY_DELIMITER);
        String from = keyValues[FROM_SQUARE_INDEX].split(KEY_VALUE_DELIMITER)[VALUE_INDEX];
        String to = keyValues[TO_SQUARE_INDEX].split(KEY_VALUE_DELIMITER)[VALUE_INDEX];
        service.move(from, to);
    }

    public GameResultDto status() {
        service.endGame();
        return service.getResult();
    }

    public void end() {
        service.endGame();
    }
}
