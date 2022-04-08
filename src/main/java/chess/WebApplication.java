package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.dto.ApiResult;
import chess.dto.MoveCommand;
import chess.service.ChessWebService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    private static final int MAX_GAME_NUMBER = 3;

    public static void main(String[] args) {
        port(8089);
        staticFileLocation("/static");

        List<ChessWebService> services = IntStream.range(0, MAX_GAME_NUMBER)
                .mapToObj(gameNumber -> new ChessWebService())
                .collect(Collectors.toList());
        Gson gson = new Gson();

        get("/room", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Integer> gameNumbers = new ArrayList<>();
            for (int i = 0; i < MAX_GAME_NUMBER; i++) {
                gameNumbers.add(i);
            }
            model.put("gameNumbers", gameNumbers);
            return render(model, "room.html");
        });

        get("/room/:gameNumber", (req, res) -> {
            int gameNumber = Integer.parseInt(req.params(":gameNumber"));
            Map<String, Object> model = services.get(gameNumber)
                    .getBoard(gameNumber)
                    .toMap();
            model.put("gameNumber", gameNumber);
            return render(model, "board.html");
        });

        get("/room/:gameNumber/initialize", (req, res) -> {
            int gameNumber = Integer.parseInt(req.params(":gameNumber"));
            services.get(gameNumber).initializeState(gameNumber);
            res.redirect("/room/" + gameNumber);
            return null;
        });

        get("/room/:gameNumber/status", (req, res) -> {
            int gameNumber = Integer.parseInt(req.params(":gameNumber"));
            ApiResult statusResult = services.get(gameNumber).getStatus(gameNumber);
            return gson.toJson(statusResult);
        });

        post("/room/:gameNumber/move", (req, res) -> {
            final MoveCommand command = gson.fromJson(req.body(), MoveCommand.class);
            int gameNumber = Integer.parseInt(req.params(":gameNumber"));
            ApiResult apiResult = services.get(gameNumber).movePiece(command);

            return gson.toJson(apiResult);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
