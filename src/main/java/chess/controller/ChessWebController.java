package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Color;
import chess.dto.BoardDto;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private ChessService chessService = new ChessService();

    public void run() {
        staticFileLocation("/static");

        get("/", this::redirect);
        get("/chess", this::viewGame);
        post("/start", this::startNewGame);
        post("/move", this::movePiece);
        get("/result", this::viewResult);
        exception(Exception.class, this::handleException);
    }

    private String redirect(final Request request, final Response response) {
        if (chessService.isGameFinish()) {
            response.redirect("/result");
            return null;
        }

        response.redirect("/chess");
        return null;
    }

    private String viewGame(final Request request, final Response response) {
        final Map<String, Object> model = new HashMap<>();
        addInformation(model);

        return render(model, "game.html");
    }

    private void addInformation(final Map<String, Object> model) {
        if(chessService.isGameWaiting()) {
            model.put("isGameWaiting", true);
            return;
        }
        final Map<Color, Double> scores = chessService.getGameScores();
        model.put("board", new BoardDto(chessService.getPieces()));
        model.put("black", scores.get(Color.BLACK));
        model.put("white", scores.get(Color.WHITE));
    }

    private String startNewGame(final Request request, final Response response) {
        chessService.startInitializedGame();

        response.redirect("/chess");
        return null;
    }

    private String movePiece(final Request request, final Response response) {
        final String source = request.queryMap().get("source").value();
        final String target = request.queryMap().get("target").value();

        chessService.movePiece(source, target);

        response.redirect("/");
        return null;
    }

    private String viewResult(final Request request, final Response response) {
        final Map<String, Object> model = new HashMap<>();
        model.put("winner", chessService.getWinner());

        return render(model, "result.html");
    }

    private void handleException(final Exception exception, final Request request, final Response response) {
        response.status(400);
        response.body(exception.getMessage());
    }

    private String render(final Object model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
