package chess.controller;

import static chess.util.JsonParser.json;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.route.HttpMethod.post;

import chess.DTO.BoardDto;
import chess.DTO.ScoreDto;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.PieceColor;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessController {

    private final Board board;

    public WebUIChessController() {
        board = BoardFactory.initializeBoard();
        run();
    }

    public void run() {
        get("/", this::renderInitBoard);
        get("/board", this::getBoard, json());

        path("/board", () ->{
            get("/status", this::isEnd, json());
            get("/turn", this::isWhiteTurn, json());
            get("/score", this::getScore, json());

            post("/move", this::move, json());
            post("/move", this::movablePath, json());
        });
    }

    private boolean movablePath(final Request request, final Response response) {
        return false;
    }

    private boolean move(final Request request, final Response response){
        return false;
    }

    private boolean isEnd(final Request request, final Response response) {
        return false;
    }

    private Map<PieceColor, Double> getScore(final Request request, final Response response) {
        return new ScoreDto(board).getScores();
    }

    private Object isWhiteTurn(final Request request, final Response response) {
        return null;
    }

    private BoardDto getBoard(final Request request, final Response response) {
        return new BoardDto(board);
    }

    private String renderInitBoard(Request request, Response response) {
        response.type("text/html");
        return render(new HashMap<>());
    }

    private static String render(Map<String, String> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }
}
