package chess.controller;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.dto.Arguments;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private GameState state;

    public WebChessController() {
        this.state = new Ready();
    }

    public String index(Request request, Response response) {
        return render(new HashMap<>(), "index.html");
    }

    public String main(Request request, Response response) {
        return render(Map.of("board", state.getPointPieces()), "game.html");
    }

    public String create(Request request, Response response) {
        this.state = new Ready();
        response.redirect("/main");
        return null;
    }

    public String start(Request request, Response response) {
        state = state.start();
        return render(Map.of("board", state.getPointPieces()), "game.html");
    }

    public String end(Request request, Response response) {
        state = new Ready();
        response.redirect("/");
        return null;
    }

    public String move(Request request, Response response) {
        Arguments arguments = Arguments.ofRequest(request, Command.MOVE.getParameters());
        state = state.move(arguments);
        response.redirect("/main");
        return null;
    }

    public String status(Request request, Response response) {
        return render(Map.of("score", state.getColorScore(), "board", state.getPointPieces()), "game.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
