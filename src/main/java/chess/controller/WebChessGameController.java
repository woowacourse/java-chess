package chess.controller;

import chess.dao.ConnectDB;
import chess.dao.LogDAO;
import chess.dao.RoomDAO;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.dto.PiecesDTO;
import chess.dto.StatusDTO;
import chess.util.JsonTransformer;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessGameController {
    public void start() {
        ConnectDB connectDB = new ConnectDB();
        ChessGame chessGame = new ChessGame();
        chessGame.initialize();
        get("/", home(new RoomDAO(connectDB)));
        post("/createNewGame", newRoom(connectDB));
        get("/enter", enterRoom());
        post("/start", gameSetting(chessGame, connectDB));
        post("/myTurn", "application/json", currentTurn(chessGame), new JsonTransformer());
        post("/movablePositions", movablePositions(chessGame), new JsonTransformer());
        post("/move", "application/json", move(chessGame, connectDB), new JsonTransformer());
        post("/initialize", "application/json", initialize(chessGame), new JsonTransformer());
    }

    private Route newRoom(ConnectDB connectDB) {
        return (req, res) -> {
            RoomDAO roomDAO = new RoomDAO(connectDB);
            roomDAO.createRoom(req.queryParams("name"));
            return true;
        };
    }

    private Route home(RoomDAO roomDAO) {
        return (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rooms", roomDAO.allRooms());
            return render(model, "index.html");
        };
    }

    private Route enterRoom() {
        return (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String roomNumber = req.queryParams("id");
            model.put("number", roomNumber);
            model.put("button", "새로운게임");
            model.put("isWhite", true);
            return render(model, "chess.html");
        };
    }

    private Route gameSetting(ChessGame chessGame, ConnectDB connectDB) {
        return (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String roomId = req.queryParams("room-id");
            chessGame.initialize();
            // history db 지우기
            LogDAO logDAO = new LogDAO(connectDB);
            logDAO.deleteLogByRoomId(roomId);
            gameInformation(chessGame, model);
            model.put("number", roomId);
            return render(model, "chess.html");
        };
    }

    private void gameInformation(ChessGame chessGame, Map<String, Object> model) {
        PiecesDTO piecesDTOs = PiecesDTO.create(chessGame.board());
        model.put("pieces", piecesDTOs.toList());
        model.put("button", "초기화");
        model.put("isWhite", Team.WHITE.equals(chessGame.turn()));
        model.put("black-score", chessGame.scoreByTeam(Team.BLACK));
        model.put("white-score", chessGame.scoreByTeam(Team.WHITE));
    }

    private Route currentTurn(ChessGame chessGame) {
        return (req, res) -> {
            String clickedSection = req.queryParams("clickedSection");
            return chessGame.checkRightTurn(clickedSection);
        };
    }

    private Route movablePositions(ChessGame chessGame) {
        return (req, res) -> {
            String startPoint = req.queryParams("clickedSection");
            return chessGame.movablePositionsByStartPoint(startPoint);
        };
    }

    private Route move(ChessGame chessGame, ConnectDB connectDB) {
        return (req, res) -> {
            String roomId = req.queryParams("roomId");
            String startPoint = req.queryParams("startPoint");
            String endPoint = req.queryParams("endPoint");
            chessGame.move(startPoint, endPoint);
            LogDAO logDAO = new LogDAO(connectDB);
            logDAO.createLog(roomId, startPoint, endPoint);
            return currentStatus(chessGame);
        };
    }

    private StatusDTO currentStatus(ChessGame chessGame) {
        String turn = chessGame.turn().name();
        return new StatusDTO(turn, chessGame);
    }

    private Route initialize(ChessGame chessGame) {
        return (req, res) -> {
            chessGame.initialize();
            return true;
        };
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
