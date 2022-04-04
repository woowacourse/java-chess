package web.controller;

import chess.piece.Color;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.dao.ChessGameDao;
import web.dao.PieceDao;
import web.dao.ScoreDao;
import web.dto.GameStatus;
import web.service.ChessGameService;

public class ChessGameController {

    private static final String WINNER_FLASH = "WINNER_MESSAGE";
    private static final String ERROR_FLASH = "ERROR_MESSAGE";

    private final ChessGameService service;
    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;
    private final ScoreDao scoreDao;

    public ChessGameController(ChessGameService service, ChessGameDao chessGameDao, PieceDao pieceDao,
                               ScoreDao scoreDao) {
        this.service = service;
        this.chessGameDao = chessGameDao;
        this.pieceDao = pieceDao;
        this.scoreDao = scoreDao;
    }

    public Object handleIndex(Request req, Response res) throws Exception {
        if (isGameFinished() || !chessGameDao.existChessGame()) {
            service.prepareNewChessGame();
        }
        return render(createModel(req), "index.html");
    }

    private boolean isGameFinished() {
        return chessGameDao.findGameStatus() == GameStatus.FINISHED;
    }

    private Map<String, Object> createModel(Request req) {
        Map<String, Object> model = new HashMap<>();
        model.put("currentColor", chessGameDao.findCurrentColor());
        model.put("pieces", pieceDao.findPieces());
        model.put("whiteScore", scoreDao.findScoreByColor(Color.WHITE));
        model.put("blackScore", scoreDao.findScoreByColor(Color.BLACK));

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

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public Object handleMove(Request req, Response res) {
        try {
            move(req, new Movement(req.body()));
        } catch (IllegalArgumentException | IllegalStateException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("hasError", true);
            error.put("errorMessage", e.getMessage());
            req.session().attribute(ERROR_FLASH, error);
        }
        res.redirect("/");
        return null;
    }

    private void move(Request req, Movement movement) {
        service.move(movement);

        if (isGameFinished()) {
            Map<String, Object> result = new HashMap<>();
            result.put("isFinished", true);
            result.put("winner", chessGameDao.findWinner());
            req.session().attribute(WINNER_FLASH, result);
        }
    }
}
