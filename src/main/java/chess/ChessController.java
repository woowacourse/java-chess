package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.GameDto;
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
            return render(model, "game.html");
        });

        get("game/:gameId", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/start", (req, res) -> chessService.start(), jsonTransformer);

        post("/board", (req, res) -> {
            GameDto gameDto = jsonTransformer.getGson().fromJson(req.body(), GameDto.class);
            return chessService.getCurrentBoard(gameDto.getGameId());
        }, jsonTransformer);

        post("/turn", (req, res) -> {
            GameDto gameDto = jsonTransformer.getGson().fromJson(req.body(), GameDto.class);
            return chessService.getCurrentTurn(gameDto.getGameId());
        }, jsonTransformer);

        post("/move", (req, res) -> {
            MoveDto moveDto = jsonTransformer.getGson().fromJson(req.body(), MoveDto.class);
            return chessService.move(moveDto);
        }, jsonTransformer);

        post("/status", (req, res) -> {
            GameDto gameDto = jsonTransformer.getGson().fromJson(req.body(), GameDto.class);
            return chessService.status(gameDto.getGameId());
        }, jsonTransformer);

        post("/end", (req, res) -> {
            GameDto gameDto = jsonTransformer.getGson().fromJson(req.body(), GameDto.class);
            chessService.end(gameDto.getGameId());
            return "chess game end!";
        }, jsonTransformer);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
