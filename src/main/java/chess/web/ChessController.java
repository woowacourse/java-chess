package chess.web;

import chess.service.*;
import chess.web.dto.MoveDto;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import static spark.Spark.*;


public class ChessController {
    private static final Gson gson = new Gson();

    public void run() {
        get("/:gameId/start", this::start, gson::toJson);
        get("/:gameId/load", this::load, gson::toJson);
        get("/:gameId/status", this::status, gson::toJson);
        post("/:gameId/save", this::save, gson::toJson);
        patch("/:gameId/move", this::move, gson::toJson);
        patch("/:gameId/end", this::end, gson::toJson);
    }

    public Object start(Request request, Response response) {
        String gameId = request.params(":gameId");

        return StartService.startNewGame(gameId, response);
    }

    public Object load(Request request, Response response) {
        String gameId = request.params(":gameId");

        return LoadService.loadByGameId(gameId, response);
    }

    public Object move(Request request, Response response) {
        String gameId = request.params(":gameId");
        MoveDto moveDto = gson.fromJson(request.body(), MoveDto.class);

        String source = moveDto.getSource();
        String target = moveDto.getTarget();

        return MoveService.move(gameId, response, source, target);
    }

    public Object save(Request request, Response response) {
        String gameId = request.params(":gameId");

        return SaveService.save(gameId, response);
    }

    public Object status(Request request, Response response) {
        String gameId = request.params(":gameId");

        return StatusService.getStatus(gameId);
    }

    public Object end(Request request, Response response) {
        String gameId = request.params(":gameId");

        return EndService.end(gameId);
    }

}
