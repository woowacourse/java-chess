package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.ChessController;
import chess.domain.dto.ResponseDto;
import chess.domain.dto.move.MoveRequestDto;
import chess.domain.dto.move.MoveResponseDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Gson gson = new Gson();
    private static final ChessController controller = new ChessController();

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/createroom/:name", (req, res) -> {
            try {
                String roomName = req.params(":name");
                controller.createRoom(roomName);
                return ResponseDto.ok(roomName);
            } catch (Exception e) {
                return ResponseDto.error(e.getMessage());
            }
        });

        get("/room/:name", (req, res) -> {
            try {
                String roomName = req.params(":name");
                MoveResponseDto result = controller.findPiecesByRoomName(roomName);
                res.type("application/json");
                return ResponseDto.ok(result);
            } catch (Exception e) {
                return ResponseDto.error(e.getMessage());
            }
        });

        get("/room/:name/start", (req, res) -> {
            try {
                String roomName = req.params(":name");
                MoveResponseDto result = controller.start(roomName);
                return ResponseDto.ok(result);
            } catch (Exception e) {
                return ResponseDto.error(e.getMessage());
            }
        });

        get("/room/:name/end", (req, res) -> {
            try {
                String roomName = req.params(":name");
                MoveResponseDto result = controller.end(roomName);
                return ResponseDto.ok(result);
            } catch (Exception e) {
                return ResponseDto.error(e.getMessage());
            }
        });

        post("/room/:name/move", (req, res) -> {
            try {
                String roomName = req.params(":name");
                MoveRequestDto moveRequestDto = gson.fromJson(req.body(), MoveRequestDto.class);
                MoveResponseDto result = controller.move(roomName, moveRequestDto.getSource(), moveRequestDto.getTarget());
                return ResponseDto.ok(result);
            } catch (Exception e) {
                return ResponseDto.error(e.getMessage());
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
