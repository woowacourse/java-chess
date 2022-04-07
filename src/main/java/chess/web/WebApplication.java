package chess.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessGame;
import chess.domain.state.Ready;
import chess.web.controller.WebChessController;
import chess.web.dao.BoardStateDaoImpl;
import chess.web.dao.PieceDaoImpl;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");

        WebChessController webChessController =
                new WebChessController(new ChessGame(new Ready()), new BoardStateDaoImpl(), new PieceDaoImpl());

        get("/", (req, res) -> {
            if (webChessController.isNotRunning()) {
                res.redirect("/start");
            }
            return render(webChessController.indexModel(), "index.html");
        });

        get("/move", (req, res) -> {
            webChessController.movePiece(req, res);
            res.redirect("/");
            return null;
        });

        get("/start", (req, res) -> {
            webChessController.startChess();
            res.redirect("/");
            return null;
        });

        get("/winner", (req, res) -> render(webChessController.getWinnerModel(), "winner.html"));

        exception(Exception.class, (exception, request, response) -> response.body(
                render(webChessController.getExceptionModel(exception), "index.html")));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
