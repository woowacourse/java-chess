package chess.controller;

import chess.model.Square;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class ChessGameController {
    private static ChessService service;

    static {
        service = new ChessService();
    }

    public static Route serveIndexPage = (request, response) -> {
        response.redirect("/chessgame.html");
        return null;
    };

    public static Route move = (request, response) -> {
        response.type("application/json");
        Map<String, Object> model = new HashMap<>();
        try {
            model.put("result", service.canMove(Square.of(request.queryMap("source").value())
                    , Square.of(request.queryMap("target").value())));
            return new Gson().toJson(model);
        } catch (Exception e) {
            model.put("result", false);
            response.status(500);
            return new Gson().toJson(model);
        }
    };
}