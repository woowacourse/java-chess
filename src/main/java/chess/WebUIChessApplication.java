package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.dto.ResponseDto;
import chess.domain.dto.move.MoveRequestDto;
import chess.domain.dto.move.MoveResponseDto;
import chess.serivce.chess.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public");

        Gson gson = new Gson();
        ChessService service = new ChessService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/createroom/:name", (req, res) -> {
            try {
                String roomName = req.params(":name");
                service.createRoom(roomName);
                return ResponseDto.ok(roomName);
            } catch (IllegalArgumentException e) {
                return ResponseDto.error(e.getMessage());
            }
        });

        get("/room/:name", (req, res) -> {
            try {
                String roomName = req.params(":name");
                MoveResponseDto result = service.findPiecesByRoomName(roomName);
                res.type("application/json");
                return ResponseDto.ok(result);
            } catch (IllegalArgumentException e) {
                return ResponseDto.error(e.getMessage());
            }
        });

        get("/room/:name/start", (req, res) -> {
            try {
                String roomName = req.params(":name");
                MoveResponseDto result = service.start(roomName);
                return ResponseDto.ok(result);
            } catch (IllegalArgumentException e) {
                return ResponseDto.error(e.getMessage());
            }
        });

        get("/room/:name/end", (req, res) -> {
            try {
                String roomName = req.params(":name");
                MoveResponseDto result = service.end(roomName);
                return ResponseDto.ok(result);
            } catch (IllegalArgumentException e) {
                return ResponseDto.error(e.getMessage());
            }
        });

        post("/room/:name/move", (req, res) -> {
            try {
                String roomName = req.params(":name");
                MoveRequestDto moveRequestDto = gson.fromJson(req.body(), MoveRequestDto.class);
                MoveResponseDto result = service.move(roomName, moveRequestDto.getSource(), moveRequestDto.getTarget());
                return ResponseDto.ok(result);
            } catch (IllegalArgumentException e) {
                return ResponseDto.error(e.getMessage());
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
