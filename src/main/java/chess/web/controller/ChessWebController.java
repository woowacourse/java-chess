package chess.web.controller;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.web.dto.BoardResponse;
import java.util.HashMap;
import java.util.List;
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
        List<List<Piece>> board = chessGame.board();

        Map<String, Object> model = new HashMap<>();
        model.put("pieces", new BoardResponse(board).getValue());
        return new ModelAndView(model, "index.html");
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
