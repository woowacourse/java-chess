package chess;

import chess.domain.*;
import chess.persistence.dto.RoomDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
        ChessService chessService = new ChessService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/rooms", (req, res) -> {

            return chessService.findLatestNRooms(5);
        }, gson::toJson);

        post("/create-room", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            RoomDto roomDto = new RoomDto();
            roomDto.setTitle(req.queryParams("title"));
            chessService.createRoom(roomDto);

            Optional<RoomDto> maybeFound = chessService.findRoomByTitle(req.queryParams("title"));

            if (maybeFound.isPresent()) {
                model.put("id", maybeFound.get().getId());
                ChessGame chessGame = new ChessGame(new StateInitiatorFactory());
                chessService.createBoardState(chessGame.getBoard(), maybeFound.get().getId());
                return model;
            }

            return model.put("result", "fail");
        }, gson::toJson);

        get("/room", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Optional<RoomDto> maybeFound = chessService.findRoomById(Long.parseLong(req.queryParams("id")));

            if (maybeFound.isPresent()) {
                model.put("id", maybeFound.get().getId());
                model.put("title", maybeFound.get().getTitle());

                return render(model, "room.html");
            }
            return render(model, "error.html");
        });

        get("/board", (req, res) -> {
            ChessGame chessGame = new ChessGame(() -> chessService.findBoardStatesByRoomId(Long.parseLong(req.queryParams("id"))));

            return chessGame.getBoard();
        }, gson::toJson);

        put("/move-piece", (req, res) -> {
            long roomId = Long.parseLong(req.queryParams("id"));

            ChessCoordinate from = ChessCoordinate.valueOf(req.queryParams("from"));
            ChessCoordinate to = ChessCoordinate.valueOf(req.queryParams("to"));

            ChessGame chessGame = new ChessGame(() -> chessService.findBoardStatesByRoomId(roomId));
            chessGame.move(from, to);

            chessService.updateChessPiecePosition(from, to, roomId);

            return new ChessGame(() -> chessService.findBoardStatesByRoomId(roomId)).getBoard();
        }, gson::toJson);

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
