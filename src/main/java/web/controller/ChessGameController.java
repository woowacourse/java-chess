package web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import web.dao.ChessGameDao;
import web.dao.PieceDao;
import web.dto.ChessGameDto;
import web.dto.GameStatus;
import web.dto.PieceDto;
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
        ChessGameDto chessGameDto = chessGameDao.findById(chessGameId);

        if (!isGameRunning(chessGameDto)) {
            chessGameDto = service.prepareNewChessGame(chessGameDto);
        }

        return new ModelAndView(createModel(req, chessGameDto), "chess-game.html");
    }

    private boolean isGameRunning(ChessGameDto chessGameDto) {
        return chessGameDto.getStatus() == GameStatus.RUNNING;
    }

    private Map<String, Object> createModel(Request req, ChessGameDto chessGameDto) {
        List<PieceDto> pieces = pieceDao.findPieces(chessGameDto.getId());

        Map<String, Object> model = new HashMap<>();
        model.put("pieces", pieces);
        model.put("chessGame", chessGameDto);

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
        ChessGameDto chessGameDto = service.move(chessGameId, movement);

        if (isGameFinished(chessGameDto)) {
            Map<String, Object> result = new HashMap<>();
            result.put("isFinished", true);
            result.put("winner", chessGameDto.getWinner());
            req.session().attribute(WINNER_FLASH, result);
        }
    }

    private boolean isGameFinished(ChessGameDto chessGameDto) {
        return chessGameDto.getStatus() == GameStatus.FINISHED;
    }
}
