package chess.web.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.service.ChessService;
import chess.service.dto.BoardDto;
import chess.service.dto.GameResultDto;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
    private static final int VALUE_INDEX = 1;
    private static final int TO_SQUARE_INDEX = 1;
    private static final int FROM_SQUARE_INDEX = 0;
    private static final String BODY_DELIMITER = "&";
    private static final String KEY_VALUE_DELIMITER = "=";
    private static final String GAME_ID = "game";
    private static final String GAME_NAME_KEY = ":gameName";
    private static final String OK = "OK";
    private static final String MESSAGE = ":message";
    private static final String ENCODING = "UTF-8";
    private static final String BOARD_PATH = "/board/";
    private static final String EXCEPTION_PATH = "/exception/";
    private static final String FAIL = "FAIL";
    private static final int SERVER_ERROR = 500;
    private static final int SUCCESS = 200;

    private final ChessService service;

    public WebChessController(ChessService service) {
        this.service = service;
    }

    public void run() {
        get("/", this::redirectToBoard);
        get("/new-board/" + GAME_NAME_KEY, this::requestInit);
        get("/board/" + GAME_NAME_KEY, this::renderBoard);
        post("/move/" + GAME_NAME_KEY, this::requestMove);
        get("/status/" + GAME_NAME_KEY, this::renderStatus);
        get("/exception/" + MESSAGE, this::renderException);
        get("/game-end/" + GAME_NAME_KEY, this::requestEndGame);
        exception(RuntimeException.class, this::handle);
    }

    private String redirectToBoard(Request req, Response res) {
        res.redirect("/board/" + GAME_ID);
        return OK;
    }

    private String renderBoard(Request req, Response res) {
        String gameName = req.params(GAME_NAME_KEY);
        BoardDto board = getRunningBoard(gameName);
        if (board == null) {
            res.redirect("/status/" + gameName);
        }
        return render(new ModelAndView(board, "board.html"));
    }

    private String render(ModelAndView modelAndView) {
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

    private String requestMove(Request req, Response res) {
        String gameName = req.params(GAME_NAME_KEY);
        move(gameName, req.body());
        res.redirect(BOARD_PATH + gameName);
        return OK;
    }

    private String renderStatus(Request req, Response res) {
        GameResultDto status = status(req.params(GAME_NAME_KEY));
        end(req.params(GAME_NAME_KEY));
        return render(new ModelAndView(status, "result.html"));
    }

    private String renderException(Request req, Response res) {
        try {
            String exception = URLDecoder.decode(req.params(MESSAGE), ENCODING);
            Map<String, Object> model = new HashMap<>();
            model.put("exception", exception);
            return render(new ModelAndView(model, "exception.html"));
        } catch (UnsupportedEncodingException e) {
            res.status(SERVER_ERROR);
            return FAIL;
        }
    }

    private String requestEndGame(Request req, Response res) {
        end(req.params(GAME_NAME_KEY));
        res.redirect(BOARD_PATH + req.params(GAME_NAME_KEY));
        return OK;
    }

    private void handle(RuntimeException exception, Request req, Response res) {
        try {
            res.redirect(EXCEPTION_PATH + URLEncoder.encode(exception.getMessage(), ENCODING));
        } catch (UnsupportedEncodingException e) {
            res.status(SERVER_ERROR);
        }
    }

    private void initGame(String gameName) {
        service.initGame(gameName);
    }

    private BoardDto getRunningBoard(String gameName) {
        if (service.isRunning(gameName) || service.isGameEmpty(gameName)) {
            return service.getBoard(gameName);
        }
        return null;
    }

    private void move(String gameName, String body) {
        String[] keyValues = body.split(BODY_DELIMITER);
        String from = keyValues[FROM_SQUARE_INDEX].split(KEY_VALUE_DELIMITER)[VALUE_INDEX];
        String to = keyValues[TO_SQUARE_INDEX].split(KEY_VALUE_DELIMITER)[VALUE_INDEX];
        service.move(gameName, from, to);
    }

    private GameResultDto status(String gameName) {
        return service.getResult(gameName);
    }

    private void end(String gameName) {
        service.endGame(gameName);
    }

    private String requestInit(Request req, Response res) {
        String gameName = req.params(GAME_NAME_KEY);
        initGame(gameName);
        res.status(SUCCESS);
        res.redirect(BOARD_PATH + gameName);
        return OK;
    }
}
