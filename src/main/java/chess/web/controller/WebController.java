package chess.web.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.web.dao.board.BoardDao;
import chess.web.dao.camp.CampDao;
import chess.web.dao.member.MemberDaoImpl;
import chess.web.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private ChessService chessService;

    public WebController() {
        chessService = new ChessService(new BoardDao(), new CampDao(), new MemberDaoImpl());
    }

    public void run() {
        get("/", (req, res) -> {
            chessService.init();
            return render(new HashMap<>(), "index.html");
        });

        post("/:command", (req, res) -> {
            checkGameState(req, res);
            return render(executeAndGetModel(req, res), "game.html");
        });

        get("/game", (req, res) -> render(redirectAndGetModel(req), "game.html"));

        post("/restart", (req, res) -> {
            chessService.restart();
            return render(executeAndGetModel(req, res), "game.html");
        });
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

    private Map<String, Object> redirectAndGetModel(final Request req) {
        final Map<String, Object> currentModel = chessService.getModelToState().get();
        if (req.session().attribute("errorFlash") != null) {
            currentModel.putAll(req.session().attribute("errorFlash"));
            req.session().removeAttribute("errorFlash");
            return currentModel;
        }
        return currentModel;
    }

    private void checkGameState(final Request req, final Response res) {
        // index.html에서 시작을 안누르고  바로 game.html으로 진입하는 경우를 처리
        if (chessService.isNotExistGame()) {
            res.redirect("/");
        }

        // game.html에서 명령어(종료버튼)입력시 내부게임종료상태로 확인되면 -> 바로 index.html로 redirect
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
