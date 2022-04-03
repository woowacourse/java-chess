package chess.web.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.web.service.ChessService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        staticFiles.location("/static");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (request,response) -> {
            Map<String, Object> model = new HashMap<>();

            List<String> chessBoard = chessService.createChessBoard();
            model.put("chessboard", chessBoard);

            return render(model, "index.html");
        });


        post("/move", (request,response) -> {
            Map<String, Object> model = new HashMap<>();

            List<String> chessBoard = chessService.getCurrentChessBoard();

            String moveCommand = request.queryParams("move");

            try {
                chessBoard = chessService.move(moveCommand);
                model.put("chessboard", chessBoard);
            } catch(IllegalArgumentException e) {
                model.put("error", e.getMessage());
                model.put("chessboard", chessBoard);
            }

            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
