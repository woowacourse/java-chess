package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.ChessController;
import chess.dto.BoardsDto;
import chess.dto.ResponseDto;
import chess.dto.ScoreDto;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessApplication {

    private static final ChessController chessController = new ChessController();

    public static void main(String[] args) {

        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("boards", chessController.getBoards());
            return render(model, "home.html");
        });

        post("/room", (req, res) -> {
            final List<String> createRoomInput = Arrays.stream(req.body().strip().split("\n"))
                    .map(s -> s.split("=")[1])
                    .collect(Collectors.toList());
            final int roomId = chessController.startGame(createRoomInput.get(0), createRoomInput.get(1),
                    createRoomInput.get(2));
            res.redirect("/room/" + roomId);
            return null;
        });

        get("/room/:roomId", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("roomId", Integer.parseInt(req.params(":roomId")));
            model.put("board", chessController.getBoard(Integer.parseInt(req.params(":roomId"))));
            return render(model, "chess-game.html");
        });

        post("/room/:roomId/move", (req, res) -> {
            final String[] split = req.body().strip().split("=")[1].split(" ");
            final int roomId = Integer.parseInt(req.params(":roomId"));
            ResponseDto response = chessController.move(roomId, split[1], split[2]);
            return response.toString();
        });

        get("/room/:roomId/status", (req, res) -> {
            final ScoreDto scoreDto = chessController.score(Integer.parseInt(req.params(":roomId")));
            return scoreDto.toString();
        });

        post("/room/:roomId/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            final int roomId = Integer.parseInt(req.params(":roomId"));
            model.put("result", chessController.score(roomId));
            chessController.end(roomId);
            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
