package chess;

import chess.domain.ChessGame;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Gson gson = new Gson();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessGame game = new ChessGame();
            Map<String, String> status = game.status();
            return render(model, "index.html");
        });

        get("/chess", (req, res) -> {
            ChessGame game = new ChessGame();
            return game.status();
        }, gson::toJson);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
