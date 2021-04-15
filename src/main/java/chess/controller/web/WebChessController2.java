package chess.controller.web;

import chess.domain.game.BoardFactory;
import chess.domain.game.Game;
import chess.service.LoadService;
import chess.service.MoveService;
import chess.service.StartService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController2 {
    private final StartService startService;
    private final MoveService moveService;
    private final LoadService loadService;
    private Game game;

    public WebChessController2(StartService startService, MoveService moveService, LoadService loadService) {
        this.startService = startService;
        this.moveService = moveService;
        this.loadService = loadService;
    }

    public void run() {
        get("/", this::welcomePage);
        get("/start", this::start);
        post("/chess", this::move);
        get("/load", this::load);
    }

    private Object welcomePage(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }

    private Object start(Request request, Response response) {
        init();
        return startService.start(game);
    }

    private Object move(Request request, Response response) {
        String command = request.queryParams("command");
        return moveService.move(game, command);
    }

    private Object load(Request request, Response response) {
        init();
        return loadService.load(game);
    }

    public void init() {
        game = new Game(BoardFactory.create());
    }

}
