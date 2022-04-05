package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.dto.RankDTO;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final ChessService chessService = new ChessService();

    public void run() {
        staticFiles.location("/static");

        displayHome();

        post("/move", (req, res) -> {
            String[] positions = req.body().split("//"); // a1//b2
            String srcPosition = positions[0];
            String dstPosition = positions[1];

            chessService.move(srcPosition, dstPosition);

            return "success";
        });

    }

    private void displayHome() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<RankDTO> board = chessService.getBoardStatus();
            model.put("board", board);

            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
