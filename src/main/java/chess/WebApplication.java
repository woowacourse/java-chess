package chess;

import chess.controller.ChessController;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        new ChessController().run();
//		get("/", (req, res) -> {
//			Map<String, Object> model = new HashMap<>();
//			return render(model, "index.html");
//		});
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
