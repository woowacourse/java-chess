package chess;

import chess.controller.WebChessController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebApplication {

    public static void main(String[] args) {
        WebChessController controller = new WebChessController();
        controller.run();
    }
}
