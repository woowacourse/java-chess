package chess.web.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.web.dao.board.BoardDao;
import chess.web.dao.room.RoomDao;
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
        chessService = new ChessService(new RoomDao(), new BoardDao());
        chessService.init();
    }

    public void run() {
        get("/", (req, res) -> render(chessService.getRooms(), "index.html"));
        post("/room/create", this::createRoomAndRedirectIndex);
        post("/room/update/", this::updateRoomNameAndRedirectIndex);
        post("/room/delete/", this::deleteRoomAndRedirectIndex);

        post("/save", (req, res) -> {
//            final Map<String, Object> currentModel = chessService.getModelToState().get();
//             에러가 날 때도, model + error 반환 전, roomId를 추가해줘야한다.
//            currentModel.put("roomId", req.queryParams("roomId"));

            chessService.saveCurrentRoom(req);
            //TODO save CurrentBoard
            //TODO redirect inded
            return null;
        });

        post("/:command", (req, res) -> {
            checkGameState(req, res);
            final Map<String, Object> model = executeAndGetModel(req, res);
            model.put("roomId", req.queryParams("roomId"));
            System.err.println("{{roomId}}로 뿌려줄 값 >> " + req.queryParams("roomId"));
            //TODO: roomId + model정보로 front가기 직전에 DB에 저장?
            return render(model, "game.html");
        });


        //for Error Redirect
        get("/game", (req, res) -> render(redirectWithErrorFlash(req), "game.html"));
        //save
        //for Restart
        post("/restart", (req, res) -> {
            chessService.restart();
            final Map<String, Object> model = executeAndGetModel(req, res);
            model.put("roomId", req.queryParams("roomId"));

            return render(model, "game.html");
        });
    }

    private Map<String, Object> createRoomAndRedirectIndex(final Request req, final Response res) {
        chessService.createRoom(req.queryParams("roomName"));
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
        // 에러가 날 때도, model + error 반환 전, roomId를 추가해줘야한다.
        currentModel.put("roomId", req.queryParams("roomId"));
        if (req.session().attribute("errorFlash") != null) {
            currentModel.putAll(req.session().attribute("errorFlash"));
            req.session().removeAttribute("errorFlash");
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
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
