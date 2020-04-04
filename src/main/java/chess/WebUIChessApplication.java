package chess;

import chess.controller.WebChessController;
import chess.controller.dto.BoardScoreDto;
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
            webChessController.start();
            List<TileDto> tileDtos = webChessController.getTiles();
            TeamDto teamDto = webChessController.getCurrentTeam();

            Map<String, Object> model = new HashMap<>();
            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);

            return render(model, "game.html");
        });

        post("/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            MoveResultDto moveResultDto = webChessController.move(source, target);
            List<TileDto> tileDtos = webChessController.getTiles();
            TeamDto teamDto = webChessController.getCurrentTeam();

            Map<String, Object> model = new HashMap<>();
            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);
            model.put("message", moveResultDto.getMessage());
            model.put("style", moveResultDto.getStyle());

            return render(model, "game.html");
        });

        post("/status", (req, res) -> {
            List<TileDto> tileDtos = webChessController.getTiles();
            TeamDto teamDto = webChessController.getCurrentTeam();
            BoardScoreDto boardScoreDto = webChessController.getBoardScore();
            String message = teamDto.getTeamName() + "의 점수는 " + boardScoreDto.getBoardScore() + "입니다.";

            Map<String, Object> model = new HashMap<>();
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
