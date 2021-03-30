package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.WebUIChessGameController;
import chess.dto.MovableRequestDto;
import chess.dto.MoveRequestDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
        Gson gson = new Gson();
        staticFiles.location("/public");
        WebUIChessGameController webUIChessGameController = new WebUIChessGameController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "chess-game.html");
        });

        post("/movable", (req, res) -> {
            MovableRequestDto movableRequestDto = gson
                .fromJson(req.body(), MovableRequestDto.class);
            return webUIChessGameController.movablePath(movableRequestDto);
        }, gson::toJson);

        post("/move", (req, res) -> {
            MoveRequestDto moveRequestDto = gson.fromJson(req.body(), MoveRequestDto.class);
            return webUIChessGameController.move(moveRequestDto);
        }, gson::toJson);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
