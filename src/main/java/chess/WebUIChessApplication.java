package chess;

import chess.conroller.ChessGameController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();

        // AJAX 요청 시 CORS 적용
        options("/*", WebUIChessApplication::cors);
        before((request, response) -> response.header("Access-Control-Allow-Origin", "localhost"));

        get("/api/rooms", ChessGameController::retrieveRooms, gson::toJson);
        get("/api/room/:id", ChessGameController::retrieveRoomById, gson::toJson);
        post("/api/room", ChessGameController::createRoom, gson::toJson);
        put("/api/game/move", ChessGameController::movePiece, gson::toJson);

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
