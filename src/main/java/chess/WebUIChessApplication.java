package chess;

import chess.domain.ChessBoard;
import chess.domain.Coordinate;
import chess.domain.Position;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    private static ChessBoard chessBoard;
    public static void main(String[] args) {
        staticFiles.location("/assets");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/game_play", (req, res) -> {
            chessBoard = new ChessBoard();
            Map<String, Object> model = new HashMap<>();
            model.put("a1", new Position(new Coordinate(1), new Coordinate(1)));
            model.put("a2", "pawn");
            return render(model, "game_play.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
