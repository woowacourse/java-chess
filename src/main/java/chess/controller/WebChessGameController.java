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
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessGameController {
    public void start() {
        ConnectDB connectDB = new ConnectDB();
        ChessGame chessGame = new ChessGame();
        chessGame.initialize();

        goHome(connectDB);
        createNewRoom(connectDB);
        enterRoom();
        startGame(connectDB, chessGame);
        continueGame(connectDB, chessGame);
        checkCurrentTurn(chessGame);
        findMovablePosition(chessGame);
        movePiece(connectDB, chessGame);
        initialize(chessGame);
    }

    private void goHome(ConnectDB connectDB) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rooms", new RoomDAO(connectDB).allRooms());
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

    private void enterRoom() {
        get("/enter", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String roomNumber = req.queryParams("id");
            model.put("number", roomNumber);
            model.put("button", "새로운게임");
            model.put("isWhite", true);
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
            gameInformation(chessGame, model, roomId);
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
            gameInformation(chessGame, model, roomId);
            return render(model, "chess.html");
        });
    }

    private void gameInformation(ChessGame chessGame, Map<String, Object> model, String roomId) {
        PiecesDTO piecesDTOs = PiecesDTO.create(chessGame.board());
        model.put("pieces", piecesDTOs.toList());
        model.put("button", "초기화");
        model.put("isWhite", Team.WHITE.equals(chessGame.turn()));
        model.put("black-score", chessGame.scoreByTeam(Team.BLACK));
        model.put("white-score", chessGame.scoreByTeam(Team.WHITE));
        model.put("number", roomId);
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
            return currentStatus(chessGame);
        }, new JsonTransformer());
    }

    private StatusDTO currentStatus(ChessGame chessGame) {
        String turn = chessGame.turn().name();
        return new StatusDTO(turn, chessGame);
    }

    private void initialize(ChessGame chessGame) {
        post("/initialize", "application/json", (req, res) -> {
            chessGame.initialize();
            return true;
        }, new JsonTransformer());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
