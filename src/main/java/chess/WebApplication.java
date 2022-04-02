package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.domain.board.BasicChessBoardGenerator;
import chess.domain.board.Board;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        port(8089);
        staticFileLocation("/static");
        Board board = BasicChessBoardGenerator.generator();

        get("/", (req, res) -> {
            Map<String, Object> model = board.toMap();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
