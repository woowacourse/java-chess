package chess.controller;

import chess.domain.Game;
import chess.dto.BoardDto;
import chess.service.GameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class MoveController {
    public final static String PATH = "/move";

    private MoveController() {
    }

    public static MoveController getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static MoveController INSTANCE = new MoveController();
    }

    public Object move(Request req, Response res) {
        Game game = req.session().attribute("game");
        int from = Integer.parseInt(req.queryParams("from"));
        int to = Integer.parseInt(req.queryParams("to"));

        BoardDto boardDto = GameService.getInstance().moveGame(game, from, to);

        return new Gson().toJson(boardDto);
    }
}
