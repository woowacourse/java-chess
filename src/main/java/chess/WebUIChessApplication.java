package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.board.ChessBoard;
import chess.domain.service.WebController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {



    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = new Gson();
        staticFileLocation("public");
        WebController webController = new WebController(gson);
        webController.setChessBoard(new ChessBoard());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/start", WebController::start);
        post("/move", WebController::move);
        post("/reset", WebController::reset);
        post("/save", WebController::save);
        post("/load", WebController::load);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
