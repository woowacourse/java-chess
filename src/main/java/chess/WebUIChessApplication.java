package chess;

import static spark.Spark.*;

import chess.domain.ChessGame;
import chess.domain.User;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");

        ChessGame chessGame = new ChessGame();

        get("/users", (req, res) -> {
            User user = new User();
            user.setName("dd");
            user.setAge("12");

            Map<String, Object> model = new HashMap<>();
            model.put("user", user);

            return render(model, "result.html");
        });

        get("/", (req, res) -> {
            User user = new User();
            user.setName("dd");
            user.setAge("12");

            // Rook rook = new Rook(Color.BLACK);

            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            // model.put("rook", rook);

            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}