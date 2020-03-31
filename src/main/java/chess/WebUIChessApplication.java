package chess;

import static chess.JsonUtil.*;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.domain.MoveDto;
import chess.domain.board.Board;
import chess.service.BoardService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    private static BoardService boardService;

    public static void main(String[] args) {

        port(8080);
        staticFiles.location("/public");

        options("/*",
            (request, response) -> {

                String accessControlRequestHeaders = request
                    .headers("Access-Control-Request-Headers");
                if (accessControlRequestHeaders != null) {
                    response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
                }

                String accessControlRequestMethod = request
                    .headers("Access-Control-Request-Method");
                if (accessControlRequestMethod != null) {
                    response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
                }

                return "OK";
            });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/board", (request, response) -> {
            validate(response);
            return boardService.getBoard();
        }, json());

        post("/board", (request, response) -> {
            response.type("application/json");
            boardService = new BoardService(Board.init());
            return boardService.getBoard();
        }, json());

        post("/move", (request, response) -> {
            validate(response);
            MoveDto dto = new Gson().fromJson(request.body(), MoveDto.class);
            return boardService.move(dto.getFrom(), dto.getTo());
        }, json());
    }

    public static void validate(final Response response) {
        response.type("application/json");
        if (Objects.isNull(boardService)) {
            boardService = new BoardService(Board.init());
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
