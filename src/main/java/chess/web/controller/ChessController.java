package chess.web.controller;

import static spark.Spark.exception;
import static spark.Spark.get;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.position.Position;
import chess.web.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    public static final String INDEX_PATH = "/";
    public static final String MOVE_PATH = "/move";
    public static final String START_PATH = "/start";
    public static final String RESULT_PATH = "/result";

    private final ChessService webChessService;

    public ChessController() {
        this.webChessService = new ChessService();
    }

    public void run() {
        get(INDEX_PATH, (req, res) -> rendIndexPage(res));

        get(MOVE_PATH, this::movePiece);

        get(START_PATH, (req, res) -> startChess(res));

        get(RESULT_PATH, (req, res) -> rendResultPage());

        exception(Exception.class, (exception, request, response) -> response.body(rendExceptionMessage(exception)));
    }

    public String rendIndexPage(Response res) {
        checkRunning(res);

        Map<String, Object> model = new HashMap<>();
        model.put("pieces", webChessService.getPieces());
        model.put("black-score", webChessService.getScore(Color.BLACK));
        model.put("white-score", webChessService.getScore(Color.WHITE));

        return render(model, "index.html");
    }

    private void checkRunning(Response res) {
        if (webChessService.isNotRunning()) {
            res.redirect(START_PATH);
        }
    }

    public String movePiece(Request req, Response res) {
        ChessGame chessGame = webChessService.getChessGame();
        chessGame.move(req.queryParams("source"), req.queryParams("target"));

        Position source = new Position(req.queryParams("source"));
        Position target = new Position(req.queryParams("target"));
        webChessService.move(chessGame, source, target);

        checkFinished(res, chessGame);

        res.redirect(INDEX_PATH);
        return null;
    }

    private void checkFinished(Response res, ChessGame chessGame) {
        if (chessGame.isFinished()) {
            webChessService.updateState(chessGame);
            res.redirect(RESULT_PATH);
        }
    }

    public String startChess(Response res) {
        webChessService.start();

        res.redirect(INDEX_PATH);
        return null;
    }

    public String rendResultPage() {
        ChessGame chessGame = webChessService.getChessGame();
        checkFinished(chessGame);

        Map<String, Object> model = new HashMap<>();
        model.put("black-score", webChessService.getScore(Color.WHITE));
        model.put("white-score", webChessService.getScore(Color.BLACK));
        model.put("winner", chessGame.result().toString());

        webChessService.end();
        return render(model, "result.html");
    }

    private void checkFinished(ChessGame chessGame) {
        if (!chessGame.isFinished()) {
            chessGame.end();
        }
    }

    public String rendExceptionMessage(Exception exception) {
        Map<String, Object> model = new HashMap<>();
        model.put("error-message", exception.getMessage());
        model.put("pieces", webChessService.getPieces());
        model.put("black-score", webChessService.getScore(Color.WHITE));
        model.put("white-score", webChessService.getScore(Color.BLACK));

        return render(model, "index.html");
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
