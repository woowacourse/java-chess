package chess;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    public static void main(String[] args) {
        staticFileLocation("/static");

        ChessGame chessGame = new ChessGame(BoardFactory.createChessBoard());

        get("/", (req, res) -> {
            return new ModelAndView(chessGame.getCurrentBoardForSpark(), "index.html");
        }, new HandlebarsTemplateEngine());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
