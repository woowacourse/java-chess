package chess.controller;

import static spark.Spark.get;
import static spark.Spark.port;

import chess.domain.ChessGame;
import chess.dto.ChessBoardDto;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private final ChessGame chessGame;

    public ChessWebController() {
        chessGame = new ChessGame();
    }

    public void run() {
        port(8081);
        get("/", (req, res) -> render(new HashMap<>(), "index.hbs"));

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.start();
            model.put("boardDto", ChessBoardDto.from(chessGame.getBoard().getPiecesByPosition()));
            return render(model, "index.hbs");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
