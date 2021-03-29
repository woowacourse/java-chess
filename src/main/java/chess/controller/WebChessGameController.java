package chess.controller;

import chess.domain.ChessGame;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessGameController {
    public void start() {
        initialPage();
        enterGameRoom();
        gameStart();
    }

    private void initialPage() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private void enterGameRoom() {
        post("/enter", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String button = "시작";
            model.put("button", button);
            return render(model, "chess.html");
        });
    }

    private void gameStart() {
        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessGame chessGame = new ChessGame();
            model.put("chessGame", chessGame);
            String button = "초기화";
            model.put("button", button);
            return render(model, "chess.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
