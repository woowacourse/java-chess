package chess.controller;

import chess.dto.UserDto;
import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ChessWebController {
    private static final HandlebarsTemplateEngine HANDLEBARS_TEMPLATE_ENGINE = new HandlebarsTemplateEngine();

    private final ChessGameService chessGameService = new ChessGameService();

    public void route() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "/index.html");
        });

        get("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "chess.html");
        });

        post("/users", (req, res) -> {
            UserDto userDto = new UserDto(req.queryParams("userId"));
            chessGameService.addUser(userDto);
            res.redirect("/");
            return 200;
        });

        post("/login", (req, res) -> {
            UserDto userDto = new UserDto(req.queryParams("userId"));
            if (chessGameService.login(userDto)) {
                res.redirect("/chess");
                return 200;
            }
            return 400;
        });

        post("/point", (req, res) -> chessGameService.getPiece(req.body()));
        post("/move", (req, res) -> chessGameService.movePiece(req.body()));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return HANDLEBARS_TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
    }
}
