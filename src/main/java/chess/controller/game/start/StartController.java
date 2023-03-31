package chess.controller.game.start;

import chess.controller.Controller;
import chess.controller.main.Request;
import chess.service.game.StartChessGameService;

public class StartController implements Controller {

    private final StartChessGameService startChessGameService;

    public StartController(StartChessGameService startChessGameService) {
        this.startChessGameService = startChessGameService;
    }

    @Override
    public void run(Request request) {
        StartRequest startRequest = StartRequest.from(request);
        startChessGameService.start(startRequest.getBoardId());
    }
}
