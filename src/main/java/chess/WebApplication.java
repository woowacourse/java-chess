package chess;

import chess.domain.game.ChessController;
import chess.dto.ResponseDto;
import chess.dto.RequestDto;
import chess.dto.StatusDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

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

        post("/room", (req, res) -> {
            final int roomId = controller.startGame(RequestDto.of(req.body().strip()));
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
