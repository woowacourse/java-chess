package chess.controller;

import static spark.Spark.get;

import chess.controller.dto.ChessGameResponse;
import chess.service.ChessGameService;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessGameController {

    private final ChessGameService chessGameService;

    public ChessGameController(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public void run() {
        getChessGame();
    }

    private void getChessGame() {
        get("/", (req, res) -> {
            ChessGameResponse chessGameResponse =  ChessGameResponse.from(chessGameService.findGameTurn());
            return render(chessGameResponse.toMap(), "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
