package chess;

import chess.controller.dto.Tile;
import chess.controller.dto.WebBoardDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");

        List<Tile> tiles = new WebBoardDto().getTiles();

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", tiles);
            return render(model, "chessGame.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
