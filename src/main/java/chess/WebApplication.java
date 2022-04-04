package chess;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.gamestate.Score;
import chess.domain.piece.Piece;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    public static void main(String[] args) {
        staticFileLocation("/static");
        GameController gameController = new GameController();
        JsonTransformer jsonTransformer = new JsonTransformer();

        GameDao gameDao = new GameDao();
        BoardDao boardDao = new BoardDao();

        get("/", (req, res) -> initializeIndex());

        get("/start", (req, res) -> {
            gameController.start();
            res.redirect("/play");
            return null;
        });

        get("/load", (req, res) -> {
            gameController.load(boardDao.load(), gameDao.isWhiteTurn());
            res.redirect("/play");
            return null;
        });

        get("/play", (req, res) -> render(modelPlayingBoard(gameController), "index.html"));

        post("/move", (req, res) -> {
            executeMove(gameController, req);
            if (gameController.isGameFinished()) {
                res.redirect("/end");
                return null;
            }
            res.redirect("/play");
            return null;
        });

        get("/status", (req, res) -> jsonTransformer.render(modelStatus(gameController)));

        get("/save", (req, res) -> {
            try {
                executeSave(gameController, gameDao, boardDao);
            } catch (Exception e) {
                res.status(500);
                return res;
            }
            res.status(201);
            return res;
        });

        get("/end", (req, res) -> {
            gameController.end();
            return render(modelResult(gameController), "result.html");
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body("[ERROR] " + exception.getMessage());
        });
    }

    private static String initializeIndex() {
        Map<String, Object> model = new HashMap<>();
        model.put("ready", true);
        return render(model, "index.html");
    }

    private static Map<String, Object> modelPlayingBoard(GameController gameController) {
        Map<Position, Piece> board = gameController.getBoard();
        Map<String, Object> model = board.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(), Map.Entry::getValue));
        model.put("started", true);
        model.put("ready", false);
        return model;
    }

    private static void executeMove(GameController gameController, Request req) {
        Map<String, String> positions = Arrays.stream(req.body().split("&"))
                .collect(Collectors.toMap(
                        data -> data.substring(0, data.indexOf("=")),
                        data -> data.substring(data.indexOf("=") + 1)
                ));
        gameController.move(Position.of(positions.get("source")), Position.of(positions.get("target")));
    }

    private static Map<String, Object> modelStatus(GameController gameController) {
        Map<Camp, Score> scores = gameController.status();
        return scores.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(), Map.Entry::getValue));
    }

    private static void executeSave(GameController gameController, GameDao gameDao, BoardDao boardDao)
            throws SQLException {
        gameDao.save();
        boardDao.save(gameController.getBoard());
    }

    private static Map<String, Object> modelResult(GameController gameController) {
        Map<String, Object> model = new HashMap<>();
        Camp winner = gameController.getWinner();
        model.put("winner", winner);
        if (winner == Camp.NONE) {
            model.put("tie", true);
        }
        model.put("started", false);
        model.put("ready", true);
        return model;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
