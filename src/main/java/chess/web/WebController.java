package chess.web;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.property.Color;
import chess.web.dto.PieceDto;
import chess.web.dto.RoomDto;
import chess.web.service.ChessService;
import chess.web.utils.Converter;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    private static final int MAX_RANK = 8;
    private static final int MIN_RANK = 1;
    private static final char MIN_FILE = 'a';
    private static final char MAX_FILE = 'h';

    private final ChessService chessService = new ChessService();

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<RoomDto> roomDtos = chessService.findAllRoom();
            model.put("rooms", roomDtos);
            return render(model, "/home.html");
        });

        post("/save/room", (req, res) -> {
            String name = req.queryParams("name");
            chessService.saveRoom(name);
            res.redirect("/");
            return null;
        });

        post("/:id/delete", (req, res) -> {
            int roomId = Converter.convertToInt(req.params("id"));
            chessService.deleteRoom(roomId);
            res.redirect("/");
            return null;
        });

        get("/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int roomId = Converter.convertToInt(req.params("id"));
            List<PieceDto> pieces = chessService.initializeData(roomId);
            initializeRowPieces(pieces, model);
            putScore(model, pieces);
            model.put("roomId", req.params("id"));
            return render(model, "/room.html");
        });

        get("/:id/start", (req, res) -> {
            int roomId = Converter.convertToInt(req.params("id"));
            chessService.start(roomId);
            res.redirect("/" + roomId);
            return null;
        });

        post("/:id/move", (req, res) -> {
            int roomId = Converter.convertToInt(req.params("id"));
            chessService.move(req.queryParams("command"), roomId);
            res.redirect("/" + roomId);
            return null;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private void initializeRowPieces(List<PieceDto> pieces, Map<String, Object> model) {
        for (int i = MAX_RANK; i >= MIN_RANK; i--) {
            List<PieceDto> columnPieces = initializeColumnPieces(pieces, i);
            model.put("pieces" + i, columnPieces);
        }
    }

    private List<PieceDto> initializeColumnPieces(List<PieceDto> pieces, int rank) {
        List<PieceDto> columnPieces = new ArrayList<>();

        for (char currentFile = MIN_FILE; currentFile <= MAX_FILE; currentFile++) {
            String position = currentFile + String.valueOf(rank);
            PieceDto pieceDto = pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findFirst()
                .orElse(PieceDto.of(position, "", ""));
            columnPieces.add(pieceDto);
        }
        return columnPieces;
    }

    private void putScore(Map<String, Object> model, List<PieceDto> pieces) {
        if (!pieces.isEmpty()) {
            Map<Color, Double> status = chessService.getStatus();
            model.put("blackScore", status.get(Color.Black));
            model.put("WhiteScore", status.get(Color.White));
        }
    }
}
