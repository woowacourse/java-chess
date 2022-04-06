package chess.controller;

import chess.dto.BoardDto;
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

    public void move(final String input) {
        chessService.movePiece(input);
    }

//    public ModelAndView save(final Request request, final Response response) {
//        chessService.save();
//        response.redirect("/chess");
//        return null;
//    }

//    public ModelAndView status(final Request request, final Response response) {
//        final Map<Color, Double> result = chessService.getResult();
//        final Color winningColor = chessService.getWinningColor();
//        Map<String, Object> model = new HashMap<>();
//        model.put("result", new ResultDto(result, winningColor));
//
//        return new ModelAndView(model, "result.html");
//    }
}
