package chess;

import chess.domain.game.BoardInitializer;
import chess.domain.game.ChessController;
import chess.domain.game.GameService;
import chess.view.ResponseDto;
import chess.view.BoardDto;
import chess.view.StatusDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebApplication {

    public static void main(String[] args) {
        port(8081);
        staticFiles.location("/static");

        ChessController controller = new ChessController();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("boards", controller.getBoards());
            return render(model, "home.html");
        });

        post("/make-room", (req, res) -> {
            System.out.println(req.body().strip());
            final List<String> createRoomInput = Arrays.stream(req.body().strip().split("\n"))
                    .map(s -> s.split("=")[1])
                    .collect(Collectors.toList());
            final int roomId = controller.startGame(createRoomInput.get(0), createRoomInput.get(1), createRoomInput.get(2));
            res.redirect("/room/" + roomId);
            return null;
        });


        get("/room/:roomId", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("roomId", Integer.parseInt(req.params(":roomId")));
            model.put("board", controller.getBoard(Integer.parseInt(req.params(":roomId"))));
            return render(model, "index.html");
        });

        post("/room/:roomId/move", (req, res) -> {
            final String[] split = req.body().strip().split("=");
            final int roomId = Integer.parseInt(req.params(":roomId"));
            ResponseDto response = controller.move(roomId, split[1]);
            return response.toString();
        });

        post("/room/:roomId/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            final int roomId = Integer.parseInt(req.params(":roomId"));
            model.put("result", controller.status(roomId));
            controller.end(roomId);
            return render(model, "result.html");
        });

        get("/room/:roomId/status", (req, res) -> {
            int roomId = Integer.parseInt(req.params(":roomId"));
            final StatusDto statusDto = controller.status(roomId);
            return statusDto.toString();
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
