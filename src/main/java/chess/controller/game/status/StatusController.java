package chess.controller.game.status;

import chess.controller.main.Request;
import chess.service.game.StatusChessGameService;

public class StatusController {

    private final StatusOutput statusOutput;
    private final StatusChessGameService statusChessGameService;

    public StatusController(StatusOutput statusOutput, StatusChessGameService statusChessGameService) {
        this.statusOutput = statusOutput;
        this.statusChessGameService = statusChessGameService;
    }

    public void run(Request request) {
        StatusRequest statusRequest = StatusRequest.from(request);
        StatusResponse statusResponse = statusChessGameService.status(statusRequest.getBoardId());
        statusOutput.printStatus(statusResponse);
    }
}
