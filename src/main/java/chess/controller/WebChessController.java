package chess.controller;

import static spark.Spark.get;

import chess.JsonTransformer;
import chess.controller.dto.response.BoardResponse;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    public void run() {
        final ChessGame chessGame = new ChessGame(new Board(new CreateCompleteBoardStrategy()));
        final JsonTransformer jsonTransformer = new JsonTransformer();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

        get("/api/start", (req, res) -> new BoardResponse(chessGame.getBoard()), jsonTransformer);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
