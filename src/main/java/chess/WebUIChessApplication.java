package chess;

import chess.dto.requestdto.MoveRequestDto;
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

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/public");
        ChessService chessService = new ChessService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
//
        post("/move", (req, res) -> {
            MoveRequestDto moveRequestDto = GSON.fromJson(req.body(), MoveRequestDto.class);
            return chessService.move(moveRequestDto);
        }, GSON::toJson);

        get("/grid/:roomName", (req, res) -> {
            String roomName = req.params(":roomName");
            StartRequestDto startRequestDto = new StartRequestDto(roomName);
            GridAndPiecesResponseDto gridAndPiecesResponseDto = chessService.getGridAndPieces(startRequestDto);
            return new Response(ResponseCode.OK, gridAndPiecesResponseDto);
        }, GSON::toJson);

        post("/grid/:gridId/start", (req, res) -> {
            String gridId = req.params("gridId");
            chessService.start(Long.parseLong(gridId));
            return new Response(ResponseCode.NO_CONTENT);
        }, GSON::toJson);
//
        post("/grid/:gridId/finish", (req, res) -> {
            String gridId = req.params("gridId");
            chessService.finish(Long.parseLong(gridId));
            return new Response(ResponseCode.NO_CONTENT);
        }, GSON::toJson);

        get("/room/:roomId/restart", (req, res) -> {
            String roomId = req.params("roomId");
            return new Response(ResponseCode.OK, chessService.restart(Long.parseLong(roomId)));
        }, GSON::toJson);

        exception(Exception.class, (exception, request, response) -> {
            Response res = new Response(ResponseCode.SERVER_ERROR);
            response.body(GSON.toJson(res));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
