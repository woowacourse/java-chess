package chess.web;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.web.dto.BoardDto;
import java.util.List;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");

        ChessGame chessGame = new ChessGame();
        Map<String, Object> model = new HashMap<>();

        get("/", (req, res) -> {
            model.put("pieces", BoardDto.newInstance(chessGame.board()).getPieces());
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
