package chess.controller;

import static spark.Spark.get;

import chess.domain.game.ChessGame;
import chess.dto.BoardDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private static final String HTML_PATH = "index.html";

    public void run() {
        ChessGame chessGame = new ChessGame();
        chessGame.startGame();
        route(chessGame);
    }

    private void route(ChessGame chessGame) {
        Map<String, Object> model = new HashMap<>();

        get("/", (req, res) -> {
            BoardDto boardDto = BoardDto.from(chessGame.getBoard());
            List<List<String>> value = boardDto.getPieceImages();
            model.put("boards", value);

            return render(model);
        });
    }

    private String render(Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView(model, HTML_PATH);
        return new HandlebarsTemplateEngine().render(modelAndView);
    }
}
