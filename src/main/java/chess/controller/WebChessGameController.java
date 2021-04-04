package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Rooms;
import chess.domain.Team;
import chess.dto.PiecesDTO;
import chess.dto.StatusDTO;
import chess.dto.UsersDTO;
import chess.service.LogService;
import chess.service.ResultService;
import chess.service.RoomService;
import chess.service.UserService;
import chess.util.JsonTransformer;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessGameController {
    private final RoomService roomService;
    private final ResultService resultService;
    private final UserService userService;
    private final LogService logService;

    public WebChessGameController(final RoomService roomService, final ResultService resultService,
                                  final UserService userService, final LogService logService) {
        this.roomService = roomService;
        this.resultService = resultService;
        this.userService = userService;
        this.logService = logService;
    }

    public void start(final Rooms rooms) {
        goHome(rooms);
        createNewRoom();
        enterRoom();
        startGame(rooms);
        continueGame(rooms);
        checkCurrentTurn(rooms);
        findMovablePosition(rooms);
        movePiece(rooms);
        initialize();
    }

    private void goHome(final Rooms rooms) {
        get("/", (req, res) -> {
            List<String> roomIds = roomService.allRoomsId();
            roomIds.forEach(id -> rooms.addRoom(id, new ChessGame()));
            Map<String, Object> model = new HashMap<>();
            model.put("rooms", roomService.allRooms());
            model.put("results", resultService.allUserResult());
            return render(model, "index.html");
        });
    }

    private void createNewRoom() {
        post("/createNewGame", (req, res) -> {
            roomService.createRoom(req.queryParams("name"));
            return true;
        });
    }

    private void enterRoom() {
        get("/enter", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String roomId = req.queryParams("id");
            model.put("number", roomId);
            model.put("button", "새로운게임");
            model.put("isWhite", true);
            model.put("users", userService.usersParticipatedInGame(roomId));
            return render(model, "chess.html");
        });
    }

    private void startGame(final Rooms rooms) {
        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String roomId = req.queryParams("room-id");
            ChessGame chessGame = new ChessGame();
            chessGame.initialize();
            rooms.addRoom(roomId, chessGame);
            logService.initializeByRoomId(roomId);
            UsersDTO users = userService.usersParticipatedInGame(roomId);
            gameInformation(rooms.loadGameByRoomId(roomId), model, roomId, users);
            return render(model, "chess.html");
        });
    }

    private void continueGame(final Rooms rooms) {
        post("/continue", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String roomId = req.queryParams("room-id");
            ChessGame chessGame = rooms.loadGameByRoomId(roomId);
            chessGame.initialize();
            List<String[]> logs = logService.logByRoomId(roomId);
            logService.executeLog(logs, chessGame);
            UsersDTO users = userService.usersParticipatedInGame(roomId);
            gameInformation(chessGame, model, roomId, users);
            return render(model, "chess.html");
        });
    }

    private void gameInformation(final ChessGame chessGame, final Map<String, Object> model,
                                 final String roomId, final UsersDTO users) {
        PiecesDTO piecesDTOs = PiecesDTO.create(chessGame.board());
        model.put("pieces", piecesDTOs.toList());
        model.put("button", "초기화");
        model.put("isWhite", Team.WHITE.equals(chessGame.turn()));
        model.put("black-score", chessGame.scoreByTeam(Team.BLACK));
        model.put("white-score", chessGame.scoreByTeam(Team.WHITE));
        model.put("number", roomId);
        model.put("users", users);
    }

    private void checkCurrentTurn(final Rooms rooms) {
        post("/myTurn", "application/json", (req, res) -> {
            String roomId = req.queryParams("roomId");
            String clickedSection = req.queryParams("clickedSection");
            ChessGame chessGame = rooms.loadGameByRoomId(roomId);
            return chessGame.checkRightTurn(clickedSection);
        }, new JsonTransformer());
    }

    private void findMovablePosition(final Rooms rooms) {
        post("/movablePositions", (req, res) -> {
            String roomId = req.queryParams("roomId");
            String startPoint = req.queryParams("clickedSection");
            ChessGame chessGame = rooms.loadGameByRoomId(roomId);
            return chessGame.movablePositionsByStartPoint(startPoint);
        }, new JsonTransformer());
    }

    private void movePiece(final Rooms rooms) {
        post("/move", "application/json", (req, res) -> {
            String roomId = req.queryParams("roomId");
            String startPoint = req.queryParams("startPoint");
            String endPoint = req.queryParams("endPoint");
            ChessGame chessGame = rooms.loadGameByRoomId(roomId);
            chessGame.move(startPoint, endPoint);
            logService.createLog(roomId, startPoint, endPoint);
            UsersDTO users = userService.usersParticipatedInGame(roomId);
            return new StatusDTO(chessGame, users);
        }, new JsonTransformer());
    }

    private void initialize() {
        post("/initialize", "application/json", (req, res) -> {
            String roomId = req.queryParams("roomId");
            String winner = req.queryParams("winner");
            String loser = req.queryParams("loser");
            roomService.changeStatus(roomId);
            int winnerId = userService.userIdByNickname(winner);
            int loserId = userService.userIdByNickname(loser);
            resultService.saveGameResult(roomId, winnerId, loserId);
            return true;
        }, new JsonTransformer());
    }

    private static String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
