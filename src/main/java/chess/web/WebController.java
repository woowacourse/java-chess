package chess.web;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.web.dto.RoomDto;
import chess.web.service.ChessService;
import chess.web.utils.Converter;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
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
            int roomId = Converter.convertToInt(req.params("id"));
            Map<String, Object> model = chessService.initializeRoomData(roomId);
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
}
