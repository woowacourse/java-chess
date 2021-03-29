package chess.controller;

import chess.controller.dto.MoveDto;
import chess.service.*;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import static spark.Spark.get;
import static spark.Spark.patch;


public class ChessController {
    private static final Gson gson = new Gson();
    private static final String CHESS_GAME = "chessGame";

    public void run() {
        get("/:gameId/start", this::start, gson::toJson);
        get("/:gameId/load", this::load, gson::toJson);
        patch("/:gameId/move", this::move, gson::toJson);
        patch("/:gameId/end", this::end, gson::toJson);
        get("/:gameId/status", this::status, gson::toJson);
    }

    public Object start(Request request, Response response) {
        String gameId = request.params(":gameId");
        StartService startService = new StartService(gameId, response);

        return startService.startNewGame();
    }

    public Object load(Request request, Response response) {
        String gameId = request.params(":gameId");
        LoadService loadService = new LoadService(gameId, response);

        return loadService.loadByGameId();
    }

    public Object move(Request request, Response response) {
        String gameId = request.params(":gameId");
        MoveDto moveDto = gson.fromJson(request.body(), MoveDto.class);

        String source = moveDto.getSource();
        String target = moveDto.getTarget();

        MoveService moveService = new MoveService(gameId, response);

        return moveService.move(source, target);
    }

    public Object status(Request request, Response response) {
        String gameId = request.params(":gameId");
        StatusService statusService = new StatusService(gameId);

        return statusService.getStatus();
    }

    public Object end(Request request, Response response) {
        String gameId = request.params(":gameId");
        EndService endService = new EndService(gameId);

        return endService.end();
    }

}
