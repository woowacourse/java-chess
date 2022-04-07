package chess.controller;

import chess.dao.JdbcBoardDao;
import chess.dao.JdbcPieceDao;
import chess.dto.request.MoveRequest;
import chess.dto.response.BoardResult;
import chess.dto.response.ErrorResponse;
import chess.service.ChessService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ChessController {

    private final Gson gson;
    private final ChessService chessService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ChessController() {
        gson = new Gson();
        chessService = new ChessService(new JdbcBoardDao(), new JdbcPieceDao());
    }

    public ModelAndView home(final Request request, final Response response) {
        return new ModelAndView(null, "index.html");
    }

    public ModelAndView start(final Request request, final Response response) {
        response.redirect("/game/" + chessService.initBoard());
        return null;
    }

    public ModelAndView move(final Request request, final Response response) {
        final Long boardId = getBoardId(request);
        final MoveRequest moveRequest = gson.fromJson(request.body(), MoveRequest.class);
        return movePiece(response, boardId, moveRequest);
    }

    public ModelAndView game(final Request request, final Response response) {
        final Long boardId = getBoardId(request);
        final BoardResult boardResult = chessService.findBoardById(boardId);
        return new ModelAndView(boardResult, "game.html");
    }

    public ModelAndView score(final Request request, final Response response) {
        final Long boardId = getBoardId(request);
        return new ModelAndView(chessService.getScore(boardId), "game.html");
    }

    private ModelAndView movePiece(final Response response, final Long boardId, final MoveRequest moveRequest) {
        final String from = moveRequest.getFrom();
        final String to = moveRequest.getTo();
        try {
            chessService.move(boardId, from, to);
            response.redirect("/game/" + boardId);
            return null;
        } catch (final IllegalArgumentException | IllegalStateException e) {
            logger.warn(e.getMessage());
            response.status(400);
            return new ModelAndView(new ErrorResponse(e.getMessage()), "game.html");
        }
    }

    private Long getBoardId(final Request request) {
        return Long.valueOf(request.params("boardId"));
    }
}

