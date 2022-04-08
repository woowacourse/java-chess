package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.dto.MoveRequestDto;
import chess.dto.MoveResultDto;
import chess.service.ChessJDBCDao;
import chess.service.ChessService;
import chess.service.DBChessServiceImpl;
import chess.view.web.JsonTransformer;
import chess.view.web.WebViewMapper;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    private static final ChessService CHESS_SERVICE = new DBChessServiceImpl(new ChessJDBCDao());

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (request, response) -> render(new HashMap<>(), "index.html"));

        get("/game/:gameId", (request, response) -> render(new HashMap<>(), "game.html"));

        get("/board/:gameId", (request, response) ->
                        WebViewMapper.parseBoardFromDB(CHESS_SERVICE.getBoardByGameId(request.params("gameId")))
                , new JsonTransformer());

        get("/score/:gameId",
                (request, response) -> CHESS_SERVICE.getScore(request.params("gameId")), new JsonTransformer());

        get("/isFinished/:gameId", ((request, response) -> CHESS_SERVICE.isFinished(request.params("gameId"))),
                new JsonTransformer());

        post("/move", (request, response) -> {
            final MoveRequestDto moveRequestDto = new Gson().fromJson(request.body(), MoveRequestDto.class);
            final boolean moveResult = CHESS_SERVICE.move(moveRequestDto.getGameId(), moveRequestDto.getFrom(),
                    moveRequestDto.getTo());
            return new MoveResultDto(moveRequestDto.getPiece(), moveRequestDto.getFrom(), moveRequestDto.getTo(),
                    moveResult);
        }, new JsonTransformer());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
