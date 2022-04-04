package web.controller;

import chess.piece.Color;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import web.dao.ChessGameDao;
import web.dao.PieceDao;
import web.dto.GameStatus;
import web.service.ChessGameService;

public class ChessGameController {

    private static final String WINNER_FLASH = "WINNER_MESSAGE";
    private static final String ERROR_FLASH = "ERROR_MESSAGE";

    private final ChessGameService service;
    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public ChessGameController(ChessGameService service, ChessGameDao chessGameDao, PieceDao pieceDao) {
        this.service = service;
        this.chessGameDao = chessGameDao;
        this.pieceDao = pieceDao;
    }

    public ModelAndView chessGame(Request req, Response res) throws Exception {
        int chessGameId = Integer.parseInt(req.queryParams("chess-game-id"));

        if (!isGameRunning(chessGameId)) {
            service.prepareNewChessGame(chessGameId);
        }

        return new ModelAndView(createModel(req, chessGameId), "chess-game.html");
    }

    private boolean isGameRunning(int chessGameId) {
        return chessGameDao.findGameStatus(chessGameId) == GameStatus.RUNNING;
    }

    private Map<String, Object> createModel(Request req, int chessGameId) {
        Map<String, Object> model = new HashMap<>();
        model.put("currentColor", chessGameDao.findCurrentColor(chessGameId));
        model.put("pieces", pieceDao.findPieces(chessGameId));
        model.put("whiteScore", chessGameDao.findScoreByColor(chessGameId, Color.WHITE));
        model.put("blackScore", chessGameDao.findScoreByColor(chessGameId, Color.BLACK));
        model.put("chess-game-id", chessGameId);

        addFlashAttribute(req, model, ERROR_FLASH);
        addFlashAttribute(req, model, WINNER_FLASH);
        return model;
    }

    private void addFlashAttribute(Request req, Map<String, Object> model, String attribute) {
        if (req.session().attribute(attribute) != null) {
            model.putAll(req.session().attribute(attribute));
            req.session().removeAttribute(attribute);
        }
    }

    public ModelAndView move(Request req, Response res) {
        int chessGameId = Integer.parseInt(req.queryParams("chess-game-id"));
        String from = req.queryParams("from");
        String to = req.queryParams("to");
        try {
            move(req, new Movement(from, to), chessGameId);
        } catch (IllegalArgumentException | IllegalStateException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("hasError", true);
            error.put("errorMessage", e.getMessage());
            req.session().attribute(ERROR_FLASH, error);
        }
        res.redirect("/chess-game?chess-game-id=" + chessGameId);
        return null;
    }

    private void move(Request req, Movement movement, int chessGameId) {
        service.move(chessGameId, movement);

        if (isGameFinished(chessGameId)) {
            Map<String, Object> result = new HashMap<>();
            result.put("isFinished", true);
            result.put("winner", chessGameDao.findWinner(chessGameId));
            req.session().attribute(WINNER_FLASH, result);
        }
    }

    private boolean isGameFinished(int chessGameId) {
        return chessGameDao.findGameStatus(chessGameId) == GameStatus.FINISHED;
    }
}
