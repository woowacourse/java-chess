package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.command.Command;
import chess.command.CommandGenerator;
import chess.domain.ChessGame;
import chess.service.DaoService;
import chess.service.DtoService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    private ChessGame game;
    private final DaoService daoService = new DaoService();

    public void run() {
        port(8080);
        staticFileLocation("/templates");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/command", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            inGame(req.queryParams("command"), model);
            return render(model, "startedChess.html");
        });
    }

    private void inGame(String input, Map<String, Object> model) {
        try {
            DtoService.packBoardInfo(game, model);
            Command command = CommandGenerator.generate(input);
            game = command.execute(game, daoService);
            DtoService.saveInfo(command, game, model);
        } catch (IllegalArgumentException e) {
            model.put("error", e);
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
