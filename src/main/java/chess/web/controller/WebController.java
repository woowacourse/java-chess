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

    private final ChessService chessService;

    public WebController() {
        chessService = new ChessService(
            new BoardDao(),
            new CampDao(),
            new MemberDaoImpl()
        );
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

        get("/game", (req, res) -> {
            return render(redirectAndGetModel(req, res), "game.html");
        });
    }

    private Map<String, Object> redirectAndGetModel(final Request req, final Response res) {
        final Map<String, Object> currentModel = chessService.getModelToState().get();
        if (req.session().attribute("errorFlash") != null) {
            currentModel.putAll(req.session().attribute("errorFlash"));
            req.session().removeAttribute("errorFlash");
            return currentModel;
        }
        return currentModel;
    }

    private void checkGameState(final Request req, final Response res) {
        // 시작안누르고 /start로 바로 가는 경우 -> index.html 시작화면으로
        if (chessService.isNotExistGame()) {
            res.redirect("/");
        }
        // 게임이 종료된 상태에서 종료 누르기 -> index.html로 보내기
        if (chessService.isEndInGameOff()) {
            res.redirect("/");
//            render(executeAndGetModel(req, res), "index.html");
        }

        //

    }

    private Map<String, Object> executeAndGetModel(final Request req, final Response res) {
        try {
            return chessService.executeCommand(req);
        } catch (IllegalArgumentException | IllegalStateException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("hasError", true);
            error.put("errorMessage", e.getMessage());
            req.session().attribute("errorFlash", error);
//            req.session().attribute("errorMessage", e.getMessage()); // map형태로 넣어야한다.
//            res.redirect("/chess-game?chess-game-id=" + chessGameId);
            System.err.println("에러나서 /game으로 redirect중");
            res.redirect("/game"); // get으로 갈듯? get()을 만들고 거기에 모델정보도 같이 담아서 보내야한다.
            return null;
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
