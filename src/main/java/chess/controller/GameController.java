package chess.controller;

import chess.dto.BoardDto;
import chess.dto.ResponseDto;
import chess.dto.ResultDto;
import chess.game.Result;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class GameController {

    private final ChessService chessService;

    public GameController() {
        this.chessService = new ChessService();
    }

    public ModelAndView init(final Request request, final Response response) {
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "start.html");
    }

    public ModelAndView start(final Request request, final Response response) {
        chessService.initGame();
        response.redirect("/chess");
        return null;
    }

    public ModelAndView printBoard(final Request request, final Response response) {
        final BoardDto boardDto = chessService.getBoard();
        Map<String, Object> model = new HashMap<>();
        model.put("board", boardDto);
        return new ModelAndView(model, "index.html");
    }

    public ResponseDto move(final Request request, final Response response) {
        final String command = "move " + request.body();
        try {
            chessService.movePiece(command);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return new ResponseDto(400, e.getMessage(), chessService.isRunning());
        }
        return new ResponseDto(200, "", chessService.isRunning());
    }

    public ModelAndView status(final Request request, final Response response) {
        final Result result = chessService.getStatusResult();
        Map<String, Object> model = new HashMap<>();
        model.put("result", ResultDto.toDto(result));

        return new ModelAndView(model, "result.html");
    }

    public ModelAndView result(final Request request, final Response response) {
        final Result result = chessService.getFinalResult();
        Map<String, Object> model = new HashMap<>();
        model.put("result", ResultDto.toDto(result));

        return new ModelAndView(model, "result.html");
    }

    public ModelAndView restart(final Request request, final Response response) {
        chessService.restartGame();
        response.redirect("/chess");
        return null;
    }

    public boolean isRunning() {
        return chessService.isRunning();
    }
}
