package chess;

import chess.cotroller.ChessController;
import chess.cotroller.RoomController;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        RoomController roomController = new RoomController();
        ChessController chessController = new ChessController();

        staticFiles.location("/templates");

        get("/", WebUIChessApplication::index);

        get("/room/status", roomController::status);
        post("/room/create", roomController::create);
        post("/room/join", roomController::join);
        post("/room/exit", roomController::exit);
        post("/room/quit", roomController::quit);

        get("/chess/renew", chessController::renew);
        get("/chess/way", chessController::getMovableWay);
        post("/chess/move", chessController::move);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static Object index(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return WebUIChessApplication.render(model, "index.html");
    }
}
