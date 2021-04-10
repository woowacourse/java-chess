package chess.controller;

import chess.dto.RequestDto;
import chess.dto.UserDto;
import chess.service.ChessGameService;
import chess.service.Status;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {
    private static final HandlebarsTemplateEngine HANDLEBARS_TEMPLATE_ENGINE = new HandlebarsTemplateEngine();
    private static final Gson GSON = new Gson();

    private final ChessGameService chessGameService = new ChessGameService();

    private static String render(Map<String, Object> model, String templatePath) {
        return HANDLEBARS_TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
    }

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
            if (chessGameService.addUser(userDto)) {
                res.redirect("/");
                return 200;
            }
            return 400;
        });

        post("/login", (req, res) -> {
            UserDto userDto = new UserDto(req.queryParams("userId"));
            if (chessGameService.login(userDto)) {
                res.redirect("/chess");
                return 200;
            }
            return 404;
        });

        post("/point", (req, res) -> {
            String point = req.body();
            return GSON.toJson(chessGameService.getPiece(point));
        });

        post("/move", (req, res) -> {
            RequestDto requestDto = GSON.fromJson(req.body(), RequestDto.class);
            Status status = chessGameService.movePiece(requestDto);
            return status.getCode();
        });
    }
}
