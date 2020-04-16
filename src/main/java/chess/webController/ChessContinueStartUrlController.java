package chess.webController;

import chess.service.ChessGameSettingService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;

public class ChessContinueStartUrlController {
    public static void run(ChessGameSettingService chessGameSettingService) {

        get("/chess", (req, res) -> {
            Map<String, Object> model = chessGameSettingService.settingChessBoard();
            return render(model, "contents/chess.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
