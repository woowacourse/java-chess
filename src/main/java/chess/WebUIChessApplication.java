package chess;

import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) throws SQLException {
        port(8084);
        final Gson GSON = new Gson();
        ChessService chessService = new ChessService();
        JsonTransformer jsonTransformer = new JsonTransformer();
        staticFiles.location("/public");
        chessService.initChessGame();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        post("/restart", (req, res) -> {
            chessService.initChessGame();
            return "보드 초기화 성공!";
        }, jsonTransformer);
        post("/move", (req, res) -> {
            RequestPosition requestPosition = GSON.fromJson(req.body(), RequestPosition.class);
            return chessService.move(requestPosition);
        }, jsonTransformer);
        post("/currentBoard", (req, res) -> {
            return chessService.getCurrentBoard();
        }, jsonTransformer);
        post("/currentTurn", (req, res) -> {
            return chessService.turn();
        }, jsonTransformer);


    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}