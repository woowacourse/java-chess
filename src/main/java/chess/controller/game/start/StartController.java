package chess.controller.game.start;

import chess.controller.main.Request;
import chess.service.game.StartChessGameService;

public class StartController {

    private final StartChessGameService startChessGameService;

    public StartController(StartChessGameService startChessGameService) {
        this.startChessGameService = startChessGameService;
    }

    public void run(Request request) {
        StartRequest startRequest = StartRequest.from(request);
        startChessGameService.start(startRequest.getBoardId());
    }
}
