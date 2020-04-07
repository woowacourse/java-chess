package chess.controller;

import chess.dao.BoardDao;
import chess.dao.TurnDao;
import chess.domain.exception.WrongOperationException;
import chess.domain.exception.WrongPositionException;
import chess.domain.game.ChessGame;
import chess.domain.piece.pieces.PiecesFactory;
import chess.domain.position.PositionFactory;
import chess.service.ChessService;
import chess.service.ServiceState;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static chess.JsonTransformer.json;
import static spark.Spark.*;

public class WebChessController {
    private static final String DEFAULT_GAME_ID = "game";

    private final ChessService chessService;
    private final ChessGame chessGame;
    private final TurnDao turnDao;
    private final BoardDao boardDao;

    public WebChessController() {
        chessService = new ChessService();
        chessGame = new ChessGame();
        turnDao = new TurnDao();
        boardDao = new BoardDao();
    }

    public void run() {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            chessGame.reset();
            return render(chessService.makeNormalModel(), ServiceState.INIT);
        });

        get("/new", (req, res) -> {
            turnDao.update(DEFAULT_GAME_ID, chessGame.getTurn());
            boardDao.update(DEFAULT_GAME_ID, chessGame.createBoard());
            return render(chessService.makeNormalModel(), ServiceState.EXECUTE);
        });

        get("/loading", (req, res) -> {
            chessGame.load(PiecesFactory.create(boardDao.select(DEFAULT_GAME_ID)), turnDao.select(DEFAULT_GAME_ID));
            return render(chessService.makeNormalModel(), ServiceState.EXECUTE);
        });

        get("/board", (req, res) -> chessService.draw(chessGame), json());

        post("/board", (req, res) -> {
            String start = req.queryParams("start");
            String end = req.queryParams("end");

            try {
                chessGame.move(PositionFactory.of(start), PositionFactory.of(end));
                turnDao.update(DEFAULT_GAME_ID, chessGame.getTurn());
                boardDao.update(DEFAULT_GAME_ID, chessGame.createBoard());
            } catch (WrongPositionException | WrongOperationException e) {
                return render(chessService.makeInvalidModel(e.getMessage()), ServiceState.EXECUTE);
            }

            if (chessGame.isKingDead()) {
                Map<String, Object> model = chessService.terminate(chessGame);
                chessGame.reset();
                turnDao.update(DEFAULT_GAME_ID, chessGame.getTurn());
                boardDao.update(DEFAULT_GAME_ID, chessGame.createBoard());
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