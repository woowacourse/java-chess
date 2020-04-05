package chess;

import static chess.util.JsonUtil.*;
import static spark.Spark.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.dao.PlayerDao;
import chess.domain.JdbcGameContext;
import chess.dto.MovableRequestDto;
import chess.dto.MoveRequestDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    private static ChessService chessService = new ChessService(new JdbcGameContext());

    public static void main(String[] args) {

        // 플레이어 회원가입 / 로그인 구현 이전 foreign key 오류를 내지 않기 위해 임시로 DB에 플레이어 추가
        try {
            new PlayerDao().addInitialPlayers();
        } catch (SQLException ignored) {
        }

        port(8080);
        staticFiles.location("/public");

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.type("application/json");
        });

        get("/", (request, response) -> {
            response.type("text/html");
            return render(new HashMap<>());
        });

        get("/boards", (request, response) -> chessService.getPlayerContexts(), json());

        post("/boards", (request, response) -> chessService.addGameAndGetPlayers(), json());

        get("/scores", (request, response) -> chessService.getScoreContexts(), json());

        path("/boards", () -> {
            get("/:id", (request, response) -> chessService.getBoard(getId(request)), json());

            post("/:id", (request, response) -> chessService.resetBoard(getId(request)), json());

            delete("/:id", (request, response) -> chessService.finishGame(getId(request)), json());

            path("/:id", () -> {
                get("/status", (request, response) -> chessService.isGameOver(getId(request)), json());

                get("/turn", (request, response) -> chessService.isWhiteTurn(getId(request)), json());

                post("/movable", (request, response) -> {
                    MovableRequestDto dto = new Gson().fromJson(request.body(), MovableRequestDto.class);
                    return chessService.findAllAvailablePath(getId(request), dto.getFrom());
                }, json());

                post("/move", (request, response) -> {
                    MoveRequestDto dto = new Gson().fromJson(request.body(), MoveRequestDto.class);
                    return chessService.move(getId(request), dto);
                }, json());

                get("/score", (request, response) -> chessService.getScore(getId(request)), json());
            });
        });
    }

    public static int getId(final Request request) {
        return Integer.parseInt(request.params(":id"));
    }

    private static String render(Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }
}
