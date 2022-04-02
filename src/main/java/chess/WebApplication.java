package chess;

import static spark.Spark.*;

import chess.dto.response.BoardResult;
import chess.dto.response.PieceResult;
import chess.game.Board;
import chess.game.BoardInitializer;
import chess.game.Position;
import chess.piece.Piece;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Map;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");

        final Map<Position, Piece> board = new Board(BoardInitializer.getBoard()).getValue();

        get("/", (req, res) -> render(new BoardResult(board).getValue(), "index.html"));

    }

    private static String render(final Map<String, PieceResult> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
