package chess;

import chess.controller.WebChessController;
import chess.controller.dto.BoardDto;
import chess.controller.dto.TileDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {

        staticFiles.location("/templates");
        WebChessController webChessController = new WebChessController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/game", (req, res) -> {
            List<TileDto> tileDtos = webChessController.start();
            Map<String, Object> model = new HashMap<>();
            model.put("tiles", tileDtos);

            return render(model, "test.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
