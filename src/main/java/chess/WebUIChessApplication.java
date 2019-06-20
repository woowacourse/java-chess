package chess;

import chess.domain.*;
import chess.persistence.dto.RoomDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static spark.Spark.*;

public class WebUIChessApplication {
    private static ChessGame chessGame;

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
                return model;
            }

            return model.put("result", "fail");
        }, gson::toJson);

        get("/room", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Optional<RoomDto> maybeFound = chessService.findById(Long.parseLong(req.queryParams("id")));

            if (maybeFound.isPresent()) {
                model.put("id", maybeFound.get().getId());
                model.put("title", maybeFound.get().getTitle());

                return render(model, "room.html");
            }
            return render(model, "error.html");
        });

        get("/board", (req, res) -> {
            initBoard();


            return chessGame.getBoard();
        }, gson::toJson);

        put("/move-piece", (req, res) -> {
            initBoard();
            chessGame.move(ChessCoordinate.valueOf(req.queryParams("from")).get(), ChessCoordinate.valueOf(req.queryParams("to")).get());
            return chessGame.getBoard();
        }, gson::toJson);


    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static void initBoard() {
        ChessPiece empty = EmptyCell.getInstance();

        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(BLACK), Knight.getInstance(BLACK), Bishop.getInstance(BLACK), Queen.getInstance(BLACK),
                        King.getInstance(BLACK), Bishop.getInstance(BLACK), Knight.getInstance(BLACK), Rook.getInstance(BLACK)),
                Arrays.asList(Pawn.getInstance(BLACK), Pawn.getInstance(BLACK), Pawn.getInstance(BLACK), Pawn.getInstance(BLACK),
                        Pawn.getInstance(BLACK), Pawn.getInstance(BLACK), Pawn.getInstance(BLACK), Pawn.getInstance(BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Pawn.getInstance(WHITE), Pawn.getInstance(WHITE), Pawn.getInstance(WHITE), Pawn.getInstance(WHITE),
                        Pawn.getInstance(WHITE), Pawn.getInstance(WHITE), Pawn.getInstance(WHITE), Pawn.getInstance(WHITE)),
                Arrays.asList(Rook.getInstance(WHITE), Knight.getInstance(WHITE), Bishop.getInstance(WHITE), Queen.getInstance(WHITE),
                        King.getInstance(WHITE), Bishop.getInstance(WHITE), Knight.getInstance(WHITE), Rook.getInstance(WHITE))
        );
        chessGame = new ChessGame(boardState);
    }
}
