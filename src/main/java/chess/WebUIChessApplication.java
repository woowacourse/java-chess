package chess;

import chess.controller.Response;
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
            Response response = webUIChessController.resetGameState();
            return render(response.getModel(), "index.html");
        });

        post("/game", (req, res) -> {
            String roomId = req.queryParams("room_id");
            Response response = webUIChessController.createRoom(roomId);
            if (response.isNotSuccessful()) {
                res.status(response.getHttpStatus());
            }
            return render(response.getModel(), "game.html");
        });

        post("/game/move", (req, res) -> {
            List<String> moveCommand = getMoveCommand(req);
            Response response = webUIChessController.movePiece(moveCommand);
            if (response.isNotSuccessful()) {
                res.status(response.getHttpStatus());
            }
            return render(response.getModel(), "game.html");
        });

        get("/save", (req, res) -> {
            Response response = webUIChessController.getSavedRooms();
            return render(response.getModel(), "repository.html");
        });

        post("/game/load", "application/json", (req, res) -> {
            Response response = webUIChessController.loadRoom(req.body());
            if (response.isNotSuccessful()) {
                res.status(response.getHttpStatus());
            }
            return render(response.getModel(), "game.html");
        });

        post("/game/save", "application/json", (req, res) -> {
            Response response = webUIChessController.saveRoom(req.body());
            if (response.isNotSuccessful()) {
                res.status(response.getHttpStatus());
            }
            return !response.isNotSuccessful();
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
