package chess.controller;

import chess.dto.request.MoveRequest;
import chess.dto.response.BoardResult;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ChessController {

    private final Gson gson;
    private final ChessService chessService;

    public ChessController() {
        gson = new Gson();
        chessService = new ChessService();
    }

    public ModelAndView home(final Request request, final Response response) {
        return new ModelAndView(null, "index.html");
    }

    public ModelAndView start(final Request request, final Response response) {
        response.redirect("/game/" + chessService.initBoard());
        return null;
    }

    public ModelAndView move(final Request request, final Response response) {
        final Long boardId = Long.valueOf(request.params("boardId"));
        final MoveRequest moveRequest = gson.fromJson(request.body(), MoveRequest.class);
        final String from = moveRequest.getFrom();
        final String to = moveRequest.getTo();
        chessService.move(boardId, from, to);
        response.redirect("/game/" + boardId);
        return null;
    }

    public ModelAndView game(final Request request, final Response response) {
        final Long boardId = Long.valueOf(request.params("boardId"));
        final BoardResult boardResult = chessService.findBoardById(boardId);
        return new ModelAndView(boardResult.getValue(), "game.html");
    }

    public ModelAndView score(final Request request, final Response response) {
        final Long boardId = Long.valueOf(request.params("boardId"));
        return new ModelAndView(chessService.getScore(boardId), "game.html");
    }
}

