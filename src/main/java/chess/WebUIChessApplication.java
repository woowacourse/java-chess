package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import chess.dao.PlayLogDao;
import chess.dao.RoomDao;
import chess.dao.UserDao;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.ScoreBoard;
import chess.domain.chessgame.Turn;
import chess.domain.gamestate.GameState;
import chess.dto.web.BoardDto;
import chess.dto.web.GameStatusDto;
import chess.dto.web.PointDto;
import chess.dto.web.RoomDto;
import chess.dto.web.UsersInRoomDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Gson GSON = new Gson();
    private static final PlayLogDao PLAY_LOG_DAO = new PlayLogDao();
    private static final RoomDao ROOM_DAO = new RoomDao();
    private static final UserDao USER_DAO = new UserDao();


    public static void main(String[] args) {

        Spark.staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rooms", ROOM_DAO.openedRooms());
            return render(model, "lobby.html");
        });

        post("/room", "application/json", (req, res) -> {
            try {
                RoomDto newRoom = GSON.fromJson(req.body(), RoomDto.class);
                USER_DAO.insert(newRoom.getWhite());
                USER_DAO.insert(newRoom.getBlack());
                String roomId = ROOM_DAO.insert(newRoom);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("result", "success");
                jsonObject.addProperty("roomId", roomId);
                return GSON.toJson(jsonObject);
            } catch (IllegalArgumentException e) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("result", "fail");
                jsonObject.addProperty("message", e.getMessage());
                return GSON.toJson(jsonObject);
            }
        });

        get("/room/:id", (req, res) -> {
            BoardDto boardDto = PLAY_LOG_DAO.latestBoard(req.params("id"));
            UsersInRoomDto usersInRoomDto = USER_DAO.usersInRoom(req.params("id"));
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDto);
            model.put("roomId", req.params(":id"));
            model.put("userInfo", usersInRoomDto);
            return render(model, "index.html");
        });

        put("/room/:id/start", (req, res) -> {
            Board board = boardFromDb(req.params(":id"));
            ChessGame chessGame = chessGameFromDb(board, req.params(":id"));
            chessGame.start();
            BoardDto boardDto = new BoardDto(board);
            GameStatusDto gameStatusDto = new GameStatusDto(chessGame);
            PLAY_LOG_DAO.insert(boardDto, gameStatusDto, req.params(":id"));
            return GSON.toJson(boardDto);
        });

        get("/room/:id/movablePoints/:point", "application/json", (req, res) -> {
            Point currentPoint = Point.of(req.params("point"));
            String roomId = req.params("id");
            Board board = boardFromDb(roomId);
            ChessGame chessGame = chessGameFromDb(board, roomId);
            List<Point> movablePoints = chessGame.movablePoints(currentPoint);
            List<PointDto> pointDtos = movablePoints.stream()
                .map(PointDto::new)
                .collect(Collectors.toList());
            return GSON.toJson(pointDtos);
        });

        get("/room/:id/stat", "application/json", (req, res) -> {
            return GSON.toJson(USER_DAO.usersInRoom(req.params("id")));
        });

        put("/room/:id/move", "application/json", (req, res) -> {
            String roomId = req.params("id");
            Board board = boardFromDb(roomId);
            ChessGame chessGame = chessGameFromDb(board, roomId);
            Map<String, String> body = GSON.fromJson(req.body(), HashMap.class);
            Point source = Point.of(body.get("source"));
            Point destination = Point.of(body.get("destination"));
            chessGame.move(source, destination);
            PLAY_LOG_DAO
                .insert(new BoardDto(board), new GameStatusDto(chessGame), roomId);
            if (!chessGame.isOngoing() && chessGame.winner() != Team.NONE) {
                USER_DAO.updateStats(roomId, chessGame.winner());
            }
            return GSON.toJson(new BoardDto(board));
        });

        get("/room/:id/getGameStatus", "application/json", (req, res) -> {
            String roomId = req.params("id");
            Board board = boardFromDb(roomId);
            ChessGame chessGame = chessGameFromDb(board, roomId);
            return GSON.toJson(new GameStatusDto(chessGame));
        });

        put("/room/:id/exit", (req, res) -> {
            String roomId = req.params("id");
            Board board = boardFromDb(roomId);
            ChessGame chessGame = chessGameFromDb(board, roomId);
            chessGame.end();
            BoardDto boardDto = new BoardDto(board);
            GameStatusDto gameStatusDto = new GameStatusDto(chessGame);
            PLAY_LOG_DAO.insert(boardDto, gameStatusDto, req.params(":id"));
            return GSON.toJson("success");
        });

        put("/room", (req, res) -> {
            Map<String, String> body = GSON.fromJson(req.body(), HashMap.class);
            ROOM_DAO.close(body.get("id"));
            return GSON.toJson("success");
        });
    }

    private static Board boardFromDb(String roomId) throws SQLException {
        return PLAY_LOG_DAO.latestBoard(roomId).toEntity();
    }

    private static ChessGame chessGameFromDb(Board board, String roomId)
        throws SQLException {
        GameStatusDto gameStatusDto = PLAY_LOG_DAO.latestGameStatus(roomId);
        Turn turn = gameStatusDto.toTurnEntity();
        GameState gameState = gameStatusDto.toGameStateEntity(board);
        return new ChessGame(turn, new ScoreBoard(board), gameState);
    }

    private static String render(Map<String, Object> model, String viewName) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, viewName));
    }
}
