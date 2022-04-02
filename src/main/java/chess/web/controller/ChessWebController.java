package chess.web.controller;

import chess.dao.TurnDao;
import chess.domain.Board;
import chess.domain.ChessBoard;
import chess.domain.state.State;
import chess.domain.state.StateGenerator;
import chess.web.dto.BoardResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ChessWebController {

    private final TurnDao turnDao;

    public ChessWebController(TurnDao turnDao) {
        this.turnDao = turnDao;
    }

    public ModelAndView index(Request request, Response response) {
        return new ModelAndView(new HashMap<>(), "index.html");
    }

    public ModelAndView create(Request request, Response response) {
        turnDao.save(StateGenerator.START.getValue());

        StateGenerator stateGenerator = turnDao.findLastSaved();
        State state = stateGenerator.parseState(new ChessBoard(() -> Collections.emptyList()));

        ChessBoard chessBoard = state.chessBoard();
        Board board = chessBoard.getBoard();

        Map<String, Object> model = new HashMap<>();
        model.put("pieces", new BoardResponse(board).getValue());
        return new ModelAndView(model, "index.html");
    }
}
