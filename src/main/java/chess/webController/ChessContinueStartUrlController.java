package chess.webController;

import chess.service.ChessGameSetting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;

public class ChessContinueStartUrlController {
    public static void run(ChessGameSetting chessGameSetting) {

        get("/chess", (req, res) -> {
            Map<String, Object> model = chessGameSetting.settingChessBoard();
            return render(model, "contents/chess.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
