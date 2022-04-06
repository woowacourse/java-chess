package chess;

import static spark.Spark.*;

import chess.controller.GameController;
import chess.dto.ResponseDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
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

//        get("/save", controller::save, new HandlebarsTemplateEngine());
//
//        get("/status", controller::status, new HandlebarsTemplateEngine());
//
//        get("/result", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            final ResultDto result = new ResultDto(game.getResult(), game.getWinColor());
//            model.put("result", result);
//
//            return render(model, "result.html");
//        });
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
            return new ResponseDto(400, e.getMessage(), true);
        }

//        if (!game.isRunning()) {
//            return new ResponseDto(200, "", true);
//        }

        return new ResponseDto(200, "", true);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
