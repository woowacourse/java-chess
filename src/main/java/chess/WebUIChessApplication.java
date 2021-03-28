package chess;

import chess.dto.requestdto.StartRequestDto;
import chess.dto.response.Response;
import chess.dto.response.ResponseCode;
import chess.dto.responsedto.GridAndPiecesResponseDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static final Gson GSON = new Gson();
    private static final JsonTransformer JSON_TRANSFORMER = new JsonTransformer();

    public static void main(String[] args) {
        staticFiles.location("/public");
        ChessService chessService = new ChessService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
//
//        post("/move", (req, res) -> {
//            Map<String, String> reqBody = (Map) GSON.fromJson(req.body(), new HashMap<>().getClass());
//            String sourcePosition = reqBody.get("source");
//            String targetPosition = reqBody.get("target");
//            return chessService.move(new MoveRequestDto(sourcePosition, targetPosition));
//        }, JSON_TRANSFORMER);

        get("/grid/:roomName", (req, res) -> {
            String roomName = req.params(":roomName");
            StartRequestDto startRequestDto = new StartRequestDto(roomName);
            GridAndPiecesResponseDto gridAndPiecesResponseDto = chessService.start(startRequestDto);
            return new Response(ResponseCode.OK, gridAndPiecesResponseDto);
        }, JSON_TRANSFORMER);
//
//        get("/check/finished", (req, res) -> {
//            return chessService.checkFinished();
//        }, JSON_TRANSFORMER);
//
//        get("/winner", (req, res) -> {
//            return chessService.getWinner();
//        }, JSON_TRANSFORMER);
//
//        post("/restart", (req, res) -> {
//            return chessService.restart();
//        }, JSON_TRANSFORMER);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
