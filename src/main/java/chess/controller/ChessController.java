package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    private static String render (Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {

        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        get("/start", (req, res) -> {
            // ChessGameService의 initializeBoard 로직 실행 -> BoardDto 반환
            // gson.toJson(BoardDto) 반환
            return render(new HashMap<>(), "index.html");
        });

        get("/end", (req, res) -> {
            // ChessGameService의 endBoard 로직 실행 -> BoardDto 반환
            // gson.toJson(BoardDto) 반환
            return render(new HashMap<>(), "index.html");
        });

        get("/status", (req, res) -> {
            // ChessGameService의 createStatus 로직 실행 -> StatusdDto 반환
            // gson.toJson(StatusdDto) 반환
            return render(new HashMap<>(), "index.html");
        });

        post("/move", (req, res) -> {
            //gson fromJson request body를 MoveDto로 변환
            // ChessGameService의 move 로직 실행 BoardDto 반환
            // gson.toJson(BoardDto) 반환
            return render(new HashMap<>(), "index.html");
        });
    }
}
