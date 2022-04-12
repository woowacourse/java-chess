package chess.web.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.web.dao.board.BoardDaoImpl;
import chess.web.dao.room.RoomDaoImpl;
import chess.web.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private final ChessService chessService;

    public WebController() {
        chessService = new ChessService(new RoomDaoImpl(), new BoardDaoImpl());
        chessService.init();
    }

    public void run() {
        get("/", (req, res) -> render(chessService.getRooms(), "index.html"));
        post("/room/create", this::createRoomAndRedirectIndex);
        post("/room/update/", this::updateRoomNameAndRedirectIndex);
        post("/room/delete/", this::deleteRoomAndRedirectIndex);

        post("/save", (req, res) -> {
            chessService.saveCurrentRoomAndBoard(req);
            res.redirect("/");
            return null;
        });

        post("/:command", (req, res) -> {
            checkGameState(req, res);
            final Map<String, Object> model = executeAndGetModel(req, res);
            model.put("roomId", req.queryParams("roomId"));
            return render(model, "game.html");
        });

        //for Error Redirect
        get("/game", (req, res) -> render(redirectWithErrorFlash(req), "game.html"));
        //for Restart
        post("/restart", (req, res) -> {
            chessService.restart();
            final Map<String, Object> model = executeAndGetModel(req, res);
            model.put("roomId", req.queryParams("roomId"));

            return render(model, "game.html");
        });
    }

    private Map<String, Object> createRoomAndRedirectIndex(final Request req, final Response res) {
        chessService.createRoomAndBoard(req.queryParams("roomName"));
        res.redirect("/");
        return null;
    }

    private Map<String, Object> updateRoomNameAndRedirectIndex(final Request req, final Response res) {
        chessService.updateRoomName(req.queryParams("roomId"), req.queryParams("roomName"));
        res.redirect("/");
        return null;
    }

    private Map<String, Object> deleteRoomAndRedirectIndex(final Request req, final Response res) {
        chessService.removeRoom(req.queryParams("roomId"));
        res.redirect("/");
        return null;
    }

    private Map<String, Object> executeAndGetModel(final Request req, final Response res) {
        try {
            return chessService.executeCommand(req);
        } catch (IllegalArgumentException | IllegalStateException e) {
            addErrorFlashToSessionForRedirect(req, e);
            res.redirect("/game");
            return null;
        }
    }

    private Map<String, Object> redirectWithErrorFlash(final Request req) {
        final Map<String, Object> currentModel = chessService.getModelToState().get();
        if (req.session().attribute("errorFlash") != null) {
            currentModel.putAll(req.session().attribute("errorFlash"));
            currentModel.put("roomId", req.session().attribute("roomId"));

            req.session().removeAttribute("errorFlash");
            req.session().removeAttribute("roomId");
            return currentModel;
        }
        return currentModel;
    }

    private void checkGameState(final Request req, final Response res) {
        if (chessService.isNotExistGame()) {
            res.redirect("/");
        }

        if (chessService.isEndInGameOff()) {
            res.redirect("/");
        }
    }

    private void addErrorFlashToSessionForRedirect(final Request req, final RuntimeException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("hasError", true);
        error.put("errorMessage", e.getMessage());
        req.session().attribute("errorFlash", error);
        // 추가..
        System.err.println(">>>>> session에 넣는 roomId" + req.queryParams("roomId"));
        req.session().attribute("roomId", req.queryParams("roomId"));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
