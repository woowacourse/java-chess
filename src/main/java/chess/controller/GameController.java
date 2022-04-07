package chess.controller;

import chess.dto.BoardDto;
import chess.dto.ResponseDto;
import chess.dto.ResultDto;
import chess.piece.detail.Color;
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
        final BoardDto boardDto = BoardDto.toDto(chessService.getBoard());
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
        final Map<Color, Double> result = chessService.getResult();
        final Color winningColor = chessService.getWinningColor();
        Map<String, Object> model = new HashMap<>();
        model.put("result", new ResultDto(result, winningColor));

        return new ModelAndView(model, "result.html");
    }

    public ModelAndView result(final Request request, final Response response) {
        final Map<Color, Double> result = chessService.getResult();
        final Color winningColor = chessService.getWinnerColor();
        Map<String, Object> model = new HashMap<>();

        model.put("result", new ResultDto(result, winningColor));

        return new ModelAndView(model, "result.html");
    }

    public boolean isRunning() {
        return chessService.isRunning();
    }

    public ModelAndView restart(final Request request, final Response response) {
        chessService.restartGame();
        response.redirect("/chess");
        return null;
    }
}
