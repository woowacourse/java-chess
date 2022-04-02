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
        return new ModelAndView(new HashMap<>(), "index.html");
    }

    public ModelAndView create(Request request, Response response) {
        chessGame.start();

        List<List<Piece>> board = chessGame.board();

        Map<String, Object> model = new HashMap<>();
        model.put("pieces", new BoardResponse(board).getValue());
        return new ModelAndView(model, "index.html");
    }
}
