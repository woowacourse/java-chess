package chess;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");


        Grid grid = new Grid(new NormalGridStrategy());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("grid", grid);
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            grid.move("a1", "a2");
            return "test";
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
