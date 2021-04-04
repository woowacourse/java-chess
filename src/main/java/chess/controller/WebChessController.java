package chess.controller;

import chess.dao.BackupBoardDao;
import chess.dao.RoomDao;
import chess.domain.Game;
import chess.domain.piece.PieceColor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {
    private final static HandlebarsTemplateEngine HANDLEBARS_TEMPLATE_ENGINE = new HandlebarsTemplateEngine();
    private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final RoomDao roomDao = new RoomDao();
    private final BackupBoardDao backupBoardDao = new BackupBoardDao();

    public void run() {
        staticFiles.location("/static");
        Game game = new Game();
        game.init();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("names", roomDao.findRoomNames());
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
                game.move(req.queryParams("source"), req.queryParams("target"));
                if (game.isEnd()) {
                    backupBoardDao.deleteExistingBoard(req.queryParams("roomName"));
                    roomDao.deleteRoom(req.queryParams("roomName"));
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

    private void addRoomToDb(String room) {
        roomDao.addRoom(room, PieceColor.WHITE);
    }

    private static void isStart(Game game) {
        if (!game.isStart()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return HANDLEBARS_TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
    }
}
