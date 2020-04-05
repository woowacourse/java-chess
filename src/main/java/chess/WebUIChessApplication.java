package chess;

import chess.controller.WebChessController;
import chess.controller.dto.MoveResultDto;
import chess.controller.dto.TeamDto;
import chess.controller.dto.TileDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");
        WebChessController webChessController = new WebChessController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "start.html");
        });

        post("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            webChessController.start();
            List<TileDto> tileDtos = webChessController.getTiles();
            TeamDto teamDto = webChessController.getCurrentTeam();

            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);

            return render(model, "game.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String source = req.queryParams("source");
            String target = req.queryParams("target");

            MoveResultDto moveResultDto = webChessController.move(source, target);
            List<TileDto> tileDtos = webChessController.getTiles();
            TeamDto teamDto = webChessController.getCurrentTeam();

            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);
            model.put("message", moveResultDto.getMessage());
            model.put("style", moveResultDto.getStyle());

            if (webChessController.isEndGame()) {
                return render(model, "end.html");
            }
            return render(model, "game.html");
        });

        post("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<TileDto> tileDtos = webChessController.getTiles();
            TeamDto teamDto = webChessController.getCurrentTeam();
            String message = webChessController.getScores();

            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);
            model.put("message", message);

            return render(model, "game.html");
        });

        post("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
