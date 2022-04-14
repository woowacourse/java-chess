package chess.web.controller;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final WebChessService webChessService;

    public WebChessController() {
        this.webChessService = new WebChessService();
    }

    public String indexModel(Response res) {
        checkRunning(res);

        Map<String, Object> model = new HashMap<>();
        model.put("pieces", webChessService.getPieces());
        model.put("black-score", webChessService.getScore(Color.BLACK));
        model.put("white-score", webChessService.getScore(Color.WHITE));

        return render(model, "index.html");
    }

    private void checkRunning(Response res) {
        if (webChessService.isNotRunning()) {
            res.redirect("/start");
        }
    }

    public String movePiece(Request req, Response res) {
        ChessGame chessGame = webChessService.getChessGame();
        chessGame.move(req.queryParams("source"), req.queryParams("target"));

        Position source = new Position(req.queryParams("source"));
        Position target = new Position(req.queryParams("target"));
        webChessService.updateChessGame(chessGame, source, target);

        checkFinished(res, chessGame);

        res.redirect("/");
        return null;
    }

    private void checkFinished(Response res, ChessGame chessGame) {
        if (chessGame.isFinished()) {
            webChessService.updateState(chessGame);
            res.redirect("/winner");
        }
    }

    public String startChess(Response res) {
        webChessService.startChessGame();

        res.redirect("/");
        return null;
    }

    public String getWinnerModel() {
        ChessGame chessGame = webChessService.getChessGame();
        checkFinished(chessGame);

        Map<String, Object> model = new HashMap<>();
        model.put("black-score", webChessService.getScore(Color.WHITE));
        model.put("white-score", webChessService.getScore(Color.BLACK));
        model.put("winner", chessGame.result().toString());

        webChessService.endChessGame();
        return render(model, "winner.html");
    }

    private void checkFinished(ChessGame chessGame) {
        if(!chessGame.isFinished()) {
            chessGame.end();
        }
    }

    public String getExceptionModel(Exception exception) {
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
