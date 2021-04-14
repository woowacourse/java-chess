package chess.controller.web;

import chess.controller.ChessController;
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

public class WebChessController extends ChessController {

    private final StartService startService;
    private final MoveService moveService;
    private final LoadService loadService;

    public WebChessController(StartService startService, MoveService moveService, LoadService loadService) {
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

    @Override
    public void status() {
        throw new UnsupportedOperationException("Web 에서는 허용되지 않는 커맨드입니다.");
    }
}
