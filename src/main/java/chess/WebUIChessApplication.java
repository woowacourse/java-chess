package chess;

import chess.conroller.ChessGameController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Collections;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();

        staticFiles.location("/public");
        // AJAX 요청 시 CORS 적용
        options("/*", WebUIChessApplication::cors);
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/api/sessions", ChessGameController::retrieveSessions, gson::toJson);
        get("/api/session/:id", ChessGameController::retrieveSessionById, gson::toJson);
        post("/api/session", ChessGameController::createSession, gson::toJson);
        get("/api/game/movable", ChessGameController::movableCoordinates, gson::toJson);
        put("/api/game/move", ChessGameController::movePiece, gson::toJson);
        get("/api/game/score", ChessGameController::retrieveScore, gson::toJson);
        post("/api/game/end", ChessGameController::endGame, gson::toJson);

        get("/*", (req, res) -> render(Collections.emptyMap(), "index.html"));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String cors(Request request, Response response) {

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
    }
}
