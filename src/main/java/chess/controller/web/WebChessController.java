package chess.controller.web;

import chess.exception.DataAccessException;
import chess.service.ChessService;
import chess.service.RoomService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {
    private final ChessService chessService;
    private final RoomService roomService;

    public WebChessController(ChessService chessService, RoomService roomService) {
        this.chessService = chessService;
        this.roomService = roomService;
    }

    public void run() {
        get("/", this::mainPage);
        get("/create", this::createRoom);
        get("/delete/:roomId", this::deleteRoom);
        get("/game/:roomId", this::loadGame);
        post("/game/:roomId/move", this::move);
        exception(IllegalArgumentException.class, this::handleException);
        exception(DataAccessException.class, this::handleException);
    }

    private Object mainPage(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("roomList", roomService.load());
        return render(model, "index.html");
    }

    private Object createRoom(Request request, Response response) {
        Long roomId = roomService.create(request.queryParams("roomName"));
        response.redirect("/game/" + roomId);
        return null;
    }

    private Object deleteRoom(Request request, Response response) {
        Long roomId = Long.parseLong(request.params(":roomId"));
        roomService.delete(roomId);
        response.redirect("/");
        return null;
    }

    private Object loadGame(Request request, Response response) {
        Long roomId = Long.parseLong(request.params(":roomId"));
        Map<String, Object> model = chessService.load(roomId);
        return render(model, "chessboard.html");
    }

    private Object move(Request request, Response response) {
        Long roomId = Long.parseLong(request.params(":roomId"));
        chessService.move(roomId, request.queryParams("from"), request.queryParams("to"));
        response.redirect("/game/" + roomId);
        return null;
    }

    private void handleException(RuntimeException e, Request req, Response res) {
        res.status(404);
        res.body(e.getMessage());
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
