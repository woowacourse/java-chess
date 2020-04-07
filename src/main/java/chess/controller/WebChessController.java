package chess.controller;

import chess.dao.HistoryDao;
import chess.domain.exception.WrongOperationException;
import chess.domain.exception.WrongPositionException;
import chess.domain.game.ChessGame;
import chess.domain.position.PositionFactory;
import chess.service.ChessService;
import chess.service.ServiceState;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static chess.JsonTransformer.json;
import static spark.Spark.*;

public class WebChessController {
    private final ChessService chessService;
    private final ChessGame chessGame;
    private final HistoryDao historyDao;

    public WebChessController() {
        chessService = new ChessService();
        chessGame = new ChessGame();
        historyDao = new HistoryDao();
    }

    public void run() {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            chessGame.reset();
            return render(chessService.makeNormalModel(), ServiceState.INIT);
        });

        get("/new", (req, res) -> {
            historyDao.clear();
            return render(chessService.makeNormalModel(), ServiceState.EXECUTE);
        });

        get("/loading", (req, res) -> {
            chessGame.moveAll(historyDao.selectAll());
            return render(chessService.makeNormalModel(), ServiceState.EXECUTE);
        });

        get("/board", (req, res) -> {
            return chessService.draw(chessGame);
        }, json());

        post("/board", (req, res) -> {
            String start = req.queryParams("start");
            String end = req.queryParams("end");

            try {
                chessGame.move(PositionFactory.of(start), PositionFactory.of(end));
                historyDao.insert(start, end);
            } catch (WrongPositionException | WrongOperationException e) {
                return render(chessService.makeInvalidModel(e.getMessage()), ServiceState.EXECUTE);
            }

            if (chessGame.isKingDead()) {
                Map<String, Object> model = chessService.terminate(chessGame);
                chessGame.reset();
                historyDao.clear();
                return render(model, ServiceState.TERMINATE);
            }

            return render(chessService.makeNormalModel(), ServiceState.EXECUTE);
        });

        post("/start", (req, res) -> {
            String start = req.queryParams("start");
            return chessService.chooseFirstPosition(chessGame, start);
        }, json());

        post("/end", (req, res) -> {
            String end = req.queryParams("end");
            return chessService.chooseSecondPosition(end);
        }, json());
    }

    private String render(Map<String, Object> model, ServiceState serviceState) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, serviceState.getUrl()));
    }
}