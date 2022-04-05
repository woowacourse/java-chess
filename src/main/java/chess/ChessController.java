package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.MoveDto;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    final ChessService chessService = new ChessService();
    final JsonTransformer jsonTransformer = new JsonTransformer();

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/board", (req, res) -> chessService.getCurrentBoard(), jsonTransformer);

        get("/turn", (req, res) -> chessService.getCurrentTurn(), jsonTransformer);

        post("/move", (req, res) -> {
            MoveDto moveDto = jsonTransformer.getGson().fromJson(req.body(), MoveDto.class);
            return chessService.move(moveDto);
        }, jsonTransformer);

        get("/start", (req, res) -> {
            chessService.start();
            return "chess game start!";
        }, jsonTransformer);

        get("/status", (req, res) -> chessService.status(), jsonTransformer);

        get("/end", (req, res) -> {
            chessService.end();
            return "chess game end!";
        }, jsonTransformer);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
