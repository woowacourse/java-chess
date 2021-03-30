package chess;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("public");
        ChessBoard chessBoard = new ChessBoard();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            for (Entry<Position, Piece> entry : chessBoard.getChessBoard().entrySet()) {
                model.put(entry.getKey().getPosition(),
                    entry.getValue());
            }
            return render(model, "index.html");
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
