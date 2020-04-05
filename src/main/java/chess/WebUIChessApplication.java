package chess;

import chess.domain.game.ScoreResult;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import chess.domain.util.WrongOperationException;
import chess.domain.util.WrongPositionException;
import chess.domain.web.Log;
import chess.domain.web.LogDao;
import chess.domain.web.WebChessGame;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.JsonTransformer.json;
import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        WebChessGame chessGame = new WebChessGame();
        LogDao logDao = new LogDao();

        staticFiles.location("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("status", true);
            return render(model, "index.html");
        });

        get("/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            chessGame.reset();
            logDao.clear();

            model.put("status", true);
            return render(model, "chess.html");
        });

        get("/loading", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("status", true);

            Map<Integer, Log> fakeLog = logDao.selectAll();

            chessGame.reset();
            for (Log log : fakeLog.values()) {
                chessGame.move(PositionFactory.of(log.getStart()), PositionFactory.of(log.getEnd()));
            }

            return render(model, "chess.html");
        });

        get("/board", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", chessGame.createBoard().getPiecesForTransfer());
            model.put("turn", chessGame.getTurn());
            model.put("score", chessGame.calculateScore());
            model.put("status", true);
            return model;
        }, json());

        post("/board", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            if (req.queryParams("start").equals(req.queryParams("end"))) {
                model.put("status", true);
                return render(model, "chess.html");
            }

            try {
                chessGame.move(PositionFactory.of(req.queryParams("start")), PositionFactory.of(req.queryParams("end")));

                logDao.insert(req.queryParams("start"), req.queryParams("end"));

                model.put("status", true);
                if (chessGame.isKingDead()) {
                    model.put("winner", chessGame.getAliveKingColor());
                    ScoreResult scoreResult = chessGame.calculateScore();
                    for (Color color : scoreResult.keySet()) {
                        model.put(color + "score", scoreResult.getScoreBy(color));
                    }

                    logDao.clear();

                    chessGame.reset();
                    return render(model, "result.html");
                }
                return render(model, "chess.html");
            } catch (WrongPositionException | WrongOperationException e) {
                model.put("status", false);
                return render(model, "chess.html");
            }
        });

        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                model.put("status", true);
                List<String> movablePositionNames = chessGame
                        .findMovablePositions(PositionFactory.of(req.queryParams("start")))
                        .getPositions()
                        .stream()
                        .map(Position::toString)
                        .collect(Collectors.toList());
                model.put("position", req.queryParams("start"));
                model.put("movable", movablePositionNames);
                return model;
            } catch (WrongPositionException | WrongOperationException e) {
                model.put("status", false);
                model.put("exception", e.getMessage());
                return model;
            }
        }, json());

        post("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                model.put("status", true);
                model.put("position", req.queryParams("end"));
                return model;
            } catch (WrongPositionException | WrongOperationException e) {
                model.put("status", false);
                model.put("exception", e.getMessage());
                return model;
            }
        }, json());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}