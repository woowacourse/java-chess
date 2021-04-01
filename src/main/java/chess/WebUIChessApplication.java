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
        staticFiles.location("/public");

        WebUIChessController webUIChessController = new WebUIChessController();

        get("/", (req, res) -> {
            webUIChessController.resetChessGame();
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/game", (req, res) -> {
            String roomId = req.queryParams("room_id");
            Map<String, Object> model = webUIChessController.chessBoard(roomId);
            if (model == null) {
                Map<String, Object> modelWithAlert = new HashMap<>();
                modelWithAlert.put("room_id", roomId);
                modelWithAlert.put("alert", "는 이미 존재하는 방입니다.");
                return render(modelWithAlert, "index.html");
            }
            model.put("room_id", roomId);
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

        get("/save", (req, res) -> {
            Map<String, Object> model = webUIChessController.getSavedRooms();
            return render(model, "repository.html");
        });

        post("/game/load", "application/json", (req, res) -> {
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
