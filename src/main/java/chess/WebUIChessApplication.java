package chess;

import chess.controller.WebUIChessController;
import chess.domain.user.User;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.view.InputView.SPACE;
import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("");

        WebUIChessController webUIChessController = new WebUIChessController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/registration", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "registration.html");
        });

        post("/registration", (req, res) -> {
            boolean isRegistrationSuccessful = webUIChessController.addUser(req.body());
            if (!isRegistrationSuccessful) {
                res.status(500);
            }
            return isRegistrationSuccessful;
        });

        post("/menu", (req, res) -> {
//            User user = new User(req.queryParams("id"), req.queryParams("pwd"));
            // 검증
            Map<String, Object> model = new HashMap<>();
//            model.put("user", user);
            return render(model, "menu.html");
        });

        get("/menu", (req, res) -> {
//            User user = new User(req.queryParams("id"), req.queryParams("pwd"));
            // 검증
            Map<String, Object> model = new HashMap<>();
//            model.put("user", user);
            return render(model, "menu.html");
        });

        get("/game", (req, res) -> {
            Map<String, Object> model = webUIChessController.initializeChessBoard();
            return render(model, "game.html");
        });

        post("/game", (req, res) -> {
            List<String> moveCommand = getMoveCommand(req);
            Map<String, Object> model = webUIChessController.movePiece(moveCommand);
            if (model == null) {
                res.status(400);
                return null;
            }
            return render(model, "game.html");
        });

        get("/save", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "save.html");
        });
    }

    private static List<String> getMoveCommand(spark.Request req) {
        return Arrays.stream(req.queryParams()
                .iterator()
                .next()
                .split(SPACE))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
