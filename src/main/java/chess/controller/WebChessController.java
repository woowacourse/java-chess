package chess.controller;

import chess.dao.BackupBoardDao;
import chess.dao.RoomDao;
import chess.domain.Game;
import chess.domain.Rooms;
import chess.domain.board.Board;
import chess.domain.piece.PieceColor;
import chess.dto.RoomNamesDto;
import chess.dto.SquareDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {
    private final static HandlebarsTemplateEngine HANDLEBARS_TEMPLATE_ENGINE = new HandlebarsTemplateEngine();
    private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final RoomDao roomDao = new RoomDao();
    private final BackupBoardDao backupBoardDao = new BackupBoardDao();

    public void run() {
        staticFiles.location("/static");
        List<RoomNamesDto> roomNames = roomDao.findRoomNames();
        Rooms rooms = new Rooms(roomNames);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("names", roomDao.findRoomNames());
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            if (req.queryParams("newGame").equals("yes")) {
                addRoomToDb(req.queryParams("roomName"));
                rooms.addRoom(req.queryParams("roomName"));
            }
            return render(model, "game.html");
        });

        post("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Game currentGame = rooms.findGame(req.queryParams("roomName"));
            model.put("squares", squareDtos(currentGame.getBoard()));
            model.put("turn", currentGame.turnColor().getName());
            return GSON.toJson(model);
        });

        post("/continue", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Game currentGame = rooms.findGame(req.queryParams("roomName"));
            model.put("squares", squareDtos(currentGame.getBoard()));
            model.put("turn", currentGame.turnColor().getName());
            return GSON.toJson(model);
        });

        post("/move", (req, res) -> {
            Game currentGame = rooms.findGame(req.queryParams("roomName"));
            try {
                isStart(currentGame);
                currentGame.move(req.queryParams("source"), req.queryParams("target"));
                if (currentGame.isEnd()) {
                    backupBoardDao.deleteExistingBoard(req.queryParams("roomName"));
                    roomDao.deleteRoom(req.queryParams("roomName"));
                    return req.queryParams("source") + " " + req.queryParams("target") + " " + currentGame.winnerColor().getSymbol();
                }
            } catch (RuntimeException e) {
                res.status(400);
                return e.getMessage();
            }

            return req.queryParams("source") + " " + req.queryParams("target") + " " + currentGame.turnColor().getName();
        });

        post("/status", (req, res) -> {
            Game currentGame = rooms.findGame(req.queryParams("roomName"));
            try {
                return currentGame.computeWhitePoint() + " " + currentGame.computeBlackPoint();
            } catch (RuntimeException e) {
                res.status(400);
                return e.getMessage();
            }
        });

        post("/end", (req, res) -> {
            Game currentGame = rooms.findGame(req.queryParams("roomName"));
            backupBoardDao.savePlayingBoard(req.queryParams("roomName"),
                currentGame.getBoard(),
                currentGame.turnColor()
            );

            currentGame.isEnd();
            return "";
        });
    }

    private void addRoomToDb(String room) {
        roomDao.addRoom(room, PieceColor.WHITE);
    }

    private static void isStart(Game game) {
        if (game.isNotStart()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private List<SquareDto> squareDtos(Board board) {
        List<SquareDto> squareDtos = new ArrayList<>();
        board.positions()
            .forEach(key ->
                squareDtos.add(new SquareDto(key.toString(), board.pieceAtPosition(key).toString()))
            );

        return squareDtos;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return HANDLEBARS_TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
    }
}
