package chess.controller.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.piece.Piece;
import chess.service.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;

    public void run() {
        staticFileLocation("/static");
        Service service = new Service();
        getIndexPage(service);
        createChessGame(service);
        deleteChessGame(service);
        getChessGamePage(service);
        movePiece(service);
        resetChessGame(service);
        getException();
    }

    private void getIndexPage(final Service service) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("chess_games", service.loadAllChessGames());
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());
    }

    private void createChessGame(final Service service) {
        post("/create_chess_game", (req, res) -> {
            String name = req.queryParams("name");
            service.createChessGame(name);
            res.redirect("/game/" + name);
            return null;
        });
    }

    private void deleteChessGame(final Service service) {
        post("/delete/:name", (req, res) -> {
            service.deleteChessGame(req.params(":name"));
            res.redirect("/");
            return null;
        });
    }

    private void getChessGamePage(final Service service) {
        get("/game/:name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.params(":name");
            model.put("chess_game_name", name);
            Map<String, Piece> boardForHtml = convertBoardForHtml(service, name);
            model.putAll(boardForHtml);
            model.put("turn", service.loadChessGame(name).getTurn());
            model.put("result", service.loadChessGame(name).generateResult());
            return new ModelAndView(model, "chess_game.html");
        }, new HandlebarsTemplateEngine());
    }

    private Map<String, Piece> convertBoardForHtml(Service service, String name) {
        return service.loadChessGame(name).getCurrentBoard().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> String.valueOf(entry.getKey().getColumn().getValue()) +
                                entry.getKey().getRow().getValue(),
                        Entry::getValue
                ));
    }

    private void movePiece(final Service service) {
        post("/move/:chess_game_name", (req, res) -> {
            String chessGameName = req.params(":chess_game_name");
            String rawSource = req.queryParams("source").trim().toLowerCase();
            String rawTarget = req.queryParams("target").trim().toLowerCase();
            service.movePiece(
                    chessGameName,
                    rawSource.charAt(COLUMN_INDEX),
                    Character.getNumericValue(rawSource.charAt(ROW_INDEX)),
                    rawTarget.charAt(COLUMN_INDEX),
                    Character.getNumericValue(rawTarget.charAt(ROW_INDEX))
            );
            res.redirect("/game/" + chessGameName);
            return null;
        });
    }

    private void resetChessGame(final Service service) {
        post("/reset/:chess_game_name", (req, res) -> {
            String chessGameName = req.params(":chess_game_name");
            service.createChessGame(chessGameName);
            res.redirect("/game/" + chessGameName);
            return null;
        });
    }

    private void getException() {
        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body(exception.getMessage());
        });
    }
}
