package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.board.generator.BoardGenerator;
import chess.domain.game.ChessGame;
import chess.service.ChessService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    private static final String INDEX_PAGE = "index.html";
    private static final String BOARD_PAGE = "board.html";
    private static final String INDEX_URL = "/";
    private static final String BOARD_URL = "/board";

    private final ChessService chessService = new ChessService();

    public void run(BoardGenerator boardGenerator) {
        ChessGame chessGame = new ChessGame();

        get(INDEX_URL, (request, response) -> render(new HashMap<>(), INDEX_PAGE));

        get(BOARD_URL, (request, response) -> render(chessService.findBoardModel(chessGame), BOARD_PAGE));

        post("/new", (request, response) -> {
            chessService.createNewBoard(boardGenerator, chessGame);
            response.redirect(BOARD_URL);
            return null;
        });

        get("/move", (request, response) -> {
            List<String> inputs = new ArrayList<>();
            inputs.add("move");
            inputs.add(request.queryParams("from"));
            inputs.add(request.queryParams("to"));

            return render(chessService.move(chessGame, inputs), BOARD_PAGE);
        });

        get("/status", (request, response) -> render(chessService.calculateScore(chessGame), BOARD_PAGE));

        get("/end", (request, response) -> {
            chessService.end(chessGame);
            response.redirect(INDEX_URL);
            return null;
        });
    }

    public String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
