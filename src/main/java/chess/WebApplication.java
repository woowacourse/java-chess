package chess;

import static spark.Spark.*;

import chess.controller.GameController;
import chess.dto.ResponseDto;
import chess.dto.ResultDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.Map;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        final GameController controller = new GameController();

        get("/start", controller::start, new HandlebarsTemplateEngine());

        get("/chess", controller::printBoard, new HandlebarsTemplateEngine());

//        post("/move", controller::move, new HandlebarsTemplateEngine());
        post("/move", (req, res) -> {
            final String command = "move " + req.body();
            final ResponseDto responseDto = progress(controller, command);
            return responseDto.toString();
        });

        get("/status", controller::status, new HandlebarsTemplateEngine());

        get("/result", controller::result, new HandlebarsTemplateEngine());
//
//        get("/restart", (req, res) -> {
//            game.restart();
//            res.redirect("/start");
//            return null;
//        });
    }

    private static ResponseDto progress(final GameController controller, final String command) {
        try {
            controller.move(command);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return new ResponseDto(400, e.getMessage(), controller.isRunning());
        }

        return new ResponseDto(200, "", controller.isRunning());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
