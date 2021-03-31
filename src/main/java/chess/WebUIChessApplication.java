package chess;

import chess.controller.WebUIChessController;
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

        post("/game", (req, res) -> {
            Map<String, Object> model = webUIChessController.chessBoard();
            model.put("room_id", req.queryParams("room_id"));
            return render(model, "game.html");
        });

        post("/game/move", (req, res) -> {
            List<String> moveCommand = getMoveCommand(req);
            Map<String, Object> model = webUIChessController.movePiece(moveCommand);
            if (model == null) {
                res.status(400);
                return null;
            }
            return render(model, "game.html");
        });

        get("/game/save", "application/json", (req, res) -> {
            Map<String, Object> model = webUIChessController.loadRoom(req.body());
            if (model == null) {
                res.status(400);
            }
            return render(model, "game.html");
        });

        post("/game/save", "application/json", (req, res) -> {
            boolean isRegistrationSuccessful = webUIChessController.saveRoom(req.body());
            if (!isRegistrationSuccessful) {
                res.status(500);
            }
            return isRegistrationSuccessful;
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
