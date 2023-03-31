package chess.controller.game.status;

import chess.controller.Controller;
import chess.controller.main.Request;
import chess.service.game.StatusChessGameService;

public class StatusController implements Controller {

    private final StatusOutput statusOutput;
    private final StatusChessGameService statusChessGameService;

    public StatusController(StatusOutput statusOutput, StatusChessGameService statusChessGameService) {
        this.statusOutput = statusOutput;
        this.statusChessGameService = statusChessGameService;
    }

    @Override
    public void run(Request request) {
        StatusRequest statusRequest = StatusRequest.from(request);
        StatusResponse statusResponse = statusChessGameService.status(statusRequest.getBoardId());
        statusOutput.printStatus(statusResponse);
    }
}
