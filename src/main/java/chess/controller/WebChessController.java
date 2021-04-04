package chess.controller;

import chess.dao.BoardDao;
import chess.domain.Game;
import chess.domain.piece.PieceColor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public void run() {
        staticFiles.location("/static");
        Game game = new Game();
        game.init();

        get("/", (req, res) -> {
            BoardDao boardDao = new BoardDao();
            Map<String, Object> model = new HashMap<>();
            model.put("names", boardDao.findRoomNames());
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            if (req.queryParams("newGame").equals("yes")) {
                addRoomToDb(req.queryParams("roomName"));
            }
            return render(model, "game.html");
        });

        post("/game", (req, res) -> {
            game.init();
            Map<String, Object> model = new HashMap<>();
            model.put("squares", game.squareDtos());
            model.put("turn", game.turnColor());
            return GSON.toJson(model);
        });

        post("/continue", (req, res) -> {
            game.continueGame(req.queryParams("roomName"));

            Map<String, Object> model = new HashMap<>();
            model.put("squares", game.squareDtos());
            model.put("turn", game.turnColor());
            return GSON.toJson(model);
        });

        post("/move", (req, res) -> {
            try {
                isStart(game);
                WebChessController.move(game, req.queryParams("source"), req.queryParams("target"));
                if (game.isEnd()) {
                    BoardDao boardDao = new BoardDao();
                    boardDao.deleteExistingBoard(req.queryParams("roomName"));
                    boardDao.deleteRoom(req.queryParams("roomName"));
                    return req.queryParams("source") + " " + req.queryParams("target") + " " + game.winnerColor().getSymbol();
                }
            } catch (RuntimeException e) {
                res.status(400);
                return e.getMessage();
            }

            return req.queryParams("source") + " " + req.queryParams("target") + " " + game.turnColor().getName();
        });

        post("/status", (req, res) -> {
            String result;
            try {
                result = game.computeWhitePoint() + " " + game.computeBlackPoint();
            } catch (RuntimeException e) {
                res.status(400);
                return e.getMessage();
            }

            return result;
        });

        post("/end", (req, res) -> {
            game.saveBoard(req.queryParams("roomName"));
            game.end();
            return "";
        });
    }

    private void addRoomToDb(String room) throws SQLException {
        BoardDao boardDao = new BoardDao();
        boardDao.addRoom(room, PieceColor.WHITE);
    }

    private static void isStart(Game game) {
        if (!game.isStart()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    public static void move(Game game, String source, String target) {
        game.move(source, target);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
