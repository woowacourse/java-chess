package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import chess.controller.ChessController;
import chess.domain.dto.ErrorResponseDto;
import chess.domain.dto.OkResponseDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.move.MoveRequestDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
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

        get("/room/:id", (req, res) -> {
            try {
                Long roomId = Long.valueOf(req.params(":id"));
                List<PieceDto> result = controller.findPiecesByRoomId(roomId);
                return gson.toJson(OkResponseDto.payload(result));
            } catch (Exception e) {
                return gson.toJson(ErrorResponseDto.message(e.getMessage()));
            }
        });

        get("/room/:id/start", (req, res) -> {
            try {
                // TODO 로그인 체크 (유저가 가지고 있는 방인지 체크)
                Long roomId = Long.valueOf(req.params(":id"));
                List<PieceDto> result = controller.start(roomId);
                return gson.toJson(OkResponseDto.payload(result));
            } catch (Exception e) {
                return gson.toJson(ErrorResponseDto.message(e.getMessage()));
            }
        });

        get("/room/:id/end", (req, res) -> {
            try {
                // TODO 로그인 체크 (유저가 가지고 있는 방인지 체크)
                Long roomId = Long.valueOf(req.params(":id"));
                List<PieceDto> result = controller.end(roomId);
                return gson.toJson(OkResponseDto.payload(result));
            } catch (Exception e) {
                return gson.toJson(ErrorResponseDto.message(e.getMessage()));
            }
        });

        post("/room/:id/move", (req, res) -> {
            try {
                Long roomId = Long.valueOf(req.params(":id"));
                MoveRequestDto moveRequestDto = gson.fromJson(req.body(), MoveRequestDto.class);
                List<PieceDto> result = controller.move(roomId, moveRequestDto.getSource(), moveRequestDto.getTarget());
                return gson.toJson(OkResponseDto.payload(result));
            } catch (Exception e) {
                return gson.toJson(ErrorResponseDto.message(e.getMessage()));
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
