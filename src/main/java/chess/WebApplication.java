package chess;

import chess.domain.board.BasicBoardStrategy;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class WebApplication {

    public static void main(final String... args) {
        port(8081);
        staticFileLocation("/static");
        get("/", (req, res) -> {
            Board board = new Board();
            board.initBoard(new BasicBoardStrategy());
            Map<String, Piece> model = board.toMap();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Piece> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}

