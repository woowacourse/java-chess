package chess.controller;

import chess.dao.*;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.dto.*;
import chess.util.JsonTransformer;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessGameController {
    public void start() {
        ConnectDB connectDB = new ConnectDB();
        ChessGame chessGame = new ChessGame();
        chessGame.initialize();

        goHome(connectDB);
        createNewRoom(connectDB);
        enterRoom(connectDB);
        startGame(connectDB, chessGame);
        continueGame(connectDB, chessGame);
        checkCurrentTurn(chessGame);
        findMovablePosition(chessGame);
        movePiece(connectDB, chessGame);
        initialize(connectDB);
    }

    private void goHome(ConnectDB connectDB) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<ResultDTO> results = new ArrayList<>();
            ResultDAO resultDAO = new ResultDAO(connectDB);
            UserDAO userDAO = new UserDAO(connectDB);
            List<UserDTO> users = userDAO.findAll();
            for (UserDTO user : users) {
                int userId = user.getId();
                int winCount = resultDAO.winCountByUserId(userId);
                int loseCount = resultDAO.loseCountByUserId(userId);
                results.add(new ResultDTO(userDAO.findNicknameById(userId), winCount, loseCount));
            }
            model.put("rooms", new RoomDAO(connectDB).allRooms());
            Collections.sort(results);
            model.put("results", results);
            return render(model, "index.html");
        });
    }

    private void createNewRoom(ConnectDB connectDB) {
        post("/createNewGame", (req, res) -> {
            RoomDAO roomDAO = new RoomDAO(connectDB);
            roomDAO.createRoom(req.queryParams("name"));
            return true;
        });
    }

    private void enterRoom(ConnectDB connectDB) {
        get("/enter", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String roomId = req.queryParams("id");
            model.put("number", roomId);
            model.put("button", "새로운게임");
            model.put("isWhite", true);
            UserDAO userDAO = new UserDAO(connectDB);
            UsersDTO users = userDAO.findByRoomId(roomId);
            model.put("users", users);
            return render(model, "chess.html");
        });
    }

    private void startGame(ConnectDB connectDB, ChessGame chessGame) {
        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String roomId = req.queryParams("room-id");
            chessGame.initialize();
            LogDAO logDAO = new LogDAO(connectDB);
            logDAO.deleteLogByRoomId(roomId);
            UserDAO userDAO = new UserDAO(connectDB);
            UsersDTO users = userDAO.findByRoomId(roomId);
            gameInformation(chessGame, model, roomId, users);
            return render(model, "chess.html");
        });
    }

    private void continueGame(ConnectDB connectDB, ChessGame chessGame) {
        post("/continue", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String roomId = req.queryParams("room-id");
            chessGame.initialize();
            LogDAO logDAO = new LogDAO(connectDB);
            List<String[]> logs = logDAO.allLogByRoomId(roomId);
            logs.forEach(positions -> chessGame.move(positions[0], positions[1]));
            UserDAO userDAO = new UserDAO(connectDB);
            UsersDTO users = userDAO.findByRoomId(roomId);
            gameInformation(chessGame, model, roomId, users);
            return render(model, "chess.html");
        });
    }

    private void gameInformation(ChessGame chessGame, Map<String, Object> model, String roomId, UsersDTO users) {
        PiecesDTO piecesDTOs = PiecesDTO.create(chessGame.board());
        model.put("pieces", piecesDTOs.toList());
        model.put("button", "초기화");
        model.put("isWhite", Team.WHITE.equals(chessGame.turn()));
        model.put("black-score", chessGame.scoreByTeam(Team.BLACK));
        model.put("white-score", chessGame.scoreByTeam(Team.WHITE));
        model.put("number", roomId);
        model.put("users", users);
    }

    private void checkCurrentTurn(ChessGame chessGame) {
        post("/myTurn", "application/json", (req, res) -> {
            String clickedSection = req.queryParams("clickedSection");
            return chessGame.checkRightTurn(clickedSection);
        }, new JsonTransformer());
    }

    private void findMovablePosition(ChessGame chessGame) {
        post("/movablePositions", (req, res) -> {
            String startPoint = req.queryParams("clickedSection");
            return chessGame.movablePositionsByStartPoint(startPoint);
        }, new JsonTransformer());
    }

    private void movePiece(ConnectDB connectDB, ChessGame chessGame) {
        post("/move", "application/json", (req, res) -> {
            String roomId = req.queryParams("roomId");
            String startPoint = req.queryParams("startPoint");
            String endPoint = req.queryParams("endPoint");
            chessGame.move(startPoint, endPoint);
            LogDAO logDAO = new LogDAO(connectDB);
            logDAO.createLog(roomId, startPoint, endPoint);
            UserDAO userDAO = new UserDAO(connectDB);
            UsersDTO users = userDAO.findByRoomId(roomId);
            return new StatusDTO(chessGame, users);
        }, new JsonTransformer());
    }

    private void initialize(ConnectDB connectDB) {
        post("/initialize", "application/json", (req, res) -> {
            String roomId = req.queryParams("roomId");
            String winner = req.queryParams("winner");
            String loser = req.queryParams("loser");
            RoomDAO roomDAO = new RoomDAO(connectDB);
            roomDAO.changeStatusEndByRoomId(roomId);
            UserDAO userDAO = new UserDAO(connectDB);
            int winnerId = userDAO.findUserIdByNickname(winner);
            int loserId = userDAO.findUserIdByNickname(loser);
            ResultDAO resultDAO = new ResultDAO(connectDB);
            resultDAO.saveGameResult(roomId, winnerId, loserId);
            return true;
        }, new JsonTransformer());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
