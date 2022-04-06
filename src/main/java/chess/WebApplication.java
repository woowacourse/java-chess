package chess;

import game.WebChessGame;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebApplication {

    public static void main(String[] args) {
        staticFileLocation("/static");
        port(8080);
        WebChessGame webChessGame = new WebChessGame();
        webChessGame.run();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
