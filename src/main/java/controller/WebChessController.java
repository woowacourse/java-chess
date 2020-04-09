package controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static util.JsonUtil.json;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import service.ChessService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
    private static Gson gson = new Gson();
    private final ChessService chessService;

    public WebChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        startPage();
        chessGamePage(chessService);
        move(chessService);
        status(chessService);
        finished(chessService);
    }

    private void startPage() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private void chessGamePage(ChessService chessService) {
        get("/chess", (req, res) -> {
            if (chessService.isFinished()) {
                res.redirect("/finished");
                return "";
            }
            return render(chessService.read(), "chess.html");
        });
    }

    private void move(ChessService chessService) {
        post("/move", (req, res) -> {
            try {
                chessService.move(req.queryParams("from"), req.queryParams("to"));
                res.redirect("/chess");
                return "";
            } catch (Exception e) {
                return e;
            }
        });
    }

    private void status(ChessService chessService) {
        post("/status", (req, res) -> chessService.status(), json());
    }

    private void finished(ChessService chessService) {
        get("/finished", (req, res) -> {
            chessService.delete();
            Map<String, Object> model = new HashMap<>();
            model.put("win", chessService.getWhoWinner());
            return render(model, "finished.html");
        });
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
