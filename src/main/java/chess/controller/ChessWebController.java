package chess.controller;

import chess.domains.board.Board;
import chess.service.ChessWebService;

import java.util.HashMap;
import java.util.Map;

public class ChessWebController {
    private final ChessWebService service;

    public ChessWebController(ChessWebService service) {
        this.service = service;
    }


    public Map<String, Object> canResume(String gameId) {
        Map<String, Object> model = new HashMap<>();

        service.canResume(model, gameId);

        model.put("game_id", gameId);
        return model;
    }

    public Map<String, Object> startNewGame(Board board, String gameId) {
        Map<String, Object> model = new HashMap<>();

        service.startNewGame(board, gameId);

        service.provideGameInfo(model, board);
        model.put("game_id", gameId);
        return model;
    }

    public Map<String, Object> resumeGame(Board board, String gameId) {
        Map<String, Object> model = new HashMap<>();

        service.resumeGame(board, gameId);

        service.provideGameInfo(model, board);
        model.put("game_id", gameId);
        return model;
    }

    public Map<String, Object> move(Board board, String gameId, String source, String target) {
        Map<String, Object> model = new HashMap<>();

        service.move(board, gameId, source, target);
        service.checkGameOver(model, board, gameId);

        service.provideGameInfo(model, board);
        model.put("game_id", gameId);
        return model;
    }
}
