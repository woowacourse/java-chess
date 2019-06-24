package chess;

import chess.domain.*;
import chess.persistence.dto.RoomDto;
import chess.persistence.dto.TurnDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
        ChessService chessService = new ChessService();
        Spark.staticFiles.location("/templates");


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
                ChessGame chessGame = new ChessGame(new StateInitiatorFactory(), Turn.firstTurn());

                chessService.createBoardState(chessGame.getBoard(), maybeFound.get().getId());
                chessService.createTurn(chessGame.getTurn(), maybeFound.get().getId());

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
            Optional<ChessGame> maybeChessGame = getChessGame(chessService, req);

            if (maybeChessGame.isPresent()) {
                return maybeChessGame.get().getBoard();
            }

            return render(Collections.EMPTY_MAP, "error.html");
        }, gson::toJson);

        put("/move-piece", (req, res) -> {
            long roomId = Long.parseLong(req.queryParams("id"));

            ChessCoordinate from = ChessCoordinate.valueOf(req.queryParams("from"));
            ChessCoordinate to = ChessCoordinate.valueOf(req.queryParams("to"));

            Optional<ChessGame> maybeChessGame = getChessGame(chessService, req);
            if (maybeChessGame.isPresent()) {
                ChessGame chessGame = maybeChessGame.get();
                chessGame.move(from, to);
                chessService.updateChessPiecePosition(from, to, roomId);
                chessService.updateTurnByRoomId(chessGame.getTurn(), roomId);
            }

            return getChessGame(chessService, req).get().getBoard();
        }, gson::toJson);

        get("/movable", (req, res) -> {
            ChessGame chessGame = getChessGame(chessService, req).get();
            return chessGame.getMovable(ChessCoordinate.valueOf(req.queryParams("from")));
        }, gson::toJson);

        get("/currentTune", (req, res) -> {
            Optional<TurnDto> maybeTurn = chessService.findTurnByRoomId(Long.parseLong(req.queryParams("id")));
            if (maybeTurn.isPresent()) {
                return maybeTurn.get().getCurrent();
            }
            return "error";
        }, gson::toJson);

    }

    private static Optional<ChessGame> getChessGame(ChessService chessService, Request req) {
        Optional<TurnDto> maybeFound = chessService.findTurnByRoomId(Long.parseLong(req.queryParams("id")));

        if (maybeFound.isPresent()) {
            Team team = Team.valueOf(maybeFound.get().getCurrent());

            ChessGame chessGame = new ChessGame(() -> chessService.findBoardStatesByRoomId(Long.parseLong(req.queryParams("id"))),
                    Turn.valueOf(team));

            return Optional.of(chessGame);
        }
        return Optional.empty();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
