package chess.controller;

import chess.service.ChessService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {

    private ChessService chessService = new ChessService();

    public void run(){
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", chessService.start());
            return render(model, "game.html");
        });

        post("/move", (req, res) -> {
            JSONObject request = (JSONObject) jsonParser(req);

            Map<String, Object> model = new HashMap<>();
            model.put("board", chessService.move(request.get("source").toString(), request.get("target").toString()));
            return render(model, "game.html");
        });
    }

    private Object jsonParser(Request req) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(req.body());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
