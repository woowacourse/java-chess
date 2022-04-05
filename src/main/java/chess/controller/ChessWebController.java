package chess.controller;

import static spark.Spark.get;

import chess.domain.game.state.ChessGame;
import chess.domain.game.state.Ready;
import chess.service.ChessService;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private final ChessService chessService;

    public ChessWebController() {
        this.chessService = new ChessService();
    }

    public void run() {
        ChessGame chessGame = new Ready();
        renderReady(chessGame);
    }

    private void renderReady(ChessGame chessGame) {
        get("/", (req, res) -> {
            Map<String, Object> model = chessService.getBoard().toMap();
            return render(model, "chess.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
