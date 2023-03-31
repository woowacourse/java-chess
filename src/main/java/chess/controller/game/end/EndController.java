package chess.controller.game.end;

import chess.controller.main.Request;
import chess.service.game.EndChessGameService;

public class EndController {

    private final EndChessGameService endChessGameService;

    public EndController(EndChessGameService endChessGameService) {
        this.endChessGameService = endChessGameService;
    }

    public void run(Request request) {
        EndRequest endRequest = EndRequest.from(request);
        endChessGameService.end(endRequest.getBoardId());
    }
}
