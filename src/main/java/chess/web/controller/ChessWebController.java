package chess.web.controller;

import chess.domain.ChessGame;
import chess.web.dto.BoardResponse;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ChessWebController {

    private final ChessGame chessGame;

    public ChessWebController() {
        this.chessGame = new ChessGame();
    }

    public ModelAndView index(Request request, Response response) {
        if (!chessGame.isStarted()) {
            return new ModelAndView(new HashMap<>(), "index.html");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("pieces", new BoardResponse(chessGame.board()).getValue());

        return new ModelAndView(model, "chess.html");
    }

    public ModelAndView create(Request request, Response response) {
        chessGame.start();

        response.redirect("/");

        return generateEmptyModelAndView();
    }

    public ModelAndView move(Request request, Response response) {
        String target = request.queryParams("target");
        String source = request.queryParams("source");

        chessGame.move(target, source);

        response.redirect("/");

        return generateEmptyModelAndView();
    }

    public void exceptionHandle(Exception exception, Request request, Response response) {
        String errorMessage = exception.getMessage();
        response.body("<h1>" + errorMessage + "</h1>" + "<a href=\"/\"> 돌아가기 </a>");
    }

    private ModelAndView generateEmptyModelAndView() {
        return new ModelAndView(null, null);
    }
}
