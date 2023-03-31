package chess.controller.game.games;

import chess.controller.main.Request;
import chess.service.game.GamesService;

public class GamesController {

    private final GamesService gamesService;
    private final GamesOutput gamesOutput;

    public GamesController(GamesService gamesService, GamesOutput gamesOutput) {
        this.gamesService = gamesService;
        this.gamesOutput = gamesOutput;
    }

    public void run(Request request) {
        GamesRequest gamesRequest = GamesRequest.from(request);
        gamesOutput.printGames(gamesService.findAllGamesByUserId(gamesRequest.getUserId()));
    }
}
