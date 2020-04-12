package chess.webController;

import chess.service.Service;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;

public class ChessContinueStartUrlController {
    public static void run() {
        Service service = new Service();

        get("/chess", (req, res) -> {
            Map<String, Object> model = service.settingChessBoard();
            return render(model, "contents/chess.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
