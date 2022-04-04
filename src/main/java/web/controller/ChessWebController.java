package web.controller;

import chess.domain.board.Point;
import chess.domain.piece.Piece;
import chess.dto.BoardAndTurnInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.service.ChessService;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.exception;
import static spark.Spark.get;

public class ChessWebController {

    private final ChessService service;

    public ChessWebController() {
        this.service = new ChessService();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        Gson gson = new GsonBuilder().create();

        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

//        get("/start1", (req, res) -> {
//            res.type("application/json; charset=utf-8");
//            BoardAndTurnInfo response = service.start();
//            Map<Point, Piece> boardInfo = response.getBoard();
//
//            Map<String, Object> model = new HashMap<>();
//            model.put("board", boardInfo);
//            return render(model, "index.html");
//        });

        get("/start", (req, res) -> {
            return gson.toJson(service.start());
        });
    }
}
