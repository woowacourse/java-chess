package chess;

import chess.controller.WebChessController;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFiles.location("/static");
        WebChessController webChessController = new WebChessController();
        webChessController.run();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
